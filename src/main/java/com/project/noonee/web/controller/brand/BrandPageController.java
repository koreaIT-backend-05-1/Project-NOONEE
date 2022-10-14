package com.project.noonee.web.controller.brand;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BrandPageController {
	
	@GetMapping("/brandpage1")
	public String page1() {
		return "brand/brandPage01";
	}
	
	@GetMapping("/brandpage2")
	public String page2() {
		return "brand/brandPage04";
	}
	
	@GetMapping("/shopinfo")
	public String shopInfo() {
		return "brand/shop-info";
	}
}
