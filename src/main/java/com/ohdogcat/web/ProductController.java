package com.ohdogcat.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ohdogcat.dto.product.ProductListDto;
import com.ohdogcat.dto.product.ProductPetTypeDto;
import com.ohdogcat.service.ProductService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService productService;

	
	@GetMapping("/collection/best") 
	public void getBestProducts(@RequestParam(defaultValue = "1") Long petType,
			@RequestParam(defaultValue = "sold") String orderBy, Model model) {
		log.debug("getBestProducts()");
		List<ProductListDto> products = productService.getProducts(petType, orderBy);
		
		log.debug("products={}",products);
		model.addAttribute("products", products);
	}
	
	@GetMapping("/collection/new") 
	public void getNewestProducts(@RequestParam(defaultValue = "1") Long petType,
			@RequestParam(defaultValue = "createDate") String orderBy, Model model) {
		log.debug("getBestProducts()");
		List<ProductListDto> products = productService.getProducts(petType, orderBy);
		
		log.debug("products={}",products);
		model.addAttribute("products", products);
	}
	
	
	
	// 유정언니꺼
	@GetMapping("/details")
	public void details(@RequestParam(name = "productPk") long productPk, Model model) {
		log.debug("details prodcutPk = {}", productPk);
	}

}
