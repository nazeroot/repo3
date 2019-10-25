package com.itheima.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.google.common.collect.Lists;
import com.itheima.dao.OrderSettingDao;
import com.itheima.entity.Result;
import com.itheima.pojo.OrderSetting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

@Service(interfaceClass = OrderSettingService.class)
@Transactional
public class OrderSettingServiceImpl implements OrderSettingService {
    @Autowired
    OrderSettingDao orderSettingDao;
    @Override
    public void setNumberByDate(OrderSetting orderSetting) {
        Integer count = orderSettingDao.findOrderSettingCount(orderSetting.getOrderDate());
        if (null != count && count >0) {
            orderSettingDao.update(orderSetting);
        }else {

        orderSettingDao.add(orderSetting);
        }
    }

    @Override
    public Result findOrderSettingsByMonth(String month) {
        String start = month + "-01";
        String end = month + "-31";
       List<OrderSetting> orderSettingList = orderSettingDao.findOrderSettingsByMonth(start,end);
        for (OrderSetting orderSetting : orderSettingList) {
            orderSetting.setMonth( orderSetting.getOrderDate().getMonth());
            orderSetting.setDate(orderSetting.getOrderDate().getDate());
        }
        return Result.success("",orderSettingList);
    }

    @Override
    public void batchAdd(List<OrderSetting> orderSettings) {
//        固定创建5个线程
        ExecutorService executorService = Executors.newFixedThreadPool(5);
//        多线程把orderSettings集合分成多份
        List<List<OrderSetting>> partition = Lists.partition(orderSettings,20);
        List<Future<Integer>> futures = new ArrayList<>();
        for (List<OrderSetting> settings : partition) {
            Future<Integer> future =executorService.submit(new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    return 1;
                }

            });
            futures.add(future);
        }
        int count = 0;
        for (Future<Integer> future : futures) {
            try {
                Integer o = future.get();
                count = count + o;
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        if (count == partition.size()) {
//            告诉客户端已帮人把数据批量导入到数据库
        }
    }


}
