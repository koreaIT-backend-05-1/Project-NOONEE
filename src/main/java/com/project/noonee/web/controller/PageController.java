package com.project.noonee.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
	
	@GetMapping({"/", "/main"})
	public String loadMain() {
		return "main";
	}
	
	@GetMapping("/auth/main")
	public String loadMain2() {
		return "main";
	}
	
	@GetMapping("/auth/signin")
	public String loadSignin() {
		return "auth/signin";
	}
	
	@GetMapping("/auth/signup")
	public String signup() {
		return "auth/signup";
	}
	
	@GetMapping("/auth/consent")
	public String consent() {
		return "auth/signup-consent";
	}
	
	@GetMapping("/auth/signupdetail")
	public String loadSignup() {
		return "auth/signup-detail";
	}
	
	@GetMapping("/user/mypage")
	public String mypage() {
		return "user/mypage";
	}
	
	
}
