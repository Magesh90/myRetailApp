package com.myretailapp;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.DispatcherServlet;

@SpringBootApplication
public class MyRetailApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        new MyRetailApplication().configure(new SpringApplicationBuilder(MyRetailApplication.class)).run(args);
    }

    @Bean
    ServletRegistrationBean dispatcherRegistration(DispatcherServlet dispatcherServlet){
        return new ServletRegistrationBean(dispatcherServlet,"/api/*");
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        RestTemplate restTemplate = builder.build();
        return restTemplate;
    }
}
