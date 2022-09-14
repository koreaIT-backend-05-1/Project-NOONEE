package com.project.noonee.web.controller.faq;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/noonee")
public class FaqController {
	
	@GetMapping("/faq")
	public String loadFaq() {
		return "bulletinboard/FAQ";
	}
}
 