package com.itheima.wechat;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.entity.Result;
import com.itheima.service.SetmealService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/setmeal")
public class SetmealController {
    @Reference
    SetmealService setmealService;
    @RequestMapping("/getSetmeal")
    public Result getSetmeal(){
        return  setmealService.getSetmeal();
    }
    @RequestMapping("/findById")
    public Result findById(Integer id){
        System.out.println(id);
        return  setmealService.findById(id);
    }
}
