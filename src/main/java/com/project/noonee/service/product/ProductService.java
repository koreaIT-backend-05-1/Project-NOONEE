package com.project.noonee.service.product;

import java.util.List;

import com.project.noonee.web.dto.product.AddProductReqDto;
import com.project.noonee.web.dto.product.ProductRespDto;
import com.project.noonee.web.dto.product.UpdateProductReqDto;

public interface ProductService {
	public List<ProductRespDto> getCategoryList(int categoryCode) throws Exception;
	public List<ProductRespDto> getCollectionList(int collectionCode) throws Exception;
	public ProductRespDto getProduct(int productCode) throws Exception;
	public List<ProductRespDto> getList() throws Exception;
	
	public boolean addProduct(AddProductReqDto addProductDto) throws Exception;
	public List<ProductRespDto> getProductList(int page, String searchFlag) throws Exception;
	public boolean updateProduct(UpdateProductReqDto updateProductReqDto) throws Exception;
	public boolean deleteProduct(int productCode) throws Exception;
	
	
}
