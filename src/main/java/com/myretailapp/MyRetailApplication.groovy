package com.myretailapp

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.boot.web.servlet.ServletRegistrationBean
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer
import org.springframework.context.annotation.Bean
import org.springframework.web.client.RestTemplate
import org.springframework.web.servlet.DispatcherServlet

@SpringBootApplication
class MyRetailApplication extends SpringBootServletInitializer {
    static void main(String[] args) {
        new MyRetailApplication().configure(new SpringApplicationBuilder(MyRetailApplication.class)).run(args)
    }

    @Bean
    ServletRegistrationBean dispatcherRegistration(DispatcherServlet dispatcherServlet) {
        new ServletRegistrationBean(dispatcherServlet, "/api/*")
    }

    @Bean
    RestTemplate restTemplate(RestTemplateBuilder builder) {
        RestTemplate restTemplate = builder.build()
        restTemplate
    }
}
