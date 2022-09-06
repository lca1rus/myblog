package com.spring.blog.dao;


import com.spring.blog.model.Users;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @Description: 用户持久层接口
 */

@Mapper
@Repository

public interface usersmapper {
    Users selectByusername(String username);


}
