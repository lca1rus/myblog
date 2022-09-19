package com.spring.blog.Config;


import com.spring.blog.Service.Impliment.LoginService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    LoginService loginService;
    @Autowired
    MyAuthenticationEntryPoint myAuthenticationEntryPoint;
    @Autowired
    MyPasswordEncoder myPasswordEncoder;


//可以直接注入
//  @Bean
//    public PasswordEncoder passwordEncoder(){
//        return new MyPasswordEncoder();
//    }

    //（身份验证管理生成器）
    /*
    void configure(AuthenticationManagerBuilder auth) 用来配置认证管理器AuthenticationManager。
    说白了就是所有 UserDetails 相关的它都管，包含 PasswordEncoder 密码等。
    如果你不清楚可以通过 Spring Security 中的 UserDetail 进行了解。
    本文对 AuthenticationManager 不做具体分析讲解，后面会有专门的文章来讲这个东西 。

     */

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(loginService)
                .passwordEncoder(myPasswordEncoder);//配置安全的userDetailsService
    }
// /*
//    void configure(HttpSecurity http) 这个是我们使用最多的，用来配置 HttpSecurity 。
//     HttpSecurity 用于构建一个安全过滤器链 SecurityFilterChain 。
//     SecurityFilterChain 最终被注入核心过滤器 。
//     HttpSecurity 有许多我们需要的配置。我们可以通过它来进行自定义安全访问策略。
//
//    */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                //   禁用 csrf 防御
                .csrf().disable()
                // 开启跨域支持
                .cors().and()
                //基于Token，不创建会话
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                //任何经过JWT验证的请求都通过
                .authorizeRequests()
            //   .mvcMatchers("/user/login").anonymous()//对登录接口允许匿名访问
           //     .antMatchers(HttpMethod.POST,"/user/login").permitAll()
                .anyRequest().authenticated()//除了以上接口都需要认证
                // 自定义JWT过滤器
                //JwtLoginFilter放在token
                //配置登录路径

             ;
            //自定义JWT过滤器,addFilterBefore（生成的自定义的过滤器的进行过滤的登录路径），该过滤器只在"/user/login"生效
        // 需要在重写的JwtLoginFilter中set，authenticationManager，过滤器过滤的位置UsernamePasswordAuthenticationFilter.class，之前）
           http .addFilterBefore(new JwtLoginFilter("/login",authenticationManager()), UsernamePasswordAuthenticationFilter.class)

            .addFilterBefore(new JwtFilter(),UsernamePasswordAuthenticationFilter.class)
            //未登录时，返回json，而不重定向
                    .exceptionHandling().authenticationEntryPoint(myAuthenticationEntryPoint);
//



    }



}