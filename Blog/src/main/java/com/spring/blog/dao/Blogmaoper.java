package com.spring.blog.dao;

import com.spring.blog.model.Blogmodel.Blog;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository

public interface Blogmaoper {
    List getBlogList();
}
