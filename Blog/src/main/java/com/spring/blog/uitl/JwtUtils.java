package com.spring.blog.uitl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;


import java.util.Date;

public class JwtUtils {

    private static final int expireTime = 3600;

    private static final String secretKey = "fagaghagnkafeawftest";

    public static String createjwt(Authentication authResult){//生成jwt密文
        String jwt = Jwts.builder()
                .setSubject(authResult.getName())
                //用户名
                .setExpiration(new Date(System.currentTimeMillis() + expireTime * 1000))
                //每次登录与重新登录之间的密码，会有过期时间
                .signWith(SignatureAlgorithm.HS512, secretKey)
                //添加密文签名
                .compact();

        return jwt;
    }
    public static Claims jwtparser(String  jwtToken){//解析密文
        Claims claims=Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(jwtToken.replace("Bearer", ""))
                .getBody();
        return claims;
    }
}
