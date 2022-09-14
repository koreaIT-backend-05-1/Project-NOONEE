package com.project.noonee.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
	
	@GetMapping({"/", "/main"})
	public String loadMain() {
		return "main";
	}
	
	@GetMapping("/auth/signin")
	public String loadSignin() {
		return "auth/signin";
	}
	
}
