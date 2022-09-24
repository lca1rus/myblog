package com.spring.blog.dao;

import com.spring.blog.model.vo.AdminBlogInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Mapper
@Repository

public interface Blogmaoper {
    List<AdminBlogInfo> getBlogList();

    void deleteBlog(Integer id);

    void updateBlog(Integer id,String title,String content);

    void insertBlog(String title, String content, Date createTime);
}
