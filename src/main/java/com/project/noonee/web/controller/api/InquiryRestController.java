package com.project.noonee.web.controller.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.noonee.service.inquiry.InquiryService;
import com.project.noonee.web.dto.CMRespDto;
import com.project.noonee.web.dto.inquiry.AddInquiryReqDto;
import com.project.noonee.web.dto.inquiry.GetInquiryListRespDto;
import com.project.noonee.web.dto.inquiry.GetInquiryRepDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping ("/api/v1/inquiry")
@RequiredArgsConstructor
public class InquiryRestController {
	
	private final InquiryService inquiryService;
	
	@PostMapping("")
	public ResponseEntity<?> insertinquiry(@RequestBody AddInquiryReqDto addinquiryReqDto ) {
		
		int inquiryCode = 0;
		
		try {
			inquiryCode = inquiryService.addInquiry(addinquiryReqDto) ;
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(new CMRespDto<>(-1, "fail", inquiryCode));
		}
		
		return ResponseEntity.ok(new CMRespDto<>(1, "success", inquiryCode));
	}
	
	@GetMapping("/list/{page}")
	public ResponseEntity<?> getinquiryList(@PathVariable int page, @RequestParam String searchValue) {
		List<GetInquiryListRespDto> listDto = null;
		
		
		try {
			listDto = inquiryService.getInquiryList(page, searchValue);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(new CMRespDto<>(-1, "database error", listDto));
		}
		
		return ResponseEntity.ok(new CMRespDto<>(1, "lookup successful", listDto));
	}
	
	@GetMapping("/{inquiryCode}")
	public ResponseEntity<?> getNotice(@PathVariable int inquiryCode) {
		GetInquiryRepDto getInquiryRepDto = null;
		
		try {
			getInquiryRepDto = inquiryService.getInquiry(inquiryCode, null);
			if(getInquiryRepDto == null) {
				return ResponseEntity.badRequest().body(new CMRespDto<>(-1, "request fail", null));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(new CMRespDto<>(-1, "db error", null));
		}
		
		log.error("{}",getInquiryRepDto);
		
		return ResponseEntity.ok(new CMRespDto<>(1, "success", getInquiryRepDto));
	}
}