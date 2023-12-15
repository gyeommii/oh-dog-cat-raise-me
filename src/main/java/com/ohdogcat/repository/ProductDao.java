package com.ohdogcat.repository;

import java.util.List;

import com.ohdogcat.domain.Product;

public interface ProductDao {

	List<Product> selectByCreatedDesc();
	
	List<Product> selectByBestSellerDesc();
	
	List<Product> selectByLowestPrice();
	
	List<Product> selectByHighestPrice();
	

}
