package com.spring.blog.Config;


import com.fasterxml.jackson.databind.ObjectMapper;

import com.spring.blog.model.Users;
import com.spring.blog.model.vo.Result;
import com.spring.blog.uitl.JwtUtils;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
//登录拦截和成功后的赋权token


public class JwtLoginFilter extends AbstractAuthenticationProcessingFilter {

    protected JwtLoginFilter(String defaultFilterProcessesUrl, AuthenticationManager authenticationManager) {
        super(new AntPathRequestMatcher(defaultFilterProcessesUrl));
        setAuthenticationManager(authenticationManager);//需要setAuthenticationManager
    }


    /*
    ObjectMapper 通过 writeValue 系列方法 将 java 对 象序列化 为 json，
    并 将 json 存 储成不同的格式，String（writeValueAsString），
    Byte Array（writeValueAsString），Writer， File，OutStream 和 DataOutput。

    ObjectMapper 通过 readValue 系列方法从不同的数据源像
    String ， Byte Array， Reader，File，URL， InputStream 将 json 反序列化为 java 对象。
     */
    @Override
//   　　attemptAuthentication（）：这个方法内部是用来获取 表单的 用户和密码的 ，并判断用户名和密码是使用默认的还是我们自定义的
//
//　　　　successfulAuthentication（）：认证成功后 调用的方法
//
//　　　　unsuccessfulAuthentication（）：认证失败后 调用的方法
    //　如果我们对 这3个方法里面的不满意，可通过继承UserNamePasswordAuthenticationFilter来重写。


    /*
    　一般是调用attemptAuthentication（）拿到用户名和密码，如果是自定义用户名和密码的，
    后面就会调用 UserDatailsService 的 一个方法，因此我们需要实现该接口并重写该方法，
    该方法会返回一个实体类对象，我们的指定或查询出来的用户名和密码要封装在这里面，
    后面会对比这个实体类里的用户名和密码和 页面传过来 的进行对比。
     */
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException {

        try {
            if (!"POST".equals(request.getMethod())) {//前端提交的请求必须是post请求不是就会抛出异常
                throw new Exception();
            }
            Users user = new ObjectMapper().readValue(request.getInputStream(), Users.class);//得到输入的用户


            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=
                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());//封装

            Authentication authentication=
                    getAuthenticationManager().authenticate(usernamePasswordAuthenticationToken);
            //会调用UserDetailsService中的loadUserByUsername进行验证
            System.out.println(authentication);
            return authentication;

        } catch (Exception exception) {
         //   System.out.println(exception.getMessage());
            response.setContentType("application/json;charset=utf-8");
            Result result = Result.create(400, "非法请求");
            PrintWriter out = response.getWriter();
            out.write(new ObjectMapper().writeValueAsString(result));
            out.flush();
            out.close();
        }
        return null;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authResult)
            throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");

        String jwt= JwtUtils.createjwt(authResult);//生成密文

        Users user = (Users) authResult.getPrincipal();
        //接验证成功后收到的Authentication转化为Users
      //  System.out.println("user是:"+user.getUsername());
        System.out.println("token是："+jwt);
        user.setPassword(null);
        Map<String, Object> map = new HashMap<>();
        map.put("user", user);
        map.put("token", jwt);
        Result result = Result.ok("登录成功", map);
        PrintWriter out = response.getWriter();
        out.write(new ObjectMapper().writeValueAsString(result));//将成功的结果传给前端，并且传入map中的user与token
        out.flush();
        out.close();
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                              AuthenticationException exception)
            throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        Result result = Result.create(401, exception.getMessage());
        //登录不成功时，会抛出对应的异常
        //可以查看 AuthenticationException 异常的继承关系，源码中有每种异常的解释
        if (exception instanceof LockedException) {
            result.setMsg("账号被锁定，请联系管理员！");
        } else if (exception instanceof CredentialsExpiredException) {
            result.setMsg("密码过期，请联系管理员！");
        } else if (exception instanceof AccountExpiredException) {
            result.setMsg("账号过期，请联系管理员！");
        } else if (exception instanceof DisabledException) {
            result.setMsg("账号被禁用，请联系管理员！");
        } else if (exception instanceof BadCredentialsException) {
            result.setMsg("用户名或密码错误！");
        }
        PrintWriter out = response.getWriter();
        out.write(new ObjectMapper().writeValueAsString(result));
        out.flush();
        out.close();
    }
}
