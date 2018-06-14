package com.wondersgroup.euraka.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("EUREKA-CLIENT")
public interface TestService {
	
	@GetMapping(value = "/find")
	String find();
}
