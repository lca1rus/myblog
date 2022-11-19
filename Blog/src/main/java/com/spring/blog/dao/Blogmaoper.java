package com.spring.blog.dao;

import com.spring.blog.model.entity.Blogmodel.Blog;
import com.spring.blog.model.vo.AdminBlogInfo;
import com.spring.blog.model.vo.UserLoginMsg;
import com.spring.blog.model.vo.WriteBlogInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Mapper
@Repository

public interface Blogmaoper {
    int WriteBlog(Blog writeBlogInfo);
    UserLoginMsg getIDByUsername(String name);
    List<Blog> getIndexBlogList();

    List<Blog> GetBlogByName(String name);
    List<AdminBlogInfo> getAdminBlogList();

    void deleteBlog(Integer id);

    void updateBlog(Integer id,String title,String content);

    void insertBlog(String title, String content, Date createTime);
    Blog getArticle(int id);


}
