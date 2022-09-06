package com.spring.blog.uitl;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.DigestUtils;

public class MD5Utils {
    public static String getMD5(CharSequence str) {

        return DigestUtils.md5DigestAsHex(str.toString().getBytes());
    }
//
    private static final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

//密码检验
//raw 为输入的密码，eccode为加密过后的密码
    public static boolean matchBC(CharSequence rawPassword, String encodedPassword) {
        return bCryptPasswordEncoder.matches(rawPassword, encodedPassword);
    }
    public static void main(String[] args) {

    }
}