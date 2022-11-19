package com.spring.blog.controller;

import com.spring.blog.Service.Impliment.BlogServiceImpl;
import com.spring.blog.model.entity.Blogmodel.Blog;
import com.spring.blog.model.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Article")
public class Article_controller {
    @Autowired
    private BlogServiceImpl blogService;
    @GetMapping("/getArticle")
    public Result getArticle(@RequestParam int id) {

        try {

            Blog blogs = blogService.getArticle(id);
            System.out.println("ing");
            return Result.ok("请求成功", blogs);
        }

        catch (Exception e) {
            System.out.println(e.getMessage());
            return Result.create(500, "异常错误");
        }


    }
}
