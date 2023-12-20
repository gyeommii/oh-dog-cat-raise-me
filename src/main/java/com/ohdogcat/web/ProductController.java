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
	
	
	// 강아지 & 고양이 전체 목록(신상품순)
	@GetMapping("/list")
	public void allProductList(Model model) {
		log.debug("list()");
		List<ProductPetTypeDto> dogNew = productService.readDogOrderByNewest20();
		List<ProductPetTypeDto> catNew = productService.readCatOrderByNewest20();

		model.addAttribute("dogNew", dogNew);
		model.addAttribute("catNew", catNew);
	}

	// 신상품 전체목록
	@GetMapping("/collection/new")
	public void newProductList(Model model) {
//		model.addAttribute("products", productService.readNewProducts());
	}

//	// 베스트 전체목록
//	@GetMapping("/collection/best")
//	public void bestProductList(Model model) {
////		model.addAttribute("products", productService.readBestSellers());
//	}

//	// 정렬
//	@GetMapping("/collection/sort")
//    @ResponseBody
//    public ProductService.SortedProductData sortProducts(@RequestParam String sortType) {
//        // ProductService에서 정렬된 제품 데이터를 가져오는 메서드 호출
//		log.debug("sortType={}", sortType);
//        return productService.getSortedProducts(sortType);
//    }

	// 유정언니꺼
	@GetMapping("/details")
	public void details(@RequestParam(name = "productPk") long productPk, Model model) {
		log.debug("details prodcutPk = {}", productPk);
	}

}
