package com.itheima.security;

import com.itheima.pojo.User;
import com.qiniu.util.Md5;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Component
public class MyUserService implements UserDetailsService {
    //模拟数据库中的用户数据
    public static Map<String, User> map = new HashMap<>();

    static {
        com.itheima.pojo.User user1 = new com.itheima.pojo.User();
        user1.setUsername("admin");
        user1.setPassword("$2a$10$MMRUr.6Q/K/5aue3tU4VcuMUrhPO/11Edyt1m3dsx7wEUr2jmrKJO");

        com.itheima.pojo.User user2 = new com.itheima.pojo.User();
        user2.setUsername("xiaoming");
        user2.setPassword("$2a$10$MMRUr.6Q/K/5aue3tU4VcuMUrhPO/11Edyt1m3dsx7wEUr2jmrKJO");

        map.put(user1.getUsername(), user1);
        map.put(user2.getUsername(), user2);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //查询数据库
        User user = map.get(username);
        //返回null 框架会提示你
        if(null == user){
            return null;
        }

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        if("admin".equals(username)){
            authorities.add(new SimpleGrantedAuthority("删除检查项"));
        }

        authorities.add(new SimpleGrantedAuthority("新增检查项"));

        return new org.springframework.security.core.userdetails.User(username,user.getPassword(),authorities);
    }

    public static void main(String[] args) {
//        String s = Md5.md5("1111".getBytes());
////        System.out.println(s);
//
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//        String encode = encoder.encode("1111");
//        System.out.println(encode);
//        //$2a$10$MMRUr.6Q/K/5aue3tU4VcuMUrhPO/11Edyt1m3dsx7wEUr2jmrKJO
//        //$2a$10$1OhETr4nPensYj4xb8DIju8m64gvnpwPY7Dn0nsO/Uw6ZLnTl3afu
//        //$2a$10$.GhTnGEBhzii.2lRcJpD3uU49ZM.mQN5bC7Sb9faDK.O8E5/vScmy
//
//
//        boolean matches1 = encoder.matches("1111", "$2a$10$MMRUr.6Q/K/5aue3tU4VcuMUrhPO/11Edyt1m3dsx7wEUr2jmrKJO");
//        boolean matches2 = encoder.matches("1111", "$2a$10$1OhETr4nPensYj4xb8DIju8m64gvnpwPY7Dn0nsO/Uw6ZLnTl3afu");
//        boolean matches3 = encoder.matches("1111", "$2a$10$.GhTnGEBhzii.2lRcJpD3uU49ZM.mQN5bC7Sb9faDK.O8E5/vScmy");
//
//        System.out.println(matches1);
//        System.out.println(matches2);
//        System.out.println(matches3);


        String pwd = "1111";
        long start = System.currentTimeMillis();
        String encode = new BCryptPasswordEncoder().encode(pwd);
        long end = System.currentTimeMillis();
        System.out.println((end - start));


        long startmd5 = System.currentTimeMillis();
        String md5 = Md5.md5(pwd.getBytes());
        long endmd5 = System.currentTimeMillis();
        System.out.println((endmd5 - startmd5));
    }
}
