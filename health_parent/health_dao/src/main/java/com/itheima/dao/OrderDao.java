package com.itheima.dao;

import com.itheima.pojo.Order;

public interface OrderDao {
    Integer selectOrderMessge(Order order);

    void addOrder(Order order);
}
