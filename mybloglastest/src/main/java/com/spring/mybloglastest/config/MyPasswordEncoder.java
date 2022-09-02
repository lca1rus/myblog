package com.spring.mybloglastest.config;

import com.spring.mybloglastest.uitl.MD5Utils;
import org.springframework.security.crypto.password.PasswordEncoder;

public class MyPasswordEncoder implements PasswordEncoder {


    public String encode(CharSequence rawPassword) {
        return MD5Utils.getMD5(rawPassword);
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return encodedPassword.equals(MD5Utils.getMD5(rawPassword));
    }


}
