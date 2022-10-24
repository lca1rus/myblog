package com.spring.blog.controller;


import com.spring.blog.Service.Impliment.BlogServiceImpl;
import com.spring.blog.model.vo.AdminBlogInfo;
import com.spring.blog.model.vo.PageResult;
import com.spring.blog.model.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;


@RestController
public class Blog_controller  {
    @Autowired
  private BlogServiceImpl blogService;

    @GetMapping("/GetBlogs")
    public Result GetBlogs(@RequestParam(defaultValue = "1") Integer pageNum) {

        try {

            PageResult<AdminBlogInfo> blogs = blogService.getBlogList(pageNum);

            return Result.ok("请求成功", blogs);
        }

        catch (Exception e) {
            System.out.println(e.getMessage());
                return Result.create(500, "异常错误");
            }


    }
    @DeleteMapping("/deleteBlogs")
    public Result deleteBlogs(@RequestParam int id) {
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