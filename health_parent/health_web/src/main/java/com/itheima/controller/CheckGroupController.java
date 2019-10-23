package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.constant.MessageConstant;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.entity.Result;
import com.itheima.pojo.CheckGroup;
import com.itheima.service.CheckGroupService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/checkgroup")
public class CheckGroupController {
    @Reference
    CheckGroupService checkGroupService;
    @RequestMapping("/add")
    public Result add(@RequestBody CheckGroup checkGroup){
        checkGroupService.add(checkGroup);
        return Result.success(MessageConstant.ADD_CHECKGROUP_SUCCESS);
    }
/*    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean){
        PageResult pageResult = checkGroupService.findPage(queryPageBean);
        return pageResult;
    }*/
    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean){

    return checkGroupService.findPage(queryPageBean);
}
        @RequestMapping("/findAll")
        public Result findAll(){
        return checkGroupService.findAll();}

    @RequestMapping("/findById4Edit")
    public Result findById4Edit(Integer id){
        Map map = checkGroupService.findById4Edit(id);
        return Result.success("",map);}
        @RequestMapping("/edit")
    public Result edit(@RequestBody CheckGroup checkGroup){
            checkGroupService.edit(checkGroup);
            return Result.success(MessageConstant.EDIT_CHECKGROUP_SUCCESS,null); }



}
