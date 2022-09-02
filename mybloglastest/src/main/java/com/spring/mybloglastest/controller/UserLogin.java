package com.spring.mybloglastest.controller;

import com.spring.mybloglastest.model.users;
import com.spring.mybloglastest.Service.Impliment.UserImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@RestController

    public class UserLogin {
        @Autowired
        private UserImpl impl;

        @RequestMapping("/admin")
        public class LoginController {


            @GetMapping
            public String index() {
                return "123456";
            }
        }
    }

