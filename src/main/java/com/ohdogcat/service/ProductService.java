package com.ohdogcat.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ohdogcat.dto.product.ProductPetTypeDto;
import com.ohdogcat.model.Product;
import com.ohdogcat.repository.ProductDao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {
	private final ProductDao productDao;
	
	public List<ProductPetTypeDto> readDogOrderByNew(){
		log.debug("readDogOrderByNew()");
		List<ProductPetTypeDto> list = productDao.selectDogTypeOrderByCreateDesc();
		return list;
	}
	
	public List<ProductPetTypeDto> readDogOrderByBest(){
		log.debug("readDogOrderByBest()");
		List<ProductPetTypeDto> list = productDao.selectDogTypeOrderBySoldDesc();
		return list;
	}
	
	public List<ProductPetTypeDto> readCatOrderByNew(){
		log.debug("readCatOrderByNew()");
		List<ProductPetTypeDto> list = productDao.selectCatTypeOrderByCreateDesc();
		return list;
	}
	
	public List<ProductPetTypeDto> readCatOrderByBest(){
		log.debug("readCatOrderByBest()");
		List<ProductPetTypeDto> list = productDao.selectCatTypeOrderBySoldDesc();
		return list;
	}
	
	
}
