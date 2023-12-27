package com.ohdogcat.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
			@RequestParam String orderBy,
			@RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "20") int size) {
		log.debug("getSortedProducts(petType={}, orderBy={})", petType, orderBy);

		List<ProductListDto> products = productService.getProducts(petType, orderBy, page, size);

		return ResponseEntity.ok(products);
	}
	
	@GetMapping("/filter")
	public ResponseEntity<List<ProductListDto>> getFilterProducts(
			@RequestParam Long petType,
			@RequestParam(defaultValue = "") String keyword,
			@RequestParam Long minPrice,
			@RequestParam Long maxPrice,
			@RequestParam Boolean inStock,
			@RequestParam (defaultValue = "") String orderBy,
			@RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "20") int size) {
		log.debug("getFilterProducts(petType={}, orderBy={}, inStock={},)", petType, keyword, minPrice, maxPrice, inStock, orderBy);
		
		List<ProductListDto> products = productService.getFilteredProducts(petType, keyword, minPrice, maxPrice, inStock, orderBy, page, size);

		
		return ResponseEntity.ok(products);
	}
	

	 @GetMapping("/collection/best")
	    public ResponseEntity<?> getBestProducts(
	            @RequestParam(defaultValue = "1") Long petType,
	            @RequestParam(defaultValue = "sold") String orderBy,
	            @RequestParam(defaultValue = "1") int page,
	            @RequestParam(defaultValue = "20") int size) {
	        List<ProductListDto> products = productService.getProducts(petType, orderBy, page, size);
	        int totalProducts = productService.getTotalProductsCount(petType, null, null, null, null); 
	        int totalPages = (int) Math.ceil((double) totalProducts / size);
	        
	        Map<String, Object> response = new HashMap<>();
	        response.put("products", products);
	        response.put("totalPages", totalPages);
	        
	        return ResponseEntity.ok(response);
	    }
	
	


}
