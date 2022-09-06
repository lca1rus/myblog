package com.spring.blog.Service;

import com.spring.blog.model.Users;

public interface LoginUserService {


        Users findUserByUsernameAndPassword(String username, String password);

//    users findUserById(Long id);
    }

