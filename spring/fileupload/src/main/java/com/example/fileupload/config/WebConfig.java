package com.example.fileupload.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //보안해지폴더등록 : 접근허용
        registry.addResourceHandler("/**")
                .addResourceLocations("file:src/main/resources/static/upload/")
                .addResourceLocations("file:///D:/temp/");   //외부 드라이버는 ///로 인식됨
    }
}
