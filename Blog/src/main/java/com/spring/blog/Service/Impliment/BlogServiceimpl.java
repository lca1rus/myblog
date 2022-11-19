package com.spring.blog.Service.Impliment;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.spring.blog.Constants.RedisKeyConstants;
import com.spring.blog.Service.BlogService;
import com.spring.blog.Service.RedisService;
import com.spring.blog.dao.Blogmaoper;
import com.spring.blog.model.entity.Blogmodel.Blog;
import com.spring.blog.model.vo.*;
import org.apache.ibatis.exceptions.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    Blogmaoper blogmaoper;
    @Autowired
    RedisService redisServiceimpl;
    //每页显示5条博客简介
    private static final int pageSize = 6;

    String AdminRedisKey = RedisKeyConstants.AdminBlogInfo;
    String IndexRedisKey = RedisKeyConstants.IndexBlogInfo;

    @Override
    public void WriteBlog(Blog writeBlogInfo) {
        if (blogmaoper.WriteBlog(writeBlogInfo) != 1) {
            throw new PersistenceException("添加博客失败");
        }
        redisServiceimpl.deleteCacheByKey(AdminRedisKey);
    }
    @Override
    public int getIDByUsername(String name) {
        UserLoginMsg userLoginMsg =blogmaoper.getIDByUsername(name);
        return userLoginMsg.getId();
    }

    public PageResult<Blog> getIndexBlogList(Integer pageNum) {//页数
        String page=String.valueOf(pageNum-1);
        PageResult<Blog> pageResultFromRedis=redisServiceimpl.RedisGetBlogList(IndexRedisKey, page);

        if (pageResultFromRedis!=null) {

            return pageResultFromRedis;
        }
        //redis没有缓存，从数据库查询，并添加缓存
        PageHelper.startPage(pageNum, pageSize);//每次6个blog

        List<Blog> blogInfos =blogmaoper.getIndexBlogList();//从mysql数据库取数据

        PageInfo<Blog> pageInfo = new PageInfo<>(blogInfos);//创建pageinfo

        PageResult<Blog> pageResult = new PageResult<>(pageInfo.getPageSize(), pageInfo.getList());

        redisServiceimpl.saveKVToHash(IndexRedisKey,page,pageResult);

        return pageResult;

    }

    @Override
    public List<Blog> GetBlogByName(String name) {
        List<Blog> blogInfos= blogmaoper.GetBlogByName(name);
        return blogInfos;
    }

    public PageResult<AdminBlogInfo> getBlogList(Integer pageNum) {
    String page=String.valueOf(pageNum-1);
        PageResult<AdminBlogInfo> pageResultFromRedis=redisServiceimpl.RedisGetBlogList(AdminRedisKey, page);

        if (pageResultFromRedis!=null) {

            return pageResultFromRedis;
        }
        //redis没有缓存，从数据库查询，并添加缓存
       PageHelper.startPage(pageNum, pageSize);//页数为1，每次3个blog

        List<AdminBlogInfo> blogInfos =blogmaoper.getAdminBlogList();//从mysql数据库取数据

        PageInfo<AdminBlogInfo> pageInfo = new PageInfo<>(blogInfos);//创建pageinfo

        PageResult<AdminBlogInfo> pageResult = new PageResult<>(pageInfo.getPageSize(), pageInfo.getList());

        redisServiceimpl.saveKVToHash(AdminRedisKey,page,pageResult);

    return pageResult;

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBlog(Integer id) {
        double locknum=Math.random();//保证每次的是同一线程
        boolean lock= redisServiceimpl.setnx_lock("lock",locknum,20);

        try {//加分布式锁
            if (lock) {//如果拿到开始操作
            int key=(id-1)/pageSize;
            redisServiceimpl.DeleteByHashKey(AdminRedisKey, String.valueOf(key));//先删除redis缓存
                blogmaoper.deleteBlog(id);//再删除数据库中

            }
        }finally {
            if(lock && redisServiceimpl.getByString("lock").equals(locknum)){
                /*两个问题
                一、只有拿到lock（既lock为ture）才能释放锁
                二、防止超时后前面线程锁过期后，线程继续开始进行，拿原来的锁进行释放锁操作影响，后面的线程
                通过设置一个随机数，保证每次线程拿到的是当前的
                 */
                redisServiceimpl.DeleteByString("lock");
                System.out.println("释放锁");
            }
            else {
                System.out.println("没拿到锁");
            }

        }

    }

    @Transactional(rollbackFor = Exception.class)
    public void updateBlog(Integer id,String title,String content) {
        double locknum=Math.random();
        boolean lock= redisServiceimpl.setnx_lock("lock2",locknum,20);

        try {//加分布式锁
            if (lock) {//如果拿到开始操作
                int key=(id-1)/pageSize;//将每6个blog，放在一个页中，索引从0开始
                blogmaoper.updateBlog(id,title,content);
               redisServiceimpl.DeleteByHashKey(AdminRedisKey, String.valueOf(key));//删除原来的redis缓存
            }
        }finally {
            if(lock && redisServiceimpl.getByString("lock2").equals(locknum)){
                /*两个问题
                一、只有拿到lock（既lock为ture）才能释放锁
                二、防止超时后前面线程锁过期后，线程继续开始进行，拿原来的锁进行释放锁操作影响，后面的线程
                通过设置一个随机数，保证每次线程拿到的是当前的
                 */
                redisServiceimpl.DeleteByString("lock2");
                System.out.println("释放锁");
            }
            else {
                System.out.println("没拿到锁");
            }

        }

    }

    @Override
    public void insertBlog(String title, String content, Date createTime) {
        blogmaoper.insertBlog(title,content,createTime);
    }

    @Override
    public Blog getArticle(int id) {

    return blogmaoper.getArticle(id);
    }


    /**
     * 删除关于我页面缓存
     */
    private void deleteAdminBlogInfoRedisCache() {
        redisServiceimpl.deleteCacheByKey(AdminRedisKey);
    }


    /**
     * 删除关于我页面缓存
     */
    private void deleteIndexBlogInfoRedisCache() {
        redisServiceimpl.deleteCacheByKey(IndexRedisKey);
    }
}
