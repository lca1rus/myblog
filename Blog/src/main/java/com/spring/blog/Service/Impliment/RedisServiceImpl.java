package com.spring.blog.Service.Impliment;

import com.spring.blog.Service.RedisService;
import com.spring.blog.model.vo.AdminBlogInfo;
import com.spring.blog.model.vo.PageResult;
import com.spring.blog.uitl.JacksonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;
import java.util.Map;
@Service
public class RedisServiceImpl implements RedisService {

    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public PageResult RedisGetBlogList(String hash, String pageNum) {

        if (redisTemplate.opsForHash().hasKey(hash,pageNum)) {//判断是否存在该key
            Object redisResult = redisTemplate.opsForHash().get(hash,pageNum);
            //直接从实现了implements UserDetails的类中拿会出现序列化的问题
            //需要写一个userinfo中拿取
            PageResult list = JacksonUtils.convertValue(redisResult, PageResult.class);
            //将object对象序列化为PageResult对象
            System.out.println("ok,one msg from Redis");
            return list;
        } else {
            return null;
        }
    }
    public boolean RedisGetUser_State(String hash, String username) {//检查是否存在
//redisTemplate.opsForZSet().add("zes1",System.currentTimeMillis()+5000,);

        return redisTemplate.opsForHash().hasKey(hash,username);


    }
    public boolean hashPutIfAbsent(String key, String hashKey, String value) {
        return redisTemplate.opsForHash().putIfAbsent(key, hashKey, value);//当不存在时才重新设置
    }


    @Override
    public void saveKVToHash(String hash, Object key, Object value) {//put一个hash进入
        redisTemplate.opsForHash().put(hash, key, value);

    }

    @Override
    public boolean setnx_lock(String key, double lock, long expire_time) {

       return redisTemplate.opsForValue().setIfAbsent(key,lock, Duration.ofSeconds(expire_time));

    }

    @Override
    public Object getByString(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public Object DeleteByString(String key) {
        return redisTemplate.opsForValue().getAndDelete(key);
    }

    @Override
    public Long DeleteByHashKey(String hash_key, String key) {
        return redisTemplate.opsForHash().delete(hash_key,key);

    }


    @Override
    public void saveMapToHash(String hash, Map map) {

    }

    @Override
    public Map getMapByHash(String hash) {
        return null;
    }

    @Override
    public Object getValueByHashKey(String hash, Object key) {
        return null;
    }

    @Override
    public void incrementByHashKey(String hash, Object key, int increment) {

    }

    @Override
    public void deleteByHashKey(String hash, Object key) {
        redisTemplate.boundHashOps(hash).delete(key);

    }

    @Override
    public <T> List<T> getListByValue(String key) {
        return null;
    }

    @Override
    public <T> void saveListToValue(String key, List<T> list) {

    }

    @Override
    public <T> Map<String, T> getMapByValue(String key) {
        return null;
    }

    @Override
    public <T> void saveMapToValue(String key, Map<String, T> map) {

    }

    @Override
    public <T> T getObjectByValue(String key, Class t) {
        return null;
    }

    @Override
    public void incrementByKey(String key, int increment) {

    }

    @Override
    public void saveObjectToValue(String key, Object object) {

    }

    @Override
    public void saveValueToSet(String key, Object value) {

    }

    @Override
    public int countBySet(String key) {
        return 0;
    }

    @Override
    public void deleteValueBySet(String key, Object value) {

    }

    @Override
    public boolean hasValueInSet(String key, Object value) {
        return false;
    }

    @Override
    public void deleteCacheByKey(String key) {
        redisTemplate.delete(key);
    }

    @Override
    public boolean hasKey(String key) {
        return false;
    }

    @Override
    public void expire(String key, long time) {

    }
}
