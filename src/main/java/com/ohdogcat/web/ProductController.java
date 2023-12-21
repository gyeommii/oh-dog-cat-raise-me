package com.ohdogcat.web;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ohdogcat.dto.product.ProductOptionListDto;
import com.ohdogcat.dto.product.ProductPetTypeDto;
import com.ohdogcat.model.ProductOption;
import com.ohdogcat.service.ProductService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {
	private final ProductService productService;
	
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
	@GetMapping("/option/{optionPk}")
	@ResponseBody
	public ResponseEntity<ProductOption> getOption(@PathVariable long optionPk){
		log.debug("getOption(optionPk={})",optionPk);
		ProductOption option = productService.readOption(optionPk);
		return ResponseEntity.ok(option);
	}
	
	
}
