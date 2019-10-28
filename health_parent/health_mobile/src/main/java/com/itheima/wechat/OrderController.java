package com.itheima.wechat;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.constant.RedisConstant;
import com.itheima.entity.Result;
import com.itheima.pojo.Member;
import com.itheima.pojo.Order;
import com.itheima.pojo.OrderSetting;
import com.itheima.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Reference
    OrderService orderService;
    @Autowired
    JedisUtil jedisUtil;
    @RequestMapping("/submit")
    public Result submit(@RequestBody Member member){
        if (null!=member) {
            String name = member.getName();
            String sex = member.getSex();
            String phoneNumber = member.getPhoneNumber();
            String validateCode = member.getValidateCode();
            String idCard = member.getIdCard();
            Date regTime = member.getRegTime();
            String setmeal_id = member.getSetmeal_id();


            String KEY = member.getPhoneNumber() + RedisConstant.SENDTYPE_ORDER;
            String ValidateCode = jedisUtil.get(KEY);
            if (!ValidateCode.equalsIgnoreCase(validateCode)) {
                return Result.error("验证码输入错误");
            }
//        查询用户是否存在
            Member user= orderService.selectMember(member);

            System.out.println("user"+user);
//            如果用户不存在，则添加用户
            if (null==user) {
                Member member1 = new Member();
                member1.setName(name);
                member1.setSex(sex);
                member1.setPhoneNumber(phoneNumber);
                member1.setIdCard(idCard);
                member1.setRegTime(regTime);
                Integer addId = orderService.addMember(member1);
                member.setId(addId);
            }else {
                member.setId(user.getId());
            }
//             查询预约日期是否可预约，以及是否已经预约满
            OrderSetting orderSetting = orderService.selectOrderSettiongTime(regTime);
            System.out.println(orderSetting);
//            判断条件，可以预约且预约人数未满
            if (null!=orderSetting && orderSetting.getNumber()>orderSetting.getReservations()) {
                Order order = new Order();
                order.setMemberId(member.getId());
                order.setOrderDate(regTime);
                order.setOrderType(Order.ORDERTYPE_WEIXIN);
                order.setOrderStatus(Order.ORDERSTATUS_NO);
                order.setSetmealId(Integer.valueOf(setmeal_id));
//                查询用户是否已经预约
                Integer orderNum = orderService.selectOrderMessge(order);
//                若没有，则预约，且将OrderSetting表中已预约人数加1，为了防止超卖，可以加乐观锁
                if (orderNum==0) {
                orderService.addOrder(order);
                orderService.addOrderSettingNum(regTime);
                }
            }

            return Result.success("预约成功");
        }else {
            return Result.error("请输入用户信息");
        }


    }
}
