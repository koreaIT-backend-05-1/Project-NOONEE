package com.project.noonee.web.dto.product;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

import com.project.noonee.domain.product.Product;
import com.project.noonee.web.dto.validationGroups.ValidationGroups;

import lombok.Data;

@Data
public class AddProductReqDto {

	@NotNull(message = "빈 값일 수 없습니다.", groups = ValidationGroups.NotBlankGroup.class)
	private int category_code;
	@NotNull(message = "빈 값일 수 없습니다.", groups = ValidationGroups.NotBlankGroup.class)
	private int collection_code;
	@NotNull(message = "상품명을 입력해 주세요.", groups = ValidationGroups.NotBlankGroup.class)
	private String product_name;
	@NotNull(message = "가격을 입력해 주세요.", groups = ValidationGroups.NotBlankGroup.class)
	private String product_price;
	
	private List<MultipartFile> files;
	
	public Product productEntity() {
		return Product.builder()
				.category_code(category_code)
				.collection_code(collection_code)
				.product_name(product_name)
				.product_price(product_price)
				.build();
	}
}
