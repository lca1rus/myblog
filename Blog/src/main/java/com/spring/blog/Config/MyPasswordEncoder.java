package com.spring.blog.Config;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class MyPasswordEncoder implements PasswordEncoder {

    private static BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    public String encode(CharSequence rawPassword) {//将输入的密码加密
        return bCryptPasswordEncoder.encode(rawPassword);
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {//输入的密码，与数据库后台的加密密码比对
        return bCryptPasswordEncoder.matches(rawPassword,encodedPassword);
    }



}