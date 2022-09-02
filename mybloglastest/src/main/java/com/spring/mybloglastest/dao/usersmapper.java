package com.spring.mybloglastest.dao;

import com.spring.mybloglastest.model.users;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @Description: 用户持久层接口
 */

@Mapper
@Repository

public interface usersmapper {
    users selectByusername(String username);


}
