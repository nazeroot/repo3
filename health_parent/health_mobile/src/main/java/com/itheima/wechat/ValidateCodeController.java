package com.itheima.wechat;

import com.itheima.constant.RedisConstant;
import com.itheima.utils.ValidateCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/validateCode")
public class ValidateCodeController {
    @Autowired
    JedisUtil jedisUtil;

    @RequestMapping("/send4Order")
    public void send4Order(String phoneNumber){
        Integer integer = ValidateCodeUtils.generateValidateCode(4);
        String KEY = phoneNumber+ RedisConstant.SENDTYPE_ORDER;
        String value = String.valueOf(integer);
        jedisUtil.setex(KEY,60 * 60 ,value);
        System.out.println(value);
    }

}
