package com.itheima.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.dao.SetmealDao;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.pojo.Setmeal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(interfaceClass = SetmealService.class)
@Transactional
public class SetmealServiceImpl implements SetmealService {
    @Autowired
    SetmealDao setmealDao;
    @Override
    public void add(Setmeal setmeal) {
        Integer[] checkgroupIds = setmeal.getCheckgroupIds();
        setmealDao.add(setmeal);
        Integer setmealId = setmeal.getId();
        List<Map> params = new ArrayList();
        for (Integer checkgroupId : checkgroupIds) {
            Map map = new HashMap();
            map.put("checkgroup_id",checkgroupId);
            map.put("setmeal_id",setmealId);
            params.add(map);
        }
       Integer integer = setmealDao.addSetmealsAndCheckGoup(params);
    }

    @Override
    public PageResult findPage(QueryPageBean queryPageBean) {
        Page page = PageHelper.startPage(queryPageBean.getCurrentPage(),queryPageBean.getPageSize());
        List<Setmeal> pages = setmealDao.findPage(queryPageBean.getQueryString());
        return new PageResult(page.getTotal(),page.getResult());
    }
}
