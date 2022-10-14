package com.project.noonee.service.inquiry;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.project.noonee.domain.inquiry.Inquiry;
import com.project.noonee.domain.inquiry.InquiryRepository;
import com.project.noonee.web.dto.inquiry.AddInquiryReqDto;
import com.project.noonee.web.dto.inquiry.GetInquiryListRespDto;
import com.project.noonee.web.dto.inquiry.GetInquiryRepDto;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class InquiryServiceImpl implements InquiryService{
	
	private final InquiryRepository inquiryRepository;
	
	@Override
	public int addInquiry(AddInquiryReqDto addInquiryReqDto) throws Exception {
		Inquiry inquiry = addInquiryReqDto.inquiryEntity();
		
		for(int i = 0; i < 100; i++) {
			inquiry = Inquiry.builder()
					.user_code(addInquiryReqDto.getUserCode())
					.user_name(addInquiryReqDto.getUsername())
					.inquiry_title(addInquiryReqDto.getQuestionTitle())
					.inquiry_password(addInquiryReqDto.getInquiryPassword())
					.inquiry_content(addInquiryReqDto.getTextareaValue())
					.build();
			
			inquiryRepository.saveInquiry(inquiry);
		}
		
		return inquiry.getInquiry_code();
	}

	@Override
	public List<GetInquiryListRespDto> getInquiryList(int page, String searchValue) throws Exception {
		int index = (page - 1) * 10;
		
		Map<String, Object> listMap = new HashMap<String, Object>();
		
		listMap.put("index", index);
		listMap.put("search_value", searchValue == null ? "" :searchValue);
		
		List<GetInquiryListRespDto> list = new ArrayList<GetInquiryListRespDto>();
		
		inquiryRepository.getInquiryList(listMap).forEach(inquiry -> {
			list.add(inquiry.listDto());
		});
		
		return list;
	}

	@Override
	public GetInquiryRepDto getInquiry(int inquiryCode, String searchValue) throws Exception {
		GetInquiryRepDto getInquiryRepDto = null;
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("inquiry_code", inquiryCode);
		map.put("search_value", searchValue);
		
		inquiryRepository.count(map);
		List<Inquiry> inquiries = inquiryRepository.getInquiry(map);
		if(!inquiries.isEmpty()) {
			Inquiry inquiry = inquiries.get(0);
			
			getInquiryRepDto = GetInquiryRepDto.builder()
					.inquiryCode(inquiry.getInquiry_code())
					.username(inquiry.getUser_name())
					.inquiryTitle(inquiry.getInquiry_title())
					.inquiryContent(inquiry.getInquiry_content())
					.createDate(inquiry.getCreate_date().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
					.inquiryCount(inquiry.getInquiry_count())
					.build();
		}
		
		return getInquiryRepDto;
	}

	

}
