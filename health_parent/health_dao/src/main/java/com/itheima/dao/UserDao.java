package com.itheima.dao;

import com.itheima.pojo.User;

import java.util.List;

public interface UserDao {
    List<User> findUsers();
}
