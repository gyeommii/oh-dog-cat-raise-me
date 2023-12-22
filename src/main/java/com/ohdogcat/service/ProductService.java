package com.ohdogcat.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ohdogcat.dto.product.ProductListDto;
import com.ohdogcat.dto.product.ProductListItemDto;
import com.ohdogcat.dto.product.ProductOptionListDto;
import com.ohdogcat.dto.product.ProductPetTypeDto;
import com.ohdogcat.model.ProductOption;
import com.ohdogcat.repository.ProductDao;

import lombok.Builder;
import lombok.Data;

import com.ohdogcat.dto.product.ProductPetTypeDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProductService {

	private final ProductDao productDao;
	
	public List<ProductListDto> getProducts(Long petType, String orderBy) {
		log.debug("getProducts(petType={}, orderBy={})", petType, orderBy);
        return productDao.selectProducts(petType, "",  null, null, null, orderBy);
    }

	
	public List<ProductListDto> getFilteredProducts(Long petType, String keyword, Long minPrice, Long maxPrice, Boolean inStock, String orderBy) {
        return productDao.selectProducts(petType, keyword, minPrice, maxPrice, inStock, orderBy);
    }
	
	
	// Dog
	public List<ProductPetTypeDto> readDogOrderByNew() {
		log.debug("readDogOrderByNew()");
		List<ProductPetTypeDto> list = productDao.selectDogTypeOrderByCreateDesc();
		return list;
	}
	
	public List<ProductPetTypeDto> readDogOrderByBest() {
		log.debug("readDogOrderByBest()");
		List<ProductPetTypeDto> list = productDao.selectDogTypeOrderBySoldDesc();
		return list;
	}
	
	
	// Cat
	public List<ProductPetTypeDto> readCatOrderByNew() {
		log.debug("readCatOrderByNew()");
		List<ProductPetTypeDto> list = productDao.selectCatTypeOrderByCreateDesc();
		return list;
	}
	

	public List<ProductPetTypeDto> readCatOrderByBest() {
		log.debug("readCatOrderByBest()");
		List<ProductPetTypeDto> list = productDao.selectCatTypeOrderBySoldDesc();
		return list;
	}

	public ProductPetTypeDto readProduct(long productPk) {
		log.debug("readProduct()");
		ProductPetTypeDto product = productDao.selectByProductPk(productPk);
		return product;
	}

	public List<ProductOptionListDto> readProductOption(long productPk) {
		log.debug("readProductOption()");
		List<ProductOptionListDto> list = productDao.selectOptionByProduckPk(productPk);
		return list;
	}

	public ProductOption readOption(long optionPk) {
		log.debug("readOption()");
		ProductOption option = productDao.selectOptionByOptionPk(optionPk);
		return option;
	}
	
	
	

}