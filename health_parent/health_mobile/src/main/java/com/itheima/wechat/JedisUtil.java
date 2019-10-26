package com.itheima.wechat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Component
public class JedisUtil {

    @Autowired
    JedisPool jedisPool;

    public Long incr(String key){
        Jedis jedis = null;
        try{
            jedis = jedisPool.getResource();
            return jedis.incr(key);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(jedis != null){
                jedis.close();
            }
        }
        return 0L;
    }

    public Long del(String key){
        Jedis jedis = null;
        try{
            jedis = jedisPool.getResource();
            return jedis.del(key);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(jedis != null){
                jedis.close();
            }
        }
        return 0L;
    }

    public String get(String key){
        Jedis jedis = null;
        try{
            jedis = jedisPool.getResource();
            return jedis.get(key);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(jedis != null){
                jedis.close();
            }
        }
        return "";
    }


    public String set(String key,String value){
        Jedis jedis = null;
        try{
            jedis = jedisPool.getResource();
            return jedis.set(key,value);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(jedis != null){
                jedis.close();
            }
        }
        return "";
    }

    public String setex(String key,int seconds,String value){
        Jedis jedis = null;
        try{
            jedis = jedisPool.getResource();
            return jedis.setex(key,seconds,value);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(jedis != null){
                jedis.close();
            }
        }
        return "";
    }

}
