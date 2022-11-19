package com.spring.blog.Service;

import com.spring.blog.model.entity.Usermodel.Users;

import java.util.List;

/**
 * @author：lcarus
 * @date： 2022/11/18 16:06
 * @Description:
 */
public interface UserService {

    List<Users> GetUsersInfo();

    void DeleteById(Integer id);

    List<Users> FindUserByName(String name);
}
