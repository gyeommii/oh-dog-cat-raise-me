package com.ohdogcat.web;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.PutExchange;
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
  
  @GetMapping("/details/{productPk}")
  public ResponseEntity<List<ReviewListDto>> reviewByProductPk (@PathVariable long productPk) {
    log.debug("reviewByProductPk()");
    List<ReviewListDto> list = reviewService.selectReviewByProductPk(productPk);
    
    return ResponseEntity.ok(list);
  }

//  @PostMapping("/updateReview")
//  public ResponseEntity<Integer> updateReviewForMember(@RequestParam("review_pk") long review_pk, @RequestParam("member_fk") long member_fk, @RequestBody ReviewUpdateDto updateDto) {  public 
//  }
}
