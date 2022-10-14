package com.project.noonee.web.dto.inquiry;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class GetInquiryRepDto {
	private int inquiryCode;
	private String username;
	private String inquiryTitle;
	private String inquiryContent;
	private String createDate;
	private int inquiryCount;
	private int totalNoticeCoint;
}
