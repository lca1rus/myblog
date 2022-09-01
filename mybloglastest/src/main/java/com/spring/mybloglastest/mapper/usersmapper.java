package com.spring.mybloglastest.mapper;
import com.spring.mybloglastest.model.users;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface usersmapper {

    users selectByusername(String username);

}