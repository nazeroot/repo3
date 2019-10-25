package com.itheima.service;

import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.dao.SetmealDao;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.entity.Result;
import com.itheima.pojo.CheckGroup;
import com.itheima.pojo.CheckItem;
import com.itheima.pojo.Setmeal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    @Override
    public Result getSetmeal() {

        return Result.success("",setmealDao.getSetmeal());
    }

    @Override
    public Result findById(Integer id) {
//        根据套餐id查出套餐基本信息
        Setmeal setmeal = setmealDao.findSetmealById(id);
        if (null!=setmeal) {
//        根据套餐id查出检查组基本信息集合
        List<CheckGroup> checkGroups = setmealDao.findCheckGroupsBySetmealId(id);
//        根据检查组id查出检查项基本信息
            if (CollectionUtil.isNotEmpty(checkGroups)) {
        List<Integer> checkItemIds = findCheckItemIds(checkGroups);
        List<CheckItem> checkItems = setmealDao.findCheckItemsByCheckGroupIds(checkItemIds);
//        将查出的检查项分组存入相应的检查组
        Map<Integer, List<CheckItem>> result = checkItems.stream().collect(Collectors.groupingBy(CheckItem::getCheckGroupId));
        for (CheckGroup checkGroup : checkGroups) {
            checkGroup.setCheckItems(result.get(checkGroup.getId()));
        }
            }
        setmeal.setCheckGroups(checkGroups);
        }

        return Result.success("",setmeal);
    }
    public List<Integer> findCheckItemIds(List<CheckGroup> checkGroups){
        List<Integer> ids = new ArrayList<>();
        for (CheckGroup checkGroup : checkGroups) {
            ids.add(checkGroup.getId());
        }
        return ids;
    }

}
