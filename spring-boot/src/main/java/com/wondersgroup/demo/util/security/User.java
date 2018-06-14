package com.wondersgroup.demo.util.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.wondersgroup.demo.entity.LoginUser;

public class User implements UserDetails {
	private static final long serialVersionUID = 1L;
	
	private LoginUser loginUser;
	private Collection<? extends GrantedAuthority> roles;

	public User(LoginUser user, Collection<? extends GrantedAuthority> roles) {
		this.loginUser = user;
		this.roles = roles;
	}

	public LoginUser getLoginUser() {
		return loginUser;
	}

	public void setLoginUser(LoginUser loginUser) {
		this.loginUser = loginUser;
	}


	public void setRoles(Collection<? extends GrantedAuthority> roles) {
		this.roles = roles;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.roles;
	}

	@Override
	public String getPassword() {
		return loginUser.getPassword();
	}

	@Override
	public String getUsername() {
		return loginUser.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
