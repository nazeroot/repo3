package com.itheima.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.dao.CheckGroupDao;
import com.itheima.dao.CheckItemDao;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.pojo.CheckGroup;
import com.itheima.pojo.CheckItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(interfaceClass = CheckGroupService.class)
@Transactional
public class CheckGroupServiceImpl implements CheckGroupService {

    @Autowired
    CheckGroupDao checkGroupDao;
    @Autowired
    CheckItemDao checkItemDao;
    @Override
    public void add(CheckGroup checkGroup) {
        checkGroupDao.add(checkGroup);
        Integer checkGroupId = checkGroup.getId();
        List<Integer> checkitemIds = checkGroup.getCheckitemIds();
        setRelationship(checkGroupId, checkitemIds);

    }

    public void setRelationship(Integer checkGroupId, List<Integer> checkitemIds) {
        List<Map> params =new ArrayList<>();
        for (Integer checkitemId : checkitemIds) {
            Map map = new HashMap();
            map.put("checkitemId",checkitemId);
            map.put("checkGroupId",checkGroupId);
            params.add(map);
        }
        checkGroupDao.setCheckGroupAndCheckItem(params);
    }

    @Override
    public PageResult findPage(QueryPageBean queryPageBean) {
        Page page = PageHelper.startPage(queryPageBean.getCurrentPage(), queryPageBean.getPageSize());
        checkGroupDao.findPage(queryPageBean.getQueryString());
        return new PageResult(page.getTotal(),page.getResult());
    }

    @Override
    public Map findById4Edit(Integer id) {
        Map map = new HashMap();
//        一：查询检查项
         List<CheckItem> checkItems = checkItemDao.findAll();
//        二：通过id查询检查组
        CheckGroup checkGroup = checkGroupDao.findCheckGroupById(id);
//        三：通过id查询检查项被选中的id
       List<Integer> checkItemIds = checkGroupDao.findCheckItemIds(id);
       map.put("checkItems",checkItems);
       map.put("checkGroup",checkGroup);
       map.put("checkItemIds",checkItemIds);
        return map;
    }

    @Override
    public void edit(CheckGroup checkGroup) {
//    一：修改检查组
        Integer integer = checkGroupDao.edit(checkGroup);
//    二：删除检查组与检查项关系(通过检查组id)
        Integer integer2 = checkGroupDao.deleteRelationship(checkGroup.getId());
//    三：重新设置检查组与检查项关系(通过检查组id)
        setRelationship(checkGroup.getId(), checkGroup.getCheckitemIds());
/*        List<Map> params =new ArrayList<>();
        for (Integer checkitemId : checkitemIds) {
            Map map = new HashMap();
            map.put("checkitemId",checkitemId);
            map.put("checkGroupId",checkGroupId);
            params.add(map);
        }
        checkGroupDao.setCheckGroupAndCheckItem(params);*/
    }


}
