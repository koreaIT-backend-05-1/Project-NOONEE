package com.project.noonee.web.dto.product;

import org.springframework.web.multipart.MultipartFile;

import com.project.noonee.domain.product.Product;

import lombok.Data;

@Data
public class UpdateProductReqDto {

	private int productCode;
	private String productName;
	private String productPrice;
	private int categoryCode;
	private int collectionCode;
	private MultipartFile img;
//	private String tempName;
	
	public Product productEntity(String tempFileName) {
		Product product =  Product.builder()
				.product_code(productCode)
				.product_name(productName)
				.product_price(productPrice)
				.category_code(categoryCode)
				.collection_code(collectionCode)
				.temp_name(tempFileName)
				.build();
				
		if(img != null) {
			product.setOrigin_name(img.getOriginalFilename());
		}
				
		return product;
	}
}
