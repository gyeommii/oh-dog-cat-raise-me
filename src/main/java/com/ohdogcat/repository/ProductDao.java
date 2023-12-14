package com.ohdogcat.repository;

import java.util.List;

import com.ohdogcat.domain.Product;
import com.ohdogcat.dto.product.ProductPetTypeDto;

public interface ProductDao {
	
	List<ProductPetTypeDto> selectPetTypeOrderByCreateDesc();
	
	List<ProductPetTypeDto> selectPetTypeOrderBySoldDesc();
	
}
