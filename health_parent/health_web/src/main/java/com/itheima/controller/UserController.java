package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.pojo.User;
import com.itheima.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Reference
    UserService userService;
    @RequestMapping("/findUsers")
    public List<User> findUsers(){
        System.out.println("findUsers");
        return userService.findUsers(); }
}
