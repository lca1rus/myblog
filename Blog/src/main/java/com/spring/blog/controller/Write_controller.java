package com.spring.blog.controller;

import com.spring.blog.Service.Impliment.BlogServiceImpl;
import com.spring.blog.model.entity.Blogmodel.Blog;
import com.spring.blog.model.vo.Result;
import com.spring.blog.uitl.JwtUtils;
import com.spring.blog.uitl.StringUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import java.util.Date;
/**
 * @author：lcarus
 * @date： 2022/11/18 16:06
 * @Description:写博客页面
 */

@RestController
public class Write_controller {
    @Autowired
    private BlogServiceImpl blogService;
//通过找到本次登录在的用户进行写博客
    @PutMapping("/WriteBlog")
    public Result WriteBlog(@RequestBody Blog blog, HttpServletRequest req) {
        return getResult(blog, "write",req);
    }
    private Result getResult(Blog blog, String type, HttpServletRequest req) {
        //验证普通字段
        if (StringUtils.isEmpty(blog.getTitle(), blog.getFirstPicture(), blog.getContent())) {
            return Result.error("参数有误");
        }
        String jwtToken = req.getHeader("Authorization");
        Claims claims = JwtUtils.jwtparser(jwtToken);//解码token
        String username = claims.getSubject();//获取当前登录用户名
        if(username==null){
            return Result.error("未找到用户名有误");
        }

        java.util.Date date = new Date();
//对前端传来的blog对象进行加工处理
        if ("write".equals(type)) {
            blog.setCreateTime(date);
            blog.setUpdateTime(date);
            int id=blogService.getIDByUsername(username);
            //通过名字来找到id
            blog.setWriterid(id);

           blogService.WriteBlog(blog);

            return Result.ok("添加成功");
        } else {
            blog.setUpdateTime(date);
            return Result.ok("更新成功");
        }
    }

}
