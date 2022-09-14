package com.project.noonee.service.auth.faq;

import java.util.List;

import com.project.noonee.web.dto.faq.GetFaqListResponseDto;

public interface faqService {
	public List<GetFaqListResponseDto> getFaqList(String searchValue) throws Exception;
}
