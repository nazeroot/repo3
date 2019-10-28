package com.itheima.wechat;

import cn.hutool.core.lang.UUID;
import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.itheima.constant.RedisConstant;
import com.itheima.entity.Result;
import com.itheima.pojo.Member;
import com.itheima.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RestController
@RequestMapping("/login")
public class LoginController {
    @Reference
    OrderService orderService;
    @Autowired
    JedisUtil jedisUtil;
    @RequestMapping("/check")
    public Result login(@RequestBody Member member, HttpServletRequest request){
        String phoneNumber = member.getPhoneNumber();
        String validateCode = member.getValidateCode();
        String KEY = phoneNumber + RedisConstant.SENDTYPE_LOGIN;
        String value = jedisUtil.get(KEY);
        if (!value.equalsIgnoreCase(validateCode)){
            return Result.error("请输入验证码");
        }
//        查询用户是否注册
        Member member1 = orderService.selectMember(member);
        if (null==member1) {
            member1 = new Member();
            member1.setPhoneNumber(phoneNumber);
            member1.setRegTime(new Date());
           orderService.addLoginMember(member1);
        }
//        向客户端写入Cookie，内容为用户手机号
//        request.getSession().setAttribute("member", JSON.toJSONString(member1));
//        现在使用集群下共享Session
        String token = UUID.randomUUID().toString();
        jedisUtil.setex(token,60*60*24, JSON.toJSONString(member1));
        return Result.success("登陆成功",token);
    }
}
