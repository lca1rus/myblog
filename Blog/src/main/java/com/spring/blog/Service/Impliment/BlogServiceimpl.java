package com.spring.blog.Service.Impliment;

import com.spring.blog.Service.BlogService;
import com.spring.blog.dao.Blogmaoper;
import com.spring.blog.model.Blogmodel.Blog;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class BlogServiceimpl  implements BlogService {

    @Autowired
    Blogmaoper blogmaoper;
    @Test
    public List getBlogList() {
        List blogs=blogmaoper.getBlogList();
    return blogs;

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
