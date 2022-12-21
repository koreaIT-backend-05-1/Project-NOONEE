package com.project.noonee.domain.product;

import java.time.LocalDateTime;
import java.util.List;

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
	private String category_name;
	private String collection_name;
	private String product_name;
	private String product_price;
	private String product_img;
	private String search_flag;
	private LocalDateTime create_date;
	private String origin_name;
	private String temp_name;
	
	private List<ProductImgFile> product_img_files;
	
	public ProductRespDto toListDto() {
		return ProductRespDto.builder()
				.productCode(product_code)
				.categoryName(category_name)
				.collectionName(collection_name)
				.productName(product_name)
				.productPrice(product_price)
				.tempName(temp_name)
				.build();
	}
	
}
