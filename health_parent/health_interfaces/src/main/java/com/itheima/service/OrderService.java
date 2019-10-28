package com.itheima.service;

import com.itheima.pojo.Member;
import com.itheima.pojo.Order;
import com.itheima.pojo.OrderSetting;

import java.util.Date;

public interface OrderService {
    Member selectMember(Member phoneNumber);

    Integer addMember(Member member1);

    OrderSetting selectOrderSettiongTime(Date regTime);

    Integer selectOrderMessge(Order order);

    void addOrder(Order order);

    void addOrderSettingNum(Date regTime);

    void addLoginMember(Member member1);
}
