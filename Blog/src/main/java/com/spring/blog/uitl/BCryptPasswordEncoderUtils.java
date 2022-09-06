package com.spring.blog.uitl;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptPasswordEncoderUtils {

    private static BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    public static String encodePassword(CharSequence str){
        return bCryptPasswordEncoder.encode(str);
    }
//    public static boolean match(CharSequence str){
//        return bCryptPasswordEncoder.matches(str);
//    }

@Test
    public void a(){
        String password = "1234";
        String pwd = encodePassword(password);
        System.out.println(pwd);
    }
}