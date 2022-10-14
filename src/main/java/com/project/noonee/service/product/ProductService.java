package com.project.noonee.service.product;

import java.util.List;

import com.project.noonee.web.dto.product.ProductRespDto;

public interface ProductService {
	public List<ProductRespDto> getCategoryList(int categoryCode) throws Exception;
	public List<ProductRespDto> getCollectionList(int collectionCode) throws Exception;
	public ProductRespDto getProduct(int productCode) throws Exception;
	public List<ProductRespDto> getList() throws Exception;
	
}
