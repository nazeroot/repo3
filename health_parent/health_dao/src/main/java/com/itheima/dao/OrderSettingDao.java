package com.itheima.dao;

import com.itheima.pojo.OrderSetting;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface OrderSettingDao {

    Integer findOrderSettingCount(@Param("orderDate") Date orderDate);

    void update(OrderSetting orderSetting);

    void add(OrderSetting orderSetting);

    List<OrderSetting> findOrderSettingsByMonth(@Param("start") String start, @Param("end") String end);
}
