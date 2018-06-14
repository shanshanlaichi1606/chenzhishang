package com.wondersgroup.demo.util.annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class Interceptor extends WebMvcConfigurerAdapter {
	@Autowired
	private AuthenticationInterceptor authenticationInterceptor;// 自己定义的拦截器类

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(authenticationInterceptor);
	}
}
