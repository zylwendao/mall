package com.customer.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@Configuration
public class InterceptorAdapterConfig implements WebMvcConfigurer {
    @Resource
    LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry interceptorRegistry) {

        InterceptorRegistration registry = interceptorRegistry.addInterceptor(loginInterceptor);
        registry.addPathPatterns("/**");
//        registry.excludePathPatterns("/index/login");
    }

}