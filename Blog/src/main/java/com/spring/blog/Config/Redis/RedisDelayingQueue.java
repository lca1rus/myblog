package com.spring.blog.Config.Redis;

import com.spring.blog.Constants.RedisKeyConstants;
import com.spring.blog.uitl.JacksonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Set;
import java.util.UUID;

public class RedisDelayingQueue<T> {
    @Autowired
    RedisTemplate redisTemplate;
    String redisKey = RedisKeyConstants.ZSET;
    static class TaskItem<T> {
        public String id;
        public T msg;
    }
    public void delay(T msg) {
        TaskItem task = new TaskItem();
        task.id = UUID.randomUUID().toString(); // 分配唯一的 uuid
        task.msg = msg;
        String s = JacksonUtils.writeValueAsString(msg); // fastjson 序列化
       // jedis.zadd(queueKey, System.currentTimeMillis() + 5000, s);
        redisTemplate.opsForZSet().add(redisKey,s,System.currentTimeMillis()+5000); // 塞入延时队列 ,5s 后再试
    }

    public void loop() {
        while (!Thread.interrupted()) {
                // 只取一条
           // Set values = jedis.zrangeByScore(queueKey, 0, System.currentTimeMillis(), 0, 1);
            Set values=  redisTemplate.opsForZSet().rangeByScore(redisKey,0,System.currentTimeMillis(),0,1);
            if (values.isEmpty()) {
                try {
                    Thread.sleep(500); // 歇会继续
                }
                catch (InterruptedException e) {
                    break;
                }
                continue;
            }
            String s = (String) values.iterator().next();
            if (redisTemplate.opsForZSet().remove(redisKey,s)>0) { // 抢到了
             // fastjson 反序列化
                TaskItem task = JacksonUtils.readValue(s, TaskItem.class);
                this.handleMsg((T) task.msg);
            }
        }
    }
    public void handleMsg(T msg) {
        System.out.println(msg);
    }

}
