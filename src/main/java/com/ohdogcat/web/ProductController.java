package com.ohdogcat.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ohdogcat.dto.product.ProductListDto;
import com.ohdogcat.dto.product.ProductPetTypeDto;
import com.ohdogcat.dto.product.ProductOptionListDto;
import com.ohdogcat.model.ProductOption;
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
	
	
	
	@GetMapping("/details")
	public void details(@RequestParam(name = "productPk") long productPk, Model model) {
		log.debug("details prodcutPk = {}", productPk);
		ProductPetTypeDto product = productService.readProduct(productPk);
		log.debug("product ={}", product);
		model.addAttribute("p", product);
	}
	
	// 상품PK에 따른 옵션 리스트 정보 불러오기
	@GetMapping("/option/all/{productPk}")
	@ResponseBody
	public ResponseEntity<List<ProductOptionListDto>> getOptionlist(@PathVariable long productPk) {
		log.debug("getOptionList(prodcutPk={})", productPk);
		List<ProductOptionListDto> productOption = productService.readProductOption(productPk);
		
		return ResponseEntity.ok(productOption);
	}
	
	// 옵션Pk에 따른 옵션 정보 불러오기
	@GetMapping("option/{optionPk}")
	@ResponseBody
	public ResponseEntity<ProductOption> getOption(@PathVariable long optionPk){
		log.debug("getOption(optionPk={})",optionPk);
		ProductOption option = productService.readOption(optionPk);
		return ResponseEntity.ok(option);
	}
	
	
}
