package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.constant.MessageConstant;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.entity.Result;
import com.itheima.execption.CheckItemExecption;
import com.itheima.pojo.CheckItem;
import com.itheima.service.CheckItemService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/checkitem")
public class CheckItemController {
    @Reference
    CheckItemService checkItemService;
    /**
     * 页面传的是json数据，后端使用map 或者 pojo时 需要加@RequestBody
     * 基本类型 & 数组 & MultipartFile 只要保持页面的参数名称和controller方法形参一致就不用加@RequestParam
     * List 不管名字一不一样 必须加@RequestParam
     * @return
     */
        @RequestMapping("/add")
    public Result add(@RequestBody CheckItem checkItem){
            try {
                checkItemService.add(checkItem);
                return new Result(true, MessageConstant.ADD_CHECKGROUP_SUCCESS);
            } catch (Exception e) {
                e.printStackTrace();
                return new Result(false, MessageConstant.ADD_CHECKGROUP_FAIL);
            }
        }
    @RequestMapping("/findAll")
    public  Result findAll(){
         return    checkItemService.findAll();
    }
        @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean){
            PageResult page = checkItemService.findPage(queryPageBean);
            return page;
        }
        @RequestMapping("/delete")
    public Result Delete(Integer id){
            try {
                checkItemService.delete(id);
                return Result.success(MessageConstant.DELETE_CHECKITEM_SUCCESS);
            }catch (CheckItemExecption e){
                e.printStackTrace();
                return Result.error(e.getMessage());
            } catch (Exception e) {
                e.printStackTrace();
            return Result.success(MessageConstant.DELETE_CHECKGROUP_FAIL);
            }
        }

        @RequestMapping("/findById")
    public Result findById(Integer id){
          return new Result(true,"",checkItemService.findById(id));
        }
        @RequestMapping("/edit")
    public Result edit(@RequestBody CheckItem checkItem) {
            try {
                checkItemService.edit(checkItem);
                return new Result(true, MessageConstant.EDIT_CHECKGROUP_SUCCESS);
            } catch (Exception e) {
                e.printStackTrace();
                return new Result(false, MessageConstant.EDIT_CHECKGROUP_FAIL);
            }
        }
}
