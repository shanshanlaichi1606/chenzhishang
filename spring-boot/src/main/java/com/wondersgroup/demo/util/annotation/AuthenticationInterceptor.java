package com.wondersgroup.demo.util.annotation;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.wondersgroup.demo.entity.LoginUser;
import com.wondersgroup.demo.util.exception.ResultException;
import com.wondersgroup.demo.util.security.SecurityUtils;
@Component
public class AuthenticationInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        // 从方法处理器中获取出要调用的方法
        Method method = handlerMethod.getMethod();
        //判断注解是否存在
        if(!method.isAnnotationPresent(LoginAnnotation.class)){
        	return true;
        }
        // 获取出方法上的自定义注解注解
        LoginAnnotation loginAnnotation = method.getAnnotation(LoginAnnotation.class);
        if (loginAnnotation == null) {
            return true;
        }
        if (loginAnnotation.value().length > 0) {
            // 如果权限配置不为空, 则取出配置值
            String[] values = loginAnnotation.value();
    		LoginUser loginUser = SecurityUtils.getCurrentUser().getLoginUser();
            if (loginUser!=null) {
            	for(String str:values){
            		if(str.equals(loginUser.getUsername())){
            			   return true;
            		}             
            	}
            }
        }
		throw ResultException.returnException("000005");
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

}
