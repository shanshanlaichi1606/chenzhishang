package com.wondersgroup.demo.util.security;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Resource
	private SecurityProvider securityProvider;
	@Resource
	private AuthenticationDetailsSource<HttpServletRequest, WebAuthenticationDetails> authenticationDetailsSource;

	/**
	 * 忽略静态文件
	 */
	@Override
	public void configure(WebSecurity web) {
		web.ignoring().antMatchers("/css/**", "/js/**", "/images/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// 开启请求拦截
		http.authorizeRequests().antMatchers("/index").hasAuthority("admin")
				// 不拦截登录页面 其他所以的请求都拦截
				.antMatchers("/login", "/login/*","/page/*").permitAll().anyRequest().authenticated().and()
				// 指定登录页面为/
				.formLogin().loginPage("/login")
				// 登录请求接口为/login
				.loginProcessingUrl("/check_login").passwordParameter("password").usernameParameter("username")
				// 登录失败
				.failureHandler(new LoginFailHandler())
/*				.failureUrl("/login?error=true")*/
				// 登录成功处理
				.successHandler(new LoginSuccessHandler())
				.authenticationDetailsSource(authenticationDetailsSource)
				.and()
				// 退出登录处理 设置session无效 删除cookie
				.logout().invalidateHttpSession(true).deleteCookies("JSESSIONID").and();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// auth.userDetailsService(userService).passwordEncoder(new
		// MyMd5PasswordEncoder());
		auth.authenticationProvider(securityProvider);
	}
	/*
	 * public AuthenticationProvider authenticationProvider() {
	 * DaoAuthenticationProvider authenticationProvider = new
	 * DaoAuthenticationProvider();
	 * authenticationProvider.setUserDetailsService(userService);
	 * authenticationProvider.setPasswordEncoder(new MyMd5PasswordEncoder());
	 * return authenticationProvider; }
	 */
	
}
