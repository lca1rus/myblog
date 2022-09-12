package com.spring.blog.Service.Impliment;

import com.spring.blog.Service.BlogService;
import com.spring.blog.dao.Blogmaoper;
import com.spring.blog.model.Blogmodel.Blog;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
