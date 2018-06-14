package com.wondersgroup.demo.util.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.wondersgroup.demo.entity.LoginUser;

public class LoginSuccessHandler implements AuthenticationSuccessHandler {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		LoginUser user = SecurityUtils.getCurrentUser().getLoginUser();
		request.getSession().setAttribute("errorMsg", null);
		request.getSession().setAttribute("loginErrorCount", null);
		logger.info("用户:"+user.getUsername()+"登录成功");
		logger.info("id : " + user.getId());
		logger.info("userId : " + user.getUserId());
		logger.info("username : " + user.getUsername());
		RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
		redirectStrategy.sendRedirect(request, response, "/index");

	}

}
