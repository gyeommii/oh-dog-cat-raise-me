package com.ohdogcat.web;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ohdogcat.dto.member.review.ReviewListDto;
import com.ohdogcat.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/review")
public class ReviewRestController {
  
  private final ReviewService reviewService;
  
  @GetMapping("/details/{product}")
  public ResponseEntity<List<ReviewListDto>> reviewByProductPk (@PathVariable long productPk) {
    
    List<ReviewListDto> list = reviewService.selectReviewByProductPk(productPk);
    
    return ResponseEntity.ok(list);
  }

}
