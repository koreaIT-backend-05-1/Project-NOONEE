package com.project.noonee.web.dto.faq;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class GetFaqListResponseDto {
	private int faqCode;
	private String faqTitle;
	private String faqContent;
}
