package com.wondersgroup.demo.util.security;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.wondersgroup.util.ValidateCode;

@Controller
public class loginController {
	
	@GetMapping(value = "/login")
	public String login() {
		return "login";
	}
	@GetMapping(value = "/index")
	public String index() {
		return "index";
	}
	
	@GetMapping("/login/ValidateCode.jpg")  
    public void createCode(HttpServletRequest request, HttpServletResponse response) throws IOException {  
        // 通知浏览器不要缓存  
        response.setHeader("Expires", "-1");  
        response.setHeader("Cache-Control", "no-cache");  
        response.setHeader("Pragma", "-1");  
        HttpSession session = request.getSession(false);  
        ValidateCode vCode = new ValidateCode(120,40,5,100);  
        // 将验证码输入到session中，用来验证  
        // 输出打web页面 
        session.setAttribute("session_validateCode", vCode.getCode());  
        session.setAttribute("session_validateCodeTime",new Date().getTime());  
        vCode.write(response.getOutputStream());  
    } 
}
