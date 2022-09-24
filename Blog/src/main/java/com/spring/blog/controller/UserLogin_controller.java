package com.spring.blog.controller;


import com.spring.blog.Service.Impliment.LoginServiceimpl;
import com.spring.blog.model.entity.Usermodel.Users;
import com.spring.blog.model.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
    public class UserLogin_controller {
        @Autowired
        private LoginServiceimpl loginService;


        @RequestMapping("/hello")
    public String hello(){
            return "hello";
        }

    @PostMapping("/login")
    public Result login(@RequestBody Users userLoginMsg) {
        Users user = loginService.findUserByUsernameAndPassword
                (userLoginMsg.getUsername(), userLoginMsg.getPassword());
       // System.out.println(user.getUsername());

         //   System.out.println(user.getUsername());
           // System.out.println(user.getPassword());

            System.out.println("登录成功");

            Result result = Result.ok("成功", Result.ok("22", Result.ok("22", null)));

        return result;

    }

    }

