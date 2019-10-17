package com.itheima.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.itheima.dao.UserDao;
import com.itheima.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service(interfaceClass = UserService.class)
@Transactional
public class UserServiceImpl implements UserService{
    @Autowired
    UserDao  userDao;
    @Override
    public List<User> findUsers() {
        System.out.println("findUsers-");
        return userDao.findUsers();
    }
}
