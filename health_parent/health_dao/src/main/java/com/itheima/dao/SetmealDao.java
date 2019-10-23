package com.itheima.dao;

import com.itheima.pojo.Setmeal;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SetmealDao {

    void add(Setmeal setmeal);

    Integer addSetmealsAndCheckGoup(@Param("params") List<Map> params);

    List<Setmeal> findPage(@Param("queryString") String queryString);
}
