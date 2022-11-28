package com.project.noonee.web.controller.inquiry;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/inquiry")
public class InquiryController {
	
	@GetMapping("/insert")
	public String inquiryInsert() {
		return "inquiry/writing";
	}
	
	@GetMapping("/list")
	public String getinquiryList() {
		return "inquiry/inquiry-list";
	}
	
	@GetMapping("/faq")
	public String loadFaq() {
		return "bulletinboard/faq";
	}
	
	@GetMapping("/detail/{inquiryCode}")
	public String loadInquiryDetail() {
		return "inquiry/inquiry-details";
	}
	
	@GetMapping("/test")
	public String test() {
		return "auth/test";
	}
}

 
