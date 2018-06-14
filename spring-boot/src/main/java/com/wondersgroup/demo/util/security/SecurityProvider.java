package com.wondersgroup.demo.util.security;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.wondersgroup.demo.entity.LoginUser;
import com.wondersgroup.demo.service.LoginUserService;

@Component
public class SecurityProvider implements AuthenticationProvider {
	@Resource
	private LoginUserService loginUserService;
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = authentication.getName();
		String password = (String) authentication.getCredentials();
        CustomWebAuthenticationDetails details = (CustomWebAuthenticationDetails) authentication.getDetails();
        int loginErrorCount = details.getLoginErrorCount();
        //登录失败3次以上开启验证码
        if(loginErrorCount>3){
            String validateCode = details.getValidateCode();
            String session_validateCode = details.getSession_validateCode();
            long session_validateCodeTime = details.getSession_validateCodeTime();
        	if(StringUtils.isEmpty(validateCode)){
        		throw new BadCredentialsException("验证码不能为空");
        	}
        	if((new Date().getTime()-session_validateCodeTime)>=60000){
        		throw new BadCredentialsException("验证码已经过期");
        	}
        	if(!session_validateCode.equalsIgnoreCase(validateCode)){
        		throw new BadCredentialsException("验证码错误!");
        	}
        }
		User user = null;
		LoginUser loginUser = loginUserService.selectByExampleUsername(username);
		if (loginUser != null) {
			List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
			list.add(new SimpleGrantedAuthority(loginUser.getUsername()));
			user = new User(loginUser, list);
		} else {
			throw new BadCredentialsException("用户名不存在");
		}
		if (!user.getPassword().equals(password)) {
			throw new BadCredentialsException("密码错误");
		}
		
		return new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities());
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return true;
	}
}
