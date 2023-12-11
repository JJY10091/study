package com.example.multiple.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //static까지는 경로를 자동으로 잡아줄게
        registry.addResourceHandler("/**")
                .addResourceLocations("file:src/main/resources/static/")
                .addResourceLocations("file:src/main/resources/static/upload/");
    }
}
