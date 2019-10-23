package com.itheima.dao;

import com.itheima.pojo.CheckGroup;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CheckGroupDao {
    void add(CheckGroup checkGroup);

    void setCheckGroupAndCheckItem(@Param("params") List<Map> params);


    List<CheckGroup> findPage(@Param("queryString") String queryString);


    CheckGroup findCheckGroupById(@Param("id") Integer id);

    List<Integer> findCheckItemIds(@Param("id")Integer id);

    Integer edit(CheckGroup checkGroup);

    Integer deleteRelationship(@Param("id") Integer id);

    List<CheckGroup> findAll();
}
