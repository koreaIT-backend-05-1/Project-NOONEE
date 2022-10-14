package com.project.noonee.service.inquiry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.noonee.domain.faq.FaqRepository;
import com.project.noonee.web.dto.faq.GetFaqListResponseDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class faqServiceImpl implements faqService {
	
	private final FaqRepository faqRepository;
	
	@Override
	public List<GetFaqListResponseDto> getFaqList(@RequestParam String searchValue) throws Exception {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("search_value", searchValue == null ? "" : searchValue);
		
		List<GetFaqListResponseDto> list = new ArrayList<GetFaqListResponseDto>();
		
		faqRepository.getFaqList(map).forEach(faq -> {
			list.add(faq.toListDto());
		});
		
		return list;
	}
	
}
