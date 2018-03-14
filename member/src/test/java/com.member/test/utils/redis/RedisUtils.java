package com.member.test.utils.redis;

import redis.clients.jedis.Jedis;

/**
 * Created by jackson on 16/6/6.
 */
public class RedisUtils {
    /**
     * 获取key对应值
     *
     */

    public static String redisGet(String key,int DbNum){
        String ip = "10.17.1.61";
        int port = 6379;
        Jedis redis = new Jedis(ip,port);
        redis.select(DbNum);
        String code = redis.get(key);
        return code;
    }
    public static Long redisDel(String key,int DbNum){
        String ip = "10.17.1.61";
        int port = 6379;
        Jedis redis = new Jedis(ip,port);
        redis.select(DbNum);
        Long result = redis.del(key);
        return result;
    }
}
