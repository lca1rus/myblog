package com.spring.blog.Config;


import com.spring.blog.uitl.BCryptPasswordEncoderUtils;
import com.spring.blog.uitl.MD5Utils;
import org.junit.Test;
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

@Test
public void  test(){
    String encode1=encode("1234");
    String encode2=encode("1234");
    System.out.println(matches("1234","$2a$10$m8FlsQbIzxqpAfpoTxwdcea2ewQkJkT03Juo5LqmVMDBo52pxT8uO"));
    System.out.println(encode1);
    System.out.println(encode2);


}


}