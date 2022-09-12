package com.spring.blog.controller;


import com.spring.blog.Service.Impliment.BlogServiceimpl;
import com.spring.blog.model.Blogmodel.Blog;
import com.spring.blog.model.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
//@RequestMapping("/admin")
public class Blog_controller  {
    @Autowired
  private   BlogServiceimpl blogService;

    @GetMapping("/blogs")
    public Result blogs() {

            List<Blog> blogs = blogService.getBlogList();
            System.out.println(blogs.toArray());
            Map<String, Object> map = new HashMap<>();
            map.put("blogs", blogs);
            return Result.ok("请求成功", map);

}}