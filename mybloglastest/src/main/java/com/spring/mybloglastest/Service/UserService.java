package com.spring.mybloglastest.Service;

import com.spring.mybloglastest.model.users;

public interface UserService {

    users findUserByUsernameAndPassword(String username, String password);

//    users findUserById(Long id);
}
