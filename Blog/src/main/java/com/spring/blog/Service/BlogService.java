package com.spring.blog.Service;

import com.spring.blog.model.Blogmodel.Blog;

import java.util.Date;
import java.util.List;

public interface BlogService {


        List getBlogList();
        void deleteBlog(Integer id);
        void updateBlog(Integer id,String title,String content);
        void insertBlog(String title, String content, Date createTime);
}
