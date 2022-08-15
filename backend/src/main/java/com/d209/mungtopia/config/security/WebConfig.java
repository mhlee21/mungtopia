package com.d209.mungtopia.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
//    public void addCorsMappings(CorsRegistry registry){
//        registry.addMapping("/**")
//                .allowedOrigins("http://localhost:3000", "https://i7d209.p.ssafy.io")
//                .allowedMethods("GET","POST","PUT","DELETE","OPTIONS")
//                .allowCredentials(true)
//                .allowedHeaders("*")
//                .maxAge(3000);
//    }

    @Bean
    public MultipartResolver multipartResolver(){
        // commonmultipartResolve 작동 X
        StandardServletMultipartResolver multipartResolver = new StandardServletMultipartResolver();
//        multipartResolver.("UTF-8");
//        multipartResolver.setMaxUploadSize(5 * 1024 * 1024); // 5MB - 나중에 조정
        return multipartResolver;
    }
}
