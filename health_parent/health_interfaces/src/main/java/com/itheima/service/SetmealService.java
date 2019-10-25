package com.itheima.service;

import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.entity.Result;
import com.itheima.pojo.Setmeal;

public interface SetmealService {
    void add(Setmeal setmeal);

    PageResult findPage(QueryPageBean queryPageBean) ;

    Result getSetmeal();

    Result findById(Integer id);
}
