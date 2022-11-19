package com.spring.blog.Service;

import com.spring.blog.model.entity.Blogmodel.Blog;
import com.spring.blog.model.vo.AdminBlogInfo;
import com.spring.blog.model.vo.PageResult;

import java.util.Date;
import java.util.List;

public interface BlogService {
        void WriteBlog(Blog writeBlogInfo);
        int getIDByUsername(String name);
        PageResult<Blog> getIndexBlogList(Integer pageNum);

        List<Blog> GetBlogByName(String name);
        PageResult<AdminBlogInfo> getBlogList(Integer pageNum);
        void deleteBlog(Integer id);
        void updateBlog(Integer id,String title,String content);
        void insertBlog(String title, String content, Date createTime);

        Blog  getArticle(int id);


}
