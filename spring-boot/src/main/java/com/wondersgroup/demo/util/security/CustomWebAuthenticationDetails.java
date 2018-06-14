package com.wondersgroup.demo.util.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.web.authentication.WebAuthenticationDetails;

public class CustomWebAuthenticationDetails extends WebAuthenticationDetails {

	private static final long serialVersionUID = 1L;

	private String validateCode;// 验证码
	private String session_validateCode;// session中的验证吗
	private long session_validateCodeTime;// 验证码刷新时间
	private int loginErrorCount;// 登录失败次数

	public CustomWebAuthenticationDetails(HttpServletRequest request) {
		super(request);
		validateCode = request.getParameter("validateCode");
		HttpSession session = request.getSession(false);
		if (session != null) {
			Object loginErrorCount = session.getAttribute("loginErrorCount");
			if (loginErrorCount != null) {
				this.loginErrorCount = Integer.parseInt(loginErrorCount.toString());
				Object session_validateCode = session.getAttribute("session_validateCode");
				if (session_validateCode != null) {
					this.session_validateCode = session_validateCode.toString();
				}
				Object session_validateCodeTime = session.getAttribute("session_validateCodeTime");
				if (session_validateCodeTime != null) {
					this.session_validateCodeTime = Long.parseLong(session_validateCodeTime.toString());
				}
			}
		}
	}

	public String getValidateCode() {
		return validateCode;
	}

	public String getSession_validateCode() {
		return session_validateCode;
	}

	public long getSession_validateCodeTime() {
		return session_validateCodeTime;
	}

	public int getLoginErrorCount() {
		return loginErrorCount;
	}

}
