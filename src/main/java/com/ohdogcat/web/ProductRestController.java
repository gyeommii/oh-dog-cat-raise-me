package com.ohdogcat.web;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ohdogcat.dto.product.ProductListDto;
import com.ohdogcat.service.ProductService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/aaa/bbb")
public class ProductRestController {

	private final ProductService productService;

	@GetMapping("/best")
	public ResponseEntity<List<ProductListDto>> getSortedProducts(
			@RequestParam Long petType,
			@RequestParam String orderBy) {
		log.debug("getSortedProducts(petType={}, orderBy={})", petType, orderBy);

		List<ProductListDto> products = productService.getProducts(petType, orderBy);

		return ResponseEntity.ok(products);
	}

//	@GetMapping("best/{petType}/{orderBy}") //product/collection/best${petType}/${orderBy}
//	public void getSortedProducts(
//			@RequestParam Long petType, @RequestParam String orderBy, Model model){
//		log.debug("getSortedProducts(petType={}, orderBy={})", petType, orderBy);
//		
//		List<ProductListDto> products = productService.getProducts(petType, orderBy);
//		
//		model.addAttribute("products", products);
//	}

}
