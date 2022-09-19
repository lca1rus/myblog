package com.spring.blog.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.blog.Service.Impliment.BlogServiceimpl;
import com.spring.blog.model.Blogmodel.Blog;
import com.spring.blog.model.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.PrintWriter;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class Blog_controller  {
    @Autowired
  private   BlogServiceimpl blogService;

    @GetMapping("/GetBlogs")
    public Result GetBlogs() {

        try {

            List<Blog> blogs = blogService.getBlogList();
            Map<String, Object> map = new HashMap<>();
            map.put("blogs", blogs);
            System.out.println("成功get");
            return Result.ok("请求成功", map);
        }

        catch (Exception e) {
                return Result.create(500, "异常错误");
            }


    }
    @DeleteMapping("/deleteBlogs")
    public Result deleteBlogs(@RequestParam int id) {
        System.out.println("id");
        try {
                blogService.deleteBlog(id);

            System.out.println("成功delete");
            return Result.ok("删除请求成功");
        }

        catch (Exception e) {
            return Result.create(500, "异常错误");
        }


    }
    @RequestMapping("/updateBlog")
    public Result updateBlog(@RequestParam int id,@RequestParam String title,@RequestParam String content) {

        try {
            blogService.updateBlog(id,title,content);
            return Result.ok("更新请求成功");
        }

        catch (Exception e) {
            return Result.create(500, "异常错误");
        }


    }
    @RequestMapping("/insertBlog")
    public Result insertBlog(@RequestParam String title,@RequestParam String content) {
        java.util.Date date=new java.util.Date();

        Date date_sql=new Date(date.getTime());
        try {
            blogService.insertBlog(title,content,date_sql);
            return Result.ok("更新请求成功");
        }

        catch (Exception e) {
            return Result.create(500, "异常错误");
        }


    }



}