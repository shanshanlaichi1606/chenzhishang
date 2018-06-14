package com.wondersgroup.demo.util.security;

import java.io.File;

import javax.servlet.ServletContext;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.wondersgroup.demo.entity.LoginUser;

public class SecurityUtils {

	public static User getCurrentUser() {
		SecurityContext ctx = SecurityContextHolder.getContext();
		if (ctx != null) {
			Authentication authentication = ctx.getAuthentication();
			if (authentication != null) {
				Object principal = authentication.getPrincipal();
				if (principal != null) {
					return (User) principal;
				}
			}
		}
		return null;
	}

	public static String getCurrentUserPwd() {
		User user = getCurrentUser();
		if (user != null) {
			LoginUser loginUser = user.getLoginUser();
			if (loginUser != null) {
				return loginUser.getPassword();
			}
		}
		return null;
	}

	public static String getCurrentUserId() {
		User user = getCurrentUser();
		if (user != null) {
			return user.getLoginUser().getUserId();
		}
		return null;
	}

	public static Integer getCurrentId() {
		User user = getCurrentUser();
		if (user != null) {
			return user.getLoginUser().getId();
		}
		return null;
	}

	public static String getCurrentUsername() {
		User user = getCurrentUser();
		if (user != null) {
			return user.getUsername();
		}
		return null;
	}

	public static String getPath(String fileName) {
		String contextPath = "";
		WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
		ServletContext servletContext = webApplicationContext.getServletContext();
		contextPath = servletContext.getRealPath("/");
		contextPath += fileName;
		File attachFile = new File(contextPath);
		if (!attachFile.exists()) {
			attachFile.mkdirs();
		}
		return contextPath;
	}

}
