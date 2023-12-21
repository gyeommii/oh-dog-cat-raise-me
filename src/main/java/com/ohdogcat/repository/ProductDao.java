package com.ohdogcat.repository;

import java.util.List;

import com.ohdogcat.dto.product.ProductOptionListDto;
import com.ohdogcat.dto.product.ProductPetTypeDto;
import com.ohdogcat.model.ProductOption;

public interface ProductDao {
	// 강아지/고양이 전체 신규 등록 순
	List<ProductPetTypeDto> selectOrderByCreateDesc();
	
	// 강아지 타입 신상품 상위 8개 
	List<ProductPetTypeDto> selectDogTypeOrderByCreateDesc();
	// 강아지 타입 베스트 상품 상위 8개 
	List<ProductPetTypeDto> selectDogTypeOrderBySoldDesc();
	
	// 고양이 타입 신상품 상위 8개 
	List<ProductPetTypeDto> selectCatTypeOrderByCreateDesc();
	// 고양이 타입 베스트 상품 상위 8개 
	List<ProductPetTypeDto> selectCatTypeOrderBySoldDesc();
	

	ProductPetTypeDto selectByProductPk(long productPk);
	
	List<ProductOptionListDto> selectOptionByProduckPk(long productPk);

	ProductOption selectOptionByOptionPk(long optionPk);
	
}
