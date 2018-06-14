package com.wondersgroup.euraka.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wondersgroup.euraka.service.TestService;

@RestController
public class TestController {

	@Autowired
	private TestService testService;

	@GetMapping(value = "/findPort")
	public String find(HttpServletRequest request) {
		String s = testService.find();
		return s;
	}
}
