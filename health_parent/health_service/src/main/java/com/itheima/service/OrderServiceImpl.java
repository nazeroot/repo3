package com.itheima.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.itheima.dao.MemberDao;
import com.itheima.dao.OrderDao;
import com.itheima.dao.OrderSettingDao;
import com.itheima.pojo.Member;
import com.itheima.pojo.Order;
import com.itheima.pojo.OrderSetting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service(interfaceClass = OrderService.class)
@Transactional
public class OrderServiceImpl implements OrderService {
    @Autowired
    MemberDao memberDao;
    @Autowired
    OrderSettingDao orderSettingDao;
    @Autowired
    OrderDao orderDao;
    @Override
    public Member selectMember(Member member) {
        Member user =  memberDao.selectMember(member);
        return user;
    }

    @Override
    public Integer addMember(Member member1) {
        memberDao.addMember(member1);
        return member1.getId();
    }

    @Override
    public OrderSetting selectOrderSettiongTime(Date regTime) {
       OrderSetting orderSetting = orderSettingDao.selectOrderSettiongTime(regTime);
        return orderSetting;
    }

    @Override
    public Integer selectOrderMessge(Order order) {
       Integer orderNum = orderDao.selectOrderMessge(order);
        return orderNum;
    }

    @Override
    public void addOrder(Order order) {
        orderDao.addOrder(order);
    }

    @Override
    public void addOrderSettingNum(Date regTime) {
        orderSettingDao.addOrderSettingNum(regTime);
    }
}
