package com.wondersgroup.demo.util.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

public class LoginFailHandler implements AuthenticationFailureHandler {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.setAttribute("errorMsg", exception);
			Integer loginErrorCount = (Integer)session.getAttribute("loginErrorCount");
			//登录失败次数统计
			if(loginErrorCount == null || loginErrorCount <0){
				session.setAttribute("loginErrorCount", 1);
			}else{
				session.setAttribute("loginErrorCount", loginErrorCount+1);
			}
		}
		logger.info("登录失败！");
		logger.info(exception.getMessage());
		RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
		redirectStrategy.sendRedirect(request, response, "/login?error=1");
	}

}
