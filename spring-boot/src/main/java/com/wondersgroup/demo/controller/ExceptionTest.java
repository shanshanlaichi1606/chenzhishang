package com.wondersgroup.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wondersgroup.demo.util.exception.ResultException;

@RestController
@RequestMapping("exception")
public class ExceptionTest {
	
	@GetMapping("/error")
	public String error() {
		throw ResultException.returnException("000002");
	}
	
	@GetMapping("/error1")
	public int error1() {
		int i=10/0;
		return i;
	}
	
	@GetMapping("/error2")
	public int error2() {
		throw ResultException.returnExceptionWithPars("000004", "林正康","@林志祥");
	}
}
