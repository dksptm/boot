package com.yedam.app.common.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping("/")
	public String homePage() {
		return "home";
		// view resolve => classpath:/templates/home.html
	}
}
