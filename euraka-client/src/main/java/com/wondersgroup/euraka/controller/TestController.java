package com.wondersgroup.euraka.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

	@GetMapping(value = "/find")
	public String find(HttpServletRequest request) {
		String s = "用户" + "     服务端端口：" + request.getLocalPort();
		return s;
	}
}
