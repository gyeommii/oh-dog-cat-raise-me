package com.ohdogcat.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ohdogcat.domain.Product;
import com.ohdogcat.dto.product.ProductPetTypeDto;
import com.ohdogcat.repository.ProductDao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {
	private final ProductDao productDao;
	
	public List<ProductPetTypeDto> readOrderByNew(){
		log.debug("readOrderByCreate()");
		List<ProductPetTypeDto> list = productDao.selectPetTypeOrderByCreateDesc();
		return list;
	}
	
	public List<ProductPetTypeDto> readOrderByBest(){
		log.debug("readOrderBysold()");
		List<ProductPetTypeDto> list = productDao.selectPetTypeOrderBySoldDesc();
		return list;
	}
	
	
}
