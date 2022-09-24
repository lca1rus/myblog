package com.spring.blog.Service.Impliment;

import com.spring.blog.Service.RedisService;
import com.spring.blog.model.entity.Blogmodel.Blog;
import com.spring.blog.model.vo.AdminBlogInfo;
import com.spring.blog.model.vo.PageResult;
import com.spring.blog.uitl.JacksonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public class RdisServiceimpl implements RedisService {

    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public PageResult<AdminBlogInfo> RedisgetBlogList(String hash, String pageNum) {

        if (redisTemplate.opsForHash().hasKey(hash,pageNum)) {//判断是否存在该key
            Object redisResult = redisTemplate.opsForHash().get(hash,pageNum);
            //直接从实现了implements UserDetails的类中拿会出现序列化的问题
            //需要写一个userinfo中拿取
            PageResult<AdminBlogInfo> list1 = JacksonUtils.convertValue(redisResult, PageResult.class);
            System.out.println("ok,RedisGetBlog");
            return list1;
        } else {
            return null;
        }
    }

    @Override
    public void saveKVToHash(String hash, Object key, Object value) {
        redisTemplate.opsForHash().put(hash, key, value);

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

    }

    @Override
    public boolean hasKey(String key) {
        return false;
    }

    @Override
    public void expire(String key, long time) {

    }
}
