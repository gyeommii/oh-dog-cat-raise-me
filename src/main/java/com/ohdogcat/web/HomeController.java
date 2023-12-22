package com.ohdogcat.web;

import com.ohdogcat.dto.product.ProductPetTypeDto;
import com.ohdogcat.service.ProductService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {
    private final ProductService productService;

    @GetMapping("/")
    public String home(Model model) {
        log.debug("home()");

        // 강아지 & 고양이 신상품 및 베스트 상품 각각 상위 8개 목록
        List<ProductPetTypeDto> dogNew = productService.readDogOrderByNew();
        List<ProductPetTypeDto> dogBest = productService.readDogOrderByBest();
        List<ProductPetTypeDto> catNew = productService.readCatOrderByNew();
        List<ProductPetTypeDto> catBest = productService.readCatOrderByBest();

        model.addAttribute("dogNew", dogNew);
        model.addAttribute("dogBest", dogBest);
        model.addAttribute("catNew", catNew);
        model.addAttribute("catBest", catBest);

        return "home";
    }
}
