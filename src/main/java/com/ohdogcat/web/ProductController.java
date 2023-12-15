package com.ohdogcat.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	}
	
	
	
}
