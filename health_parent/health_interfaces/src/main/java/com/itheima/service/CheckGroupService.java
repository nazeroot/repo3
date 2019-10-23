package com.itheima.service;

import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.entity.Result;
import com.itheima.pojo.CheckGroup;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/checkgroup")
public interface CheckGroupService {

    void add(CheckGroup checkGroup);


    PageResult findPage(QueryPageBean queryPageBean);

    Map findById4Edit(Integer id);

    void edit(CheckGroup checkGroup);

    Result findAll();
}
