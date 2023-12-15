package com.ohdogcat.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ohdogcat.service.ProductService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("/list")
	public void allProductList(Model model) {
		// 전체상품 목록을 가져와서 모델에 추가
		model.addAttribute("products", productService.readNewProducts());
	}
	
	@GetMapping("/collection/new")
	public void newProductList(Model model) {
		// 신상품 목록을 가져와서 모델에 추가
		model.addAttribute("products", productService.readNewProducts());
	}
	
	@GetMapping("/collection/best")
	public void bestProductList(Model model) {
		// 베스트 목록을 가져와서 모델에 추가
		model.addAttribute("products", productService.readBestSellers());
	}
	 
	@GetMapping("/collection/sort")
    @ResponseBody
    public ProductService.SortedProductData sortProducts(@RequestParam String sortType) {
        // ProductService에서 정렬된 제품 데이터를 가져오는 메서드 호출
		log.debug("sortType={}", sortType);
        return productService.getSortedProducts(sortType);
    }

	// 유정언니꺼
	@GetMapping("/details")
	public void details(@RequestParam(name = "productPk") long productPk, Model model) {
		log.debug("details prodcutPk = {}", productPk);
	}
	
	
	

}
