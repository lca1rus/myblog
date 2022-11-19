package com.spring.blog.dao;


import com.spring.blog.model.entity.Usermodel.Users;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description: 用户持久层接口
 */

@Mapper
@Repository
public interface usersmapper {
   Users selectByUsername(String username);
    List<Users> GetUsersInfo();
    void DeleteById(Integer id);

    List<Users> FindUserByName(String username);
}
