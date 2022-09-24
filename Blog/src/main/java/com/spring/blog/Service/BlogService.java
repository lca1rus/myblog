package com.spring.blog.Service;

import com.spring.blog.model.vo.AdminBlogInfo;
import com.spring.blog.model.vo.PageResult;

import java.util.Date;
import java.util.List;

public interface BlogService {


        PageResult<AdminBlogInfo> getBlogList(Integer pageNum);
        void deleteBlog(Integer id);
        void updateBlog(Integer id,String title,String content);
        void insertBlog(String title, String content, Date createTime);
}
