package com.xu.springbootdemo.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Component
public class WebConfig extends WebMvcConfigurationSupport  {

    ///自定义一个拦截器，添加到配置中
    @Override
    public void addInterceptors(InterceptorRegistry registry){

             ///自定义一个拦截器，添加到配置中
            registry.addInterceptor(new MyInterceptor()).addPathPatterns("/**");
    }
    //自定义资源映射路径
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //定义了一个虚拟路径/file/** 映射到本地目录下，访问本地文件，file:C:/testDir 中file指的的本地路径
        registry.addResourceHandler("/file/**").addResourceLocations("file:C:/testDir/");
        //一个虚拟路径，映射多个本地目录
        registry.addResourceHandler("/img/**").addResourceLocations("file:C:/testDir/").addResourceLocations("file:C:/img/");

        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }
}
