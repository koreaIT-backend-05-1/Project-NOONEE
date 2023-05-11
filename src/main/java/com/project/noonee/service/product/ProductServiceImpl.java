package com.project.noonee.service.product;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.project.noonee.config.exception.CustomInternalServerErrorException;
import com.project.noonee.domain.product.Product;
import com.project.noonee.domain.product.ProductImgFile;
import com.project.noonee.domain.product.ProductRepository;
import com.project.noonee.web.dto.product.AddProductReqDto;
import com.project.noonee.web.dto.product.ProductRespDto;
import com.project.noonee.web.dto.product.UpdateProductReqDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor                                                                        
public class ProductServiceImpl implements ProductService {
	
	@Value("${file.path}")
    private String filePath;
	
	private final ProductRepository productRepository;

	
	@Override
	public boolean addProduct(AddProductReqDto addProductDto) throws Exception {
			
		int resultCount = 0;

        List<MultipartFile> files = addProductDto.getFiles();
        List<ProductImgFile> productImgFiles = null;

        Product product = addProductDto.productEntity();
        resultCount = productRepository.addProduct(product);
        log.warn("{}", product);

        if(files != null) {
            int product_code = product.getProduct_code();
            productImgFiles = getProductImgFiles(files, product_code);
            resultCount = productRepository.addImgFiles(productImgFiles);
        }

        if(resultCount == 0){
            throw new CustomInternalServerErrorException("상품 등록 실패");
        }
	        
		return true;
	}
	
	 private List<ProductImgFile> getProductImgFiles(List<MultipartFile> files, int productId) throws Exception {
	        List<ProductImgFile> productImgFiles = new ArrayList<ProductImgFile>();

	        files.forEach(file -> {
	            String originName = file.getOriginalFilename();
	            String extension = originName.substring(originName.lastIndexOf("."));
	            String temp_name = UUID.randomUUID().toString() + extension;

	            Path uploadPath = Paths.get(filePath + "product/" + temp_name);

	            File f = new File(filePath + "product");
	            if(!f.exists()) {
	                f.mkdirs();
	            }

	            try {
	                Files.write(uploadPath, file.getBytes());
	            } catch (IOException e) {
	                throw new RuntimeException(e);
	            }

	            ProductImgFile productImgFile = ProductImgFile.builder()
	                    .product_code(productId)
	                    .origin_name(originName)
	                    .temp_name(temp_name)
	                    .build();

	            productImgFiles.add(productImgFile);
	        });

	        return productImgFiles;
	    }
	
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
					.productPrice(product.getProduct_price())
					.categoryCode(product.getCategory_code())
					.collectionCode(product.getCollection_code())
					.tempName(product.getTemp_name())
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

	@Override
	public List<ProductRespDto> getProductList(int page, String searchFlag) throws Exception {
		int index = (page - 1) * 10;
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("index", index);
		map.put("search_flag", searchFlag);
		
		List<ProductRespDto> list = new ArrayList<ProductRespDto>();
		
		productRepository.adminProductList(map).forEach(product -> {
			list.add(product.toListDto());
		});
//		log.error(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>{}", list);
		
		return list;
	}

	@Override
	public boolean deleteProduct(int productCode) throws Exception {
		
		return productRepository.deleteProduct(productCode) > 0;
	}

	@Override
	public boolean updateProduct(UpdateProductReqDto updateProductReqDto) throws Exception {
		String temp_name = null;
		
		if(updateProductReqDto.getImg() != null) {
			String originName = updateProductReqDto.getImg().getOriginalFilename();
			String extension = originName.substring(originName.lastIndexOf("."));
			temp_name = UUID.randomUUID().toString() + extension;
			
			Path uploadPath = Paths.get(filePath + "product/" + temp_name);
			
			File f = new File(filePath + "product");
			if(!f.exists()) {
				f.mkdirs();
			}
			
			try {
				Files.write(uploadPath, updateProductReqDto.getImg().getBytes());
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		
			
        Product product = updateProductReqDto.productEntity(temp_name);
        
        
		
		return productRepository.updateProduct(product) > 0;
	}

	
}

	
	

