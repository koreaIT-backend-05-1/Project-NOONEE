package com.project.noonee.web.controller.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.noonee.service.auth.UserService;
import com.project.noonee.service.product.ProductService;
import com.project.noonee.web.dto.CMRespDto;
import com.project.noonee.web.dto.auth.UserRespDto;
import com.project.noonee.web.dto.product.AddProductReqDto;
import com.project.noonee.web.dto.product.ProductRespDto;
import com.project.noonee.web.dto.product.UpdateProductReqDto;
import com.project.noonee.web.dto.validationGroups.ValidationSequence;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class AdminRestController {

	private final ProductService productService;
	private final UserService userService;
	
	@PostMapping("/product")
	public ResponseEntity<?> addProduct(@Validated(ValidationSequence.class) AddProductReqDto addProductDto, BindingResult bindingResult) throws Exception {
		
		return ResponseEntity.created(null).body(new CMRespDto<>(1, "Successfully", productService.addProduct(addProductDto)));
	}
	
	@GetMapping("/list/{page}")
	public ResponseEntity<?> adminProductList(@PathVariable int page, @RequestParam String searchFlag) {
		List<ProductRespDto> list = null;
		
		try {
			list = productService.getProductList(page, searchFlag);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(new CMRespDto<>(-1, "failed", list));
		}
//		log.error(">>>>>>>>>>>>>>>{}", list);
		
		
		return ResponseEntity.ok(new CMRespDto<>(1, "success", list));
	}
	
	@GetMapping("/product/{productCode}")
	public ResponseEntity<?> getAdminProductDetails(@PathVariable int productCode) {
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
	
	@GetMapping("/user-list/{page}")
	public ResponseEntity<?> getAdminUserList(@PathVariable int page, @RequestParam String searchFlag) {
		List<UserRespDto> list = null;
		
		try {
			list = userService.getUserList(page, searchFlag);
			if(list == null) {
				return ResponseEntity.badRequest().body(new CMRespDto<>(-1, "request fail", null));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(new CMRespDto<>(-1, "db error", null));
		}
		
		return ResponseEntity.ok(new CMRespDto<>(1, "success", list));
	}
	
	@PutMapping("/update/{productCode}")
	public ResponseEntity<?> updateProduct(UpdateProductReqDto updateProductReqDto) {
		
		boolean status = false;
		
		try {
			status = productService.updateProduct(updateProductReqDto);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(new CMRespDto<>(1, "fail", status));
		}
		
		return ResponseEntity.ok(new CMRespDto<>(1, "success", status));
	}
	
	@DeleteMapping("/{productCode}")
	public ResponseEntity<?> deleteProduct(@PathVariable int productCode) {
		boolean status = false;
		
		try {
			status =  productService.deleteProduct(productCode);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(new CMRespDto<>(1, "delete fail", status));
		}
		
		return ResponseEntity.ok(new CMRespDto<>(1, "delete success", status));
	}
	

	
}
