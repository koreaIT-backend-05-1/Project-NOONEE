package com.project.noonee.domain.product;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductRepository {
	public List<Product> getCategoryList(int categoryCode) throws Exception;
	public List<Product> getCollectionList(int collectionCode) throws Exception;
	public List<Product> getProduct(int productCode) throws Exception;
	public List<Product> getProductList() throws Exception;
}
