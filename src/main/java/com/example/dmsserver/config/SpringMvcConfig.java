package com.example.dmsserver.config;

import com.example.dmsserver.interceptor.LogInterceptor;
import com.example.dmsserver.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@Configuration
public class SpringMvcConfig implements WebMvcConfigurer {

//    @Resource
//    LogInterceptor logInterceptor;

    @Autowired
    LoginInterceptor loginInterceptor;

    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(
                        "/",
                        "/user/login",
                        "/category/list"
                );



//        registry.addInterceptor(logInterceptor)
//                .addPathPatterns("/**");
//                .excludePathPatterns(
//                        "/test/**"
//                );
    }
}
