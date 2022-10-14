package com.project.noonee.domain.inquiry;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.project.noonee.web.dto.inquiry.GetInquiryListRespDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Inquiry {

	private int user_code;
	private int inquiry_code;
	private int member_code;
	private String inquiry_title;
	private String user_name;
	private String inquiry_password;
	private String inquiry_content;
	private int inquiry_count;
	private LocalDateTime create_date;
	private int total_inquiry_count;
	
	public GetInquiryListRespDto listDto() {
		return GetInquiryListRespDto.builder()
				.inquiryCode(inquiry_code)
				.inquiryTitle(inquiry_title)
				.username(user_name)
				.createDate(create_date.format(DateTimeFormatter.ofPattern("yyyy-MM")))
				.inquiryCount(inquiry_count)
				.totalinquiryCount(total_inquiry_count)
				.build();
	}
}
