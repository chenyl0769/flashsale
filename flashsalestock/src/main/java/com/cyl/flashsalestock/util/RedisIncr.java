package com.cyl.flashsalestock.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/***
 * redis自增与自减
 */
@Component
public class RedisIncr {
    @Autowired
    private RedisTemplate redisTemplate;

    public Long getincr (String key , long endtime) {
        RedisAtomicLong redisAtomicLong = new RedisAtomicLong(key,redisTemplate.getConnectionFactory());
        Long num = redisAtomicLong.getAndIncrement();
        if (endtime>0&&(num==null||num.longValue()==0)) {
            redisAtomicLong.expire(endtime, TimeUnit.SECONDS);
        }
        return num;
    }

    public Long getdecr (String key , long endtime) {
        RedisAtomicLong redisAtomicLong = new RedisAtomicLong(key,redisTemplate.getConnectionFactory());
        Long num = redisAtomicLong.decrementAndGet();
        if (endtime>0&&(num==null||num.longValue()==0)) {
            redisAtomicLong.expire(endtime, TimeUnit.SECONDS);
        }
        return num;
    }
}
