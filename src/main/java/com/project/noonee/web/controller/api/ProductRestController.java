package com.project.noonee.web.controller.api;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.noonee.service.product.ProductService;
import com.project.noonee.web.dto.CMRespDto;
import com.project.noonee.web.dto.product.ProductRespDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductRestController {
	
	private final ProductService productService;

	@GetMapping("/bridal/{categoryCode}")
	public ResponseEntity<?> getCategoryList(@PathVariable int categoryCode) {
		List<ProductRespDto> categoryList = null;
		
		try {
			categoryList = productService.getCategoryList(categoryCode);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(new CMRespDto<>(-1, "fail", categoryList));
		}
		return ResponseEntity.ok(new CMRespDto<>(1, "success", categoryList));
	}
	
	@GetMapping("/collection/{collectionCode}")
	public ResponseEntity<?> getCollectionList(@PathVariable int collectionCode) {
		List<ProductRespDto> collectionList = null;
		
		try {
			collectionList = productService.getCollectionList(collectionCode);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(new CMRespDto<>(-1, "fail", collectionList));
		}
		return ResponseEntity.ok(new CMRespDto<>(1, "success", collectionList));
	}
	
	@GetMapping("/{productCode}")
	public ResponseEntity<?> getProductDetails(@PathVariable int productCode) {
		ProductRespDto productRespDto = null;
		
		try {
			productRespDto = productService.getProduct(productCode);
			if(productRespDto == null) {
				return ResponseEntity.badRequest().body(new CMRespDto<>(-1, "request fail", null));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(new CMRespDto<>(-1, "db error", null));
		}
		
		return ResponseEntity.ok(new CMRespDto<>(1, "success", productRespDto));
	}
	
	@GetMapping("/loadmain")
	public ResponseEntity<?> getList() {
		List<ProductRespDto> list = null;
		
		try {
			list = productService.getList();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(new CMRespDto<>(-1, "fail", list));
		}
		
		return ResponseEntity.ok(new CMRespDto<>(1, "success", list));
	}
	
	@GetMapping("/test")
	public ResponseEntity<?> test(HttpServletRequest request, HttpServletResponse response) {
		
//		ObjectMapper objectMapper = new ObjectMapper();
//		request.getCookies(); 쿠키 가져옴
		
//		Cookie cookie = new Cookie("key", objectMapper.writeValueAsString()); 쿠키에 key value를 넣음
		
//		response.addCookie(cookie); response에 쿠키를 추가
		
		return ResponseEntity.ok(new CMRespDto<>(1, "success", null));
	}
	
	
	
}
