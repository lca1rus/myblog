package com.spring.blog.Service;

import com.spring.blog.model.entity.Usermodel.Users;

public interface LoginUserService {


        Users findUserByUsernameAndPassword(String username, String password);

//    users findUserById(Long id);
    }

