package com.spring.blog.controller;


import com.spring.blog.Service.Impliment.LoginServiceImpl;

import com.spring.blog.model.entity.Usermodel.Users;
import com.spring.blog.model.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
    public class UserLogin_controller {
        @Autowired
        private LoginServiceImpl loginService;

    }

