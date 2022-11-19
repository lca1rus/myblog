package com.spring.blog.controller;

import com.spring.blog.Service.Impliment.BlogServiceImpl;
import com.spring.blog.model.entity.Blogmodel.Blog;
import com.spring.blog.model.vo.PageResult;
import com.spring.blog.model.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController

@RequestMapping("/index")
public class Index_controller {

    @Autowired
    private BlogServiceImpl blogService;


    @GetMapping("/getIndexBlogList")
    public Result getIndexBlogList(@RequestParam(defaultValue = "1") Integer pageNum) {

        try {

            PageResult<Blog> blogs = blogService.getIndexBlogList(pageNum);

            return Result.ok("请求成功", blogs);
        }

        catch (Exception e) {
            System.out.println(e.getMessage());
            return Result.create(500, "异常错误");
        }


    }
}
