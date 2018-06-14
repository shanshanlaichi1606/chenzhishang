package com.wondersgroup.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.wondersgroup.demo.mapper")
public class SpringWebApplication extends SpringBootServletInitializer {
     
    @Override
    protected SpringApplicationBuilder configure(
            SpringApplicationBuilder application) {
        return application.sources(SpringWebApplication.class);
    }
 
     
    public static void main(String[] args) {
        SpringApplication.run(SpringWebApplication.class, args);
    }
}