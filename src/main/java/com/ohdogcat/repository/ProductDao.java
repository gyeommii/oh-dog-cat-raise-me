package com.ohdogcat.repository;


import com.ohdogcat.model.Product;

import java.util.List;

import com.ohdogcat.dto.product.ProductPetTypeDto;

public interface ProductDao {
	List<Product> selectByCreatedDesc(); // list페이지에서 사용 신상품순
	
	List<Product> selectByBestSellerDesc(); // best페이지에서 사용 판매순
	
	List<Product> selectByLowestPrice(); // 가격낮은순
	
	List<Product> selectByHighestPrice(); // 가격높은순
	
	
	// 강아지/고양이 전체 신규 등록 순
	List<ProductPetTypeDto> selectOrderByCreateDesc();
	
	
	// 강아지 타입 신상품 상위 8개 
	List<ProductPetTypeDto> selectDogTypeOrderByCreateDesc();
	// 강아지 타입 베스트 상품 상위 8개 
	List<ProductPetTypeDto> selectDogTypeOrderBySoldDesc();
	// 강아지 타입 신상품 전체
	List<ProductPetTypeDto> selectDogTypeAllProduct();
	
	// 고양이 타입 신상품 상위 8개 
	List<ProductPetTypeDto> selectCatTypeOrderByCreateDesc();
	// 고양이 타입 베스트 상품 상위 8개 
	List<ProductPetTypeDto> selectCatTypeOrderBySoldDesc();
	// 고양이 타입 신상품 전체
	List<ProductPetTypeDto> selectCatTypeAllProduct();
}
