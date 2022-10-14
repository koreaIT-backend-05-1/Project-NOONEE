package com.project.noonee.domain.inquiry;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface InquiryRepository {

	public int saveInquiry(Inquiry Inquiry) throws Exception;
	public List<Inquiry> getInquiryList(Map<String, Object> map) throws Exception;
	public List<Inquiry> getInquiry(Map<String, Object> map) throws Exception;
	public int count(Map<String, Object> map) throws Exception;
	
}
