package com.spring.blog.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//跨域的设置


@Configuration
public class CorsConfig implements WebMvcConfigurer {
        @Override
        public void addCorsMappings(CorsRegistry registry) {
            registry.addMapping("/**")
                    .allowedOrigins("*")
                    .allowedHeaders("*")
                    .allowedMethods("GET", "POST", "PUT", "DELETE", "HEAD", "OPTIONS")
                    .allowCredentials(false)
                    .maxAge(3600);
        }
    }

