package com.project.noonee.service.inquiry;

import java.util.List;

import com.project.noonee.web.dto.inquiry.AddInquiryReqDto;
import com.project.noonee.web.dto.inquiry.GetInquiryListRespDto;
import com.project.noonee.web.dto.inquiry.GetInquiryRepDto;

public interface InquiryService {

	public int addInquiry(AddInquiryReqDto addInquiryReqDto) throws Exception;
	public List<GetInquiryListRespDto> getInquiryList(int page, String searchValue) throws Exception;
	public GetInquiryRepDto getInquiry(int inquiryCode, String searchValue) throws Exception;
}
