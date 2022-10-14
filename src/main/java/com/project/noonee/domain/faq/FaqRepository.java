package com.project.noonee.domain.faq;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FaqRepository {
	public List<Faq> getFaqList(Map<String, Object> map) throws Exception;
}
