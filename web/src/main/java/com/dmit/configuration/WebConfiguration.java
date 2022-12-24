package com.dmit.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.dmit.controller")
public class WebConfiguration implements WebMvcConfigurer {
    static final long MAXIMUM_FILE_SIZE = 1_000_000;

    @Bean
    public ModelMapper getMapper() {
        return new ModelMapper();
    }

    @Bean
    public ViewResolver internalResourceViewResolver() {
        return new InternalResourceViewResolver("/jsp/", ".jsp");
    }

    @Bean(name = "multipartResolver")
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();

        multipartResolver.setMaxUploadSize(MAXIMUM_FILE_SIZE);

        return multipartResolver;
    }

}
