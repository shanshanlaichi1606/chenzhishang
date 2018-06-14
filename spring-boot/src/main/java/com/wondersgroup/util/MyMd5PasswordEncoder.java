package com.wondersgroup.util;

import org.springframework.dao.DataAccessException;

public class MyMd5PasswordEncoder {

	public String encodePassword(String origPwd, Object salt) throws DataAccessException {
//		return EncodeUtils.encodePassword(origPwd);
		return origPwd;
	}

	public boolean isPasswordValid(String encPwd, String origPwd, Object salt) throws DataAccessException {
		return encPwd.equals(encodePassword(origPwd, salt));
	}
}
