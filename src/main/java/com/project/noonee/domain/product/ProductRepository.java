package com.project.noonee.domain.product;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductRepository {
	public List<Product> getCategoryList(int categoryCode) throws Exception;
	public List<Product> getCollectionList(int collectionCode) throws Exception;
	public List<Product> getProduct(int productCode) throws Exception;
	public List<Product> getProductList() throws Exception;
	public int addProduct(Product product) throws Exception;
	public int addImgFiles(List<ProductImgFile> productImgFiles) throws Exception;
	public List<Product> adminProductList(Map<String, Object> map) throws Exception;
	public int updateProduct(Product product) throws Exception;
	public int deleteProduct(int productCode) throws Exception;
	
}
