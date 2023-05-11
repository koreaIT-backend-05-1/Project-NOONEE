package com.project.noonee.web.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

	@GetMapping("/admin/insert-product")
	public String admin() {
		return "user/admin";
	}
	
	@GetMapping("/admin/list")
	public String adminInquiry() {
		return "user/admin-inquiry-list";
	}
	
	@GetMapping("/admin/product-list")
	public String adminProductList() {
		return "user/admin-product-list";
	}
	
	@GetMapping("/admin/update-product/{productCode}")
	public String adminUpdateProduct() {
		return "user/admin-update-product";
	}
	
	@GetMapping("/admin/user-list")
	public String adminUserList() {
		return "user/admin-user-list";
	}
}
