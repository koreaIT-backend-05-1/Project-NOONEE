package com.project.noonee.web.dto.inquiry;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class GetInquiryListRespDto {
	private int inquiryCode;
	private String inquiryTitle;
	private String username;
	private String createDate;
	private int inquiryCount;
	private int totalinquiryCount;
	
}
