package com.project.noonee.service.product;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.project.noonee.domain.product.Product;
import com.project.noonee.domain.product.ProductRepository;
import com.project.noonee.web.dto.product.ProductRespDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor                                                                        
public class ProductServiceImpl implements ProductService {
	
	private final ProductRepository productRepository;

	@Override
	public List<ProductRespDto> getCategoryList(int categoryCode) throws Exception {
		
		List<ProductRespDto> categoryList = new ArrayList<ProductRespDto>();
		
		productRepository.getCategoryList(categoryCode).forEach(product -> {
			categoryList.add(product.toListDto());
		});
		return categoryList;
	}

	@Override
	public List<ProductRespDto> getCollectionList(int collectionCode) throws Exception {
		
		List<ProductRespDto> collectionList = new ArrayList<ProductRespDto>();
		
		productRepository.getCollectionList(collectionCode).forEach(product -> {
			collectionList.add(product.toListDto());
		});
		return collectionList;
	}


	@Override
	public ProductRespDto getProduct(int productCode) throws Exception {
		ProductRespDto productRespDto = null;
		
		List<Product> products = productRepository.getProduct(productCode);
		
		if(!products.isEmpty()) {
			Product product = products.get(0);
			
			productRespDto = ProductRespDto.builder()
					.productCode(product.getProduct_code())
					.productName(product.getProduct_name())
					.productImg(product.getProduct_img())
					.productPrice(product.getProduct_price())
					.build();
		}
		
		return productRespDto;
	}
	
	@Override
	public List<ProductRespDto> getList() throws Exception {
		List<ProductRespDto> list = new ArrayList<ProductRespDto>();
		
		productRepository.getProductList().forEach(product -> {
			list.add(product.toListDto());
		});
		
		
		return list;

	}
	
}
