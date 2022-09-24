package com.spring.blog.Service.Impliment;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.spring.blog.Constants.RedisKeyConstants;
import com.spring.blog.Service.BlogService;
import com.spring.blog.dao.Blogmaoper;
import com.spring.blog.model.entity.Blogmodel.Blog;
import com.spring.blog.model.vo.AdminBlogInfo;
import com.spring.blog.model.vo.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class BlogServiceimpl  implements BlogService {

    @Autowired
    Blogmaoper blogmaoper;
    @Autowired
    RdisServiceimpl rdisServiceimpl;
    //随机博客显示5条
    private static final int randomBlogLimitNum = 5;
    //最新推荐博客显示3条
    private static final int newBlogPageSize = 3;
    //每页显示5条博客简介
    private static final int pageSize = 3;
    //博客简介列表排序方式
    private static final String orderBy = "is_top desc, create_time desc";
    //私密博客提示
    private static final String PRIVATE_BLOG_DESCRIPTION = "此文章受密码保护！";



    public PageResult<AdminBlogInfo> getBlogList(Integer pageNum) {
        String redisKey = RedisKeyConstants.HOME_BLOG_INFO_LIST;
        String pagekey=pageNum.toString();
        PageResult<AdminBlogInfo> pageResultFromRedis =  rdisServiceimpl.RedisgetBlogList(redisKey,pagekey);

        if (pageResultFromRedis != null) {
           // setBlogViewsFromRedisToPageResult(pageResultFromRedis);
            System.out.println("成功从redis获取");
            return pageResultFromRedis;
        }
        //redis没有缓存，从数据库查询，并添加缓存
        PageHelper.startPage(pageNum, pageSize);
        List<AdminBlogInfo> blogInfos =blogmaoper.getBlogList();//从mysql数据库取数据
        PageInfo<AdminBlogInfo> pageInfo = new PageInfo<>(blogInfos);//创建pageinfo
        PageResult<AdminBlogInfo> pageResult = new PageResult<>(pageInfo.getPages(), pageInfo.getList());
        rdisServiceimpl.saveKVToHash(redisKey,pagekey,pageResult);

    return pageResult;

    }

    @Override
    public void deleteBlog(Integer id) {
        blogmaoper.deleteBlog(id);
    }

    public void updateBlog(Integer id,String title,String content) {
        blogmaoper.updateBlog(id,title,content);
    }

    @Override
    public void insertBlog(String title, String content, Date createTime) {
        blogmaoper.insertBlog(title,content,createTime);
    }

}
