package com.yedam.app.security.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SampleController {

	@GetMapping("all")
	public String exAll() {
		return "security/all";
	}
	
	@GetMapping("admin")
	public String exAdmin() {
		return "security/admin";
	}
	
	@GetMapping("user")
	public String exUser() {
		return "security/user";
	}
}
