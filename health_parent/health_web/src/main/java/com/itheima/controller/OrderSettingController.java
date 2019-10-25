package com.itheima.controller;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.entity.Result;
import com.itheima.pojo.OrderSetting;
import com.itheima.service.OrderSettingService;
import com.itheima.utils.POIUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/ordersetting")
public class OrderSettingController {
    @Reference
    OrderSettingService orderSettingService;
    @RequestMapping("setNumberByDate")
    public Result setNumberByDate(@RequestBody OrderSetting orderSetting){
        orderSettingService.setNumberByDate(orderSetting);
        return Result.success("");
    }
    @RequestMapping("findOrderSettingsByMonth")
    public Result findOrderSettingsByMonth(String month){
        return  orderSettingService.findOrderSettingsByMonth(month);
    }
    @RequestMapping("upload")
    public Result upload(MultipartFile excelFile){
        try {
            List<String[]> rows = POIUtils.readExcel(excelFile);
            List<OrderSetting> orderSettings = new ArrayList<>();
            if (CollectionUtil.isNotEmpty(rows)) {
                 for (String[] row : rows) {
                    if (row.length !=2) {
                    continue;
                }
                OrderSetting orderSetting = new OrderSetting();
                orderSetting.setOrderDate(DateUtil.parse(row[0],"yyyy/MM/dd"));
                orderSetting.setNumber(Integer.valueOf(row[1]));
                orderSettings.add(orderSetting);
            }
            }
            orderSettingService.batchAdd(orderSettings);
            return Result.success("");
        } catch (IOException e) {
            e.printStackTrace();
            return Result.error("");
        }
    }
}
