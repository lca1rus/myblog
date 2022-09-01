package com.spring.mybloglastest.controller;

import com.spring.mybloglastest.model.users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


    @Controller
    public class log {
        @Autowired
        private com.spring.mybloglastest.sever.implim.impl impl;

        @PostMapping(value = "/login")
        public String login(@RequestParam( "username")String username, @RequestParam("password")String password){
            users users1=impl.selectByusername(username);
            try {
                if (users1 != null && users1.getUserpassword().equals(password)) {
                    System.out.println("yes");
                }
                else {
                    return "error";

                }

            }
            catch (NullPointerException a){

                a.printStackTrace();
            }
            return username;
        }
    }

