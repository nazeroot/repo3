package com.itheima.service;

import com.itheima.entity.Result;
import com.itheima.pojo.OrderSetting;

import java.util.List;

public interface OrderSettingService {
    void setNumberByDate(OrderSetting orderSetting);

    Result findOrderSettingsByMonth(String month);


    void batchAdd(List<OrderSetting> orderSettings);
}
