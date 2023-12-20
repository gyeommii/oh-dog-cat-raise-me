package com.ohdogcat.repository;



import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ohdogcat.dto.product.ProductListDto;
import com.ohdogcat.dto.product.ProductPetTypeDto;

public interface ProductDao {
	/*
	 * 
	 */
//	List<ProductListDto> selectProducts(String keyword, Long petType, Long minPrice, Long maxPrice, Boolean inStock, String orderBy);
	
//	// 처음 펫타입과 조건별로 불러오는 설정
//	List<ProductListDto> selectProductsPetTypeSoldDesc(
//			@Param("petType") Long petType,
//	        @Param("orderBy") String orderBy
//	    );
	
	// 정렬, 검색 시 사용
	List<ProductListDto> selectProducts(
			@Param("petType") Long petType,
	        @Param("keyword") String keyword,
	        @Param("minPrice") Long minPrice,
	        @Param("maxPrice") Long maxPrice,
	        @Param("inStock") Boolean inStock,
	        @Param("orderBy") String orderBy
	    );
	 

	
	
//	List<Product> selectByCreatedDesc(); // list페이지에서 사용 신상품순
//	
//	List<Product> selectByBestSellerDesc(); // best페이지에서 사용 판매순
//	
//	List<Product> selectByLowestPrice(); // 가격낮은순
//	
//	List<Product> selectByHighestPrice(); // 가격높은순
	
	List<ProductPetTypeDto> selectByCreatedDesc(); // list페이지에서 사용 신상품순
	
	List<ProductPetTypeDto> selectByBestSellerDesc(); // best페이지에서 사용 판매순
	
	List<ProductPetTypeDto> selectByLowestPrice(); // 가격낮은순
	
	List<ProductPetTypeDto> selectByHighestPrice(); // 가격높은순
	
	
	// 강아지/고양이 전체 신규 등록 순
	List<ProductPetTypeDto> selectOrderByCreateDesc();
	
	
	// 강아지 타입 신상품 상위 8개 
	List<ProductPetTypeDto> selectDogTypeOrderByCreateDesc();
	// 강아지 타입 베스트 상품 상위 8개 
	List<ProductPetTypeDto> selectDogTypeOrderBySoldDesc();
	// 강아지 타입 신상품 전체
	List<ProductPetTypeDto> selectDogTypeAllProduct();
	// 강아지 타입 신상품 상위 20개
	List<ProductPetTypeDto> selectDogTypeOrderByCreateDescNewest20();
	// 강아지 타입 베스트 상위 20개
	List<ProductPetTypeDto> selectDogTypeOrderByCreateDescBestSeller20();
	// 강아지 타입 최저가 상위 20개
	List<ProductPetTypeDto> selectDogTypeOrderByLowestPrice20();
	// 강아지 타입 최고가 상위 20개
	List<ProductPetTypeDto> selectDogTypeOrderByHighestPrice20();
	
	// 고양이 타입 신상품 상위 8개 
	List<ProductPetTypeDto> selectCatTypeOrderByCreateDesc();
	// 고양이 타입 베스트 상품 상위 8개 
	List<ProductPetTypeDto> selectCatTypeOrderBySoldDesc();
	// 고양이 타입 신상품 전체
	List<ProductPetTypeDto> selectCatTypeAllProduct();
	// 고양이 타입 신상품 상위 20개
	List<ProductPetTypeDto> selectCatTypeOrderByCreateDescNewest20();
	// 고양이 타입 베스트 상위 20개
	List<ProductPetTypeDto> selectCatTypeOrderByCreateDescBestSeller20();
	// 고양이 타입 최저가 상위 20개
	List<ProductPetTypeDto> selectCatTypeOrderByLowestPrice20();
	// 고양이 타입 최고가 상위 20개
	List<ProductPetTypeDto> selectCatTypeOrderByHighestPrice20();




	
	
}
