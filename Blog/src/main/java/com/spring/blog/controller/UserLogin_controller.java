package com.spring.blog.controller;


import com.spring.blog.Service.Impliment.LoginServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
    public class UserLogin_controller {
        @Autowired
        private LoginServiceImpl loginService;


        @RequestMapping("/hello")
    public String hello(){
            return "hello";
        }


//@PostMapping("/login")
//    public Result login(@RequestBody Users usersLogMsg){
//            Users users=loginService.findUserByUsernameAndPassword(usersLogMsg.getUsername(), usersLogMsg.getPassword());
//  Result result=Result.ok("成功1",Result.ok("22",Result.ok("22",null)));
//  return result;
//}


    }

