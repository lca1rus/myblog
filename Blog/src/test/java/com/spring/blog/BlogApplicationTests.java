package com.spring.blog;

import com.spring.blog.dao.Blogmaoper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class BlogApplicationTests {


    @Autowired
    Blogmaoper blogMapper;

    @Test
    void test() {
        List blogs = blogMapper.getBlogList();
        System.out.println(blogs);
    }
}
