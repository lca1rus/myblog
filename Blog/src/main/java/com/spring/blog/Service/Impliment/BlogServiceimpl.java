package com.spring.blog.Service.Impliment;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.spring.blog.Constants.RedisKeyConstants;
import com.spring.blog.Service.BlogService;
import com.spring.blog.dao.Blogmaoper;
import com.spring.blog.model.vo.AdminBlogInfo;
import com.spring.blog.model.vo.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    Blogmaoper blogmaoper;
    @Autowired
    RedisServiceImpl redisServiceimpl;
    //随机博客显示5条
    private static final int randomBlogLimitNum = 5;
    //最新推荐博客显示3条
    private static final int newBlogPageSize = 3;
    //每页显示5条博客简介
    private static final int pageSize = 4;
    //每次拿出的博客个数
    private static final String orderBy = "is_top desc, create_time desc";
    //私密博客提示
    private static final String PRIVATE_BLOG_DESCRIPTION = "此文章受密码保护！";

    String redisKey = RedisKeyConstants.HOME_BLOG_INFO_LIST;

    public PageResult<AdminBlogInfo> getBlogList(Integer pageNum) {
    String page=String.valueOf(pageNum-1);
        PageResult<AdminBlogInfo> pageResultFromRedis=redisServiceimpl.RedisGetBlogList(redisKey, page);

        if (pageResultFromRedis!=null) {

            return pageResultFromRedis;
        }
        //redis没有缓存，从数据库查询，并添加缓存
       PageHelper.startPage(pageNum, pageSize);//页数为1，每次3个blog

        List<AdminBlogInfo> blogInfos =blogmaoper.getBlogList();//从mysql数据库取数据

        PageInfo<AdminBlogInfo> pageInfo = new PageInfo<>(blogInfos);//创建pageinfo

        PageResult<AdminBlogInfo> pageResult = new PageResult<>(pageInfo.getPageSize(), pageInfo.getList());

        redisServiceimpl.saveKVToHash(redisKey,page,pageResult);

    return pageResult;

    }

    @Override
    public void deleteBlog(Integer id) {
        double locknum=Math.random();
        boolean lock= redisServiceimpl.setnx_lock("lock",locknum,20);

        try {//加分布式锁
            if (lock) {//如果拿到开始操作
            int key=(id-1)/4;
            redisServiceimpl.DeleteByHashKey(redisKey, String.valueOf(key));//先删除redis缓存
                blogmaoper.deleteBlog(id);//再删除数据库中

            }else {
                System.out.println("没拿到锁");
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

    public void updateBlog(Integer id,String title,String content) {
        double locknum=Math.random();
        boolean lock= redisServiceimpl.setnx_lock("lock2",locknum,20);

        try {//加分布式锁
            if (lock) {//如果拿到开始操作
                int key=(id-1)/4;//将每4个blog，放在一个页中，索引从0开始
                blogmaoper.updateBlog(id,title,content);
               redisServiceimpl.DeleteByHashKey(redisKey, String.valueOf(key));//删除原来的redis缓存

            }else {
                System.out.println("没拿到锁");
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

}
