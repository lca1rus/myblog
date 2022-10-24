package com.spring.blog.Config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.blog.model.vo.Result;
import com.spring.blog.uitl.JwtUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import io.jsonwebtoken.Claims;
public class JwtFilter extends GenericFilterBean {

//jwt的拦截器，如果没有token认证被拦截
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String jwtToken = request.getHeader("Authorization");

        if (jwtToken != null && !"".equals(jwtToken) && !jwtToken.equals("null")) {//jwtToken不能为null
            try {
                Claims claims = JwtUtils.jwtparser(jwtToken);//解码token

                String username = claims.getSubject();//获取当前登录用户名
//		        List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList((String) claims.get("authorities"));

                //TODO 获得权限信息传入authorities中
                UsernamePasswordAuthenticationToken token =
                        new UsernamePasswordAuthenticationToken(username, null, null);
//通过用户名得到该


                SecurityContextHolder
                        .getContext()
                        .setAuthentication(token);
                System.out.println("jwtFilter成功进行中");

            } catch (Exception e) {
                response.setContentType("application/json;charset=utf-8");
                Result result = Result.create(403, "凭证已失效，请重新登录！");
                PrintWriter out = response.getWriter();
                out.write(new ObjectMapper().writeValueAsString(result));
                out.flush();
                out.close();
            }

        }
else {
            System.out.println("token为空被拦截");
        }

        filterChain.doFilter(request, servletResponse);
        //token为空放行，或者存储 SecurityContextHolder后再放行


    }
}
