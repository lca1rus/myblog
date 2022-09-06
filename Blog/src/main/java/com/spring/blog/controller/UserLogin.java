package com.spring.blog.controller;


import com.spring.blog.Service.Impliment.LoginService;
import com.spring.blog.model.UserLoginMsg;
import com.spring.blog.model.Users;
import com.spring.blog.model.vo.Result;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
    public class UserLogin {
        @Autowired
        private LoginService loginService;


        @RequestMapping("/hello")
    public String hello(){
            return "hello";
        }
    @PostMapping("/login")
    public Result login(@RequestBody UserLoginMsg userLoginMsg) {
        Users user = loginService.findUserByUsernameAndPassword
                (userLoginMsg.getUsername(), userLoginMsg.getPassword());
       // System.out.println(user.getUsername());
        if(user!=null) {
         //   System.out.println(user.getUsername());
           // System.out.println(user.getPassword());
            System.out.println("成功");
        }
            Result result = Result.ok("成功", Result.ok("22", Result.ok("22", null)));

        return result;

    }

    }

