package com.starter.fastweb.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;

@Component
public class RedisService {

    @Resource
    private JedisPool jedisPool;

    private static void releaseJedis(Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }
    }

    public String get(final String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.get(key);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            releaseJedis(jedis);
        }
        return null;
    }

    public void set(final String key,final String value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.set(key, value);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            releaseJedis(jedis);
        }
    }

    public void setEx(final String key,final String value, int expire){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.setex(key, expire, value);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            releaseJedis(jedis);
        }
    }




}
