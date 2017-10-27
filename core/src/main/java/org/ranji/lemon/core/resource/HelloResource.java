package org.ranji.lemon.core.resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloResource {
	
	@RequestMapping("/hello")
	public String hello(){
		return "hello world";
	}
}
