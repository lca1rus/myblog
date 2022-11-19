package com.spring.blog.Config.Redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;

import java.util.UUID;

public class SimpleRateLimiter {
//redis流量控制
    @Autowired
    RedisTemplate redisTemplate;
    static long count=Integer.MAX_VALUE;
    public boolean isActionAllowed(String key, int max_count, int period) {//当同一时间段内的某个请求超过限制的最大数量时，会决定是否开始
        double now_time=System.currentTimeMillis();
        String uid=UUID.randomUUID().toString();

       redisTemplate.executePipelined(new SessionCallback<Object>() {//管道统一执行
            @Override
            public <K, V> Object execute(RedisOperations<K, V> operations) throws DataAccessException {
                RedisTemplate<String, Object> ops = (RedisTemplate<String, Object>) operations;
                ops.opsForZSet().add(key,uid,now_time);
                ops.opsForZSet().removeRangeByScore(key,0,now_time-period*1000);
                // 移除时间窗口之前的行为记录，剩下的都是时间窗口内的
                //获取窗口内的行为数量
                count= ops.opsForZSet().zCard(key);//得到剩余的10s内的请求数

                // 返回null即可，因为返回值会被管道的返回值覆盖，外层取不到这里的返回值
                return null;
            }
        });
            return count<=max_count;
    }

}
