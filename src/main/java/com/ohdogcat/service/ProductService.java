package com.ohdogcat.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ohdogcat.domain.Product;
import com.ohdogcat.dto.product.ProductListItemDto;
import com.ohdogcat.repository.ProductDao;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProductService {
	
	private final ProductDao productDao;
	
	public List<ProductListItemDto> readNewProducts() {
		log.debug("readNewProducts");
		List<Product> list = productDao.selectByCreatedDesc();
		return list.stream()
				.map(ProductListItemDto::fromEntity)
				.toList();
	}
	
	public List<ProductListItemDto> readBestSellers() {
        log.debug("readBestSellers");
        List<Product> list = productDao.selectByBestSellerDesc();
        return list.stream()
        		.map(ProductListItemDto::sortProductEntity)
        		.toList();
    }
	
	public List<ProductListItemDto> readLowestPrice() {
        log.debug("readLowestPrice");
        List<Product> list = productDao.selectByLowestPrice();
        return list.stream()
        		.map(ProductListItemDto::sortProductEntity)
        		.toList();
    }
	
	public List<ProductListItemDto> readHighestPrice() {
        log.debug("readHighestPrice");
        List<Product> list = productDao.selectByHighestPrice();
        return list.stream()
        		.map(ProductListItemDto::sortProductEntity)
        		.toList();
    }
	
	public SortedProductData getSortedProducts(String sortType) {
	    List<Product> sortedList = getSortedList(sortType);
	    return SortedProductData.fromList(sortedList); // 정렬된 데이터
	}
	
	private List<Product> getSortedList(String sortType) {
        switch (sortType) {
            case "new":
                return productDao.selectByCreatedDesc();
	        case "best":
	        	return  productDao.selectByBestSellerDesc();
	        case "lowest":
	        	return  productDao.selectByLowestPrice();
	        case "highest":
	        	return  productDao.selectByHighestPrice();
	        default:
                return productDao.selectByCreatedDesc();
	    }
	}
    
	@Data
	@Builder
	public static class SortedProductData {
	    private List<ProductListItemDto> products;
	    private int count; // 목록의 상품수

	    public static SortedProductData fromList(List<Product> sortedList) {
	        return SortedProductData.builder()
	                .products(sortedList.stream()
	                        .map(ProductListItemDto::fromEntity)
	                        .collect(Collectors.toList()))
	                .count(sortedList.size())
	                .build();
	    }
	}
}

