package com.project.noonee.web.controller.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.noonee.service.inquiry.faqService;
import com.project.noonee.web.dto.CMRespDto;
import com.project.noonee.web.dto.faq.GetFaqListResponseDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/noonee")
@RequiredArgsConstructor
public class FaqRestController {
	
	private final faqService faqService;
	
	@GetMapping("/faq")
	public ResponseEntity<?> getFaqList(@RequestParam String searchValue) {
		List<GetFaqListResponseDto> list = null;
		
		try {
			list = faqService.getFaqList(searchValue);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(new CMRespDto<>(-1, "failed", list));
		}
		
		return ResponseEntity.ok(new CMRespDto<>(1, "success", list));
	}
}
