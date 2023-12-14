package com.ohdogcat.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ohdogcat.dto.product.ProductPetTypeDto;
import com.ohdogcat.service.ProductService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {
	private final ProductService productService;
	
	@GetMapping("/")
	public String home(Model model) {
		log.debug("home()");
		List<ProductPetTypeDto> newList = productService.readOrderByNew();
		List<ProductPetTypeDto> bestList = productService.readOrderByBest();
		
		model.addAttribute("newList", newList);
		model.addAttribute("bestList", bestList);
		
		return "home";
	}
}
