package com.spring.blog.Config;


import com.spring.blog.Service.Impliment.LoginServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    LoginServiceImpl loginService;
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
Article
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/index/**","/Article/**");
    }//不走过滤器链直接放行

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(loginService)
                .passwordEncoder(myPasswordEncoder)
                ;//配置安全的userDetailsService
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

                .anyRequest().authenticated();//除了以上接口都需要认证
                // 自定义JWT过滤器
                //JwtLoginFilter放在token
                //配置登录路径


            //自定义JWT过滤器,addFilterBefore（生成的自定义的过滤器的进行过滤的登录路径），该过滤器只在"/user/login"生效
        // 需要在重写的JwtLoginFilter中set，authenticationManager，过滤器过滤的位置UsernamePasswordAuthenticationFilter.class，之前）

        http
                .addFilterBefore(new JwtLoginFilter("/login",//设置login为登录页面的，进行登录与验证
                           authenticationManager()), UsernamePasswordAuthenticationFilter.class)

            .addFilterBefore(new JwtFilter(),UsernamePasswordAuthenticationFilter.class)

            //通过过滤器检查出没有带token时跳转在此
                    .exceptionHandling().authenticationEntryPoint(myAuthenticationEntryPoint);


    }
}