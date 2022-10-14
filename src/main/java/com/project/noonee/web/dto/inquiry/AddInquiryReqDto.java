package com.project.noonee.web.dto.inquiry;

import com.project.noonee.domain.inquiry.Inquiry;

import lombok.Data;

@Data
public class AddInquiryReqDto {
	
//	private String inquiryTitle;
	private int inquiryCode;
	private int userCode;
	private String questionTitle;
	private String username;
	private String inquiryPassword;
	private String textareaValue;
	
	public Inquiry inquiryEntity() {
		return Inquiry.builder()
				.user_name(username)
				.inquiry_password(inquiryPassword == null ? "1234" : inquiryPassword)
				.inquiry_content(textareaValue)
				.build();
	}
}

