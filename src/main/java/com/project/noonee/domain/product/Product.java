package com.project.noonee.domain.product;

import java.time.LocalDateTime;

import com.project.noonee.web.dto.product.ProductRespDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Product {
	private int product_code;
	private int category_code;
	private int collection_code;
	private String product_name;
	private String product_price;
	private String product_img;
	private LocalDateTime create_date;
	
	public ProductRespDto toListDto() {
		return ProductRespDto.builder()
				.productCode(product_code)
				.categoryCode(category_code)
				.collectionCode(collection_code)
				.productName(product_name)
				.productPrice(product_price)
				.productImg(product_img)
				.build();
	}
	
}
