package com.project.noonee.web.controller.product;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/product")
public class ProductController {
	
	@GetMapping("/bridal/{categoryCode}")
	public String bridal() {
		return "product/product-list";
	}
	
	@GetMapping("/collection/{collectionCode}")
	public String collection() {
		return "product/product-list";
	}
	
	@GetMapping("/detail/{productCode}")
	public String details() {
		return "product/product-details";
	}
}
