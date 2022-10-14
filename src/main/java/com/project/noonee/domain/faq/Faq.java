package com.project.noonee.domain.faq;

import com.project.noonee.web.dto.faq.GetFaqListResponseDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Faq {
	private int faq_code;
	private String faq_title;
	private String faq_content;
	
	public GetFaqListResponseDto toListDto() {
		return GetFaqListResponseDto.builder()
				.faqCode(faq_code)
				.faqTitle(faq_title)
				.faqContent(faq_content)
				.build();
	}
}
