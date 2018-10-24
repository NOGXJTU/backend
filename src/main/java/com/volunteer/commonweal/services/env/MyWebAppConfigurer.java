package com.volunteer.commonweal.services.env;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/*
CORS 解决跨域请求 与CORSFilter二者存在一个就好
 */

//@Configuration
//public class MyWebAppConfigurer extends WebMvcConfigurerAdapter {
//
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/wechat/**")
//                .allowedOrigins("*")
//                .allowedMethods("GET", "POST", "DELETE")
//                .allowCredentials(true).maxAge(3600);
//    }
//}