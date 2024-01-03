package com.ohdogcat.web;

import com.ohdogcat.service.ReviewService;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/review")
public class ReviewRestController {

    private final ReviewService reviewService;

    @GetMapping("")
    public ResponseEntity<Object> getReviewDataAtProductPage(@RequestParam Long productPk) {
        log.debug("리뷰컨트");
        Map<String, Object> result = reviewService.getReviewDataAtProductPage(productPk);

        return ResponseEntity.ok(result);
    }


}
