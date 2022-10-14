package com.project.noonee.web.dto.product;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ProductRespDto {
	private int productCode;
	private int categoryCode;
	private int collectionCode;
	private String productName;
	private String productPrice;
	private String productImg;
}
