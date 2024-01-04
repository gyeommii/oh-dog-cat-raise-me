package com.ohdogcat.web;

import com.ohdogcat.dto.member.MemberSessionDto;
import com.ohdogcat.service.ReviewService;
import jakarta.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
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
    public ResponseEntity<Object> getReviewDataAtProductPage(@RequestParam Long productPk, HttpSession session) {
        MemberSessionDto signedMember = (MemberSessionDto) session.getAttribute("signedMember");

        Map<String , Object> reviewDataMap = new HashMap<>();
        if (signedMember != null) {
            reviewDataMap.put("memberPk", signedMember.getMember_pk());
        }
        reviewDataMap.put("productPk", productPk);
        Map<String, Object> result = reviewService.getReviewDataAtProductPage(reviewDataMap);

        if (signedMember == null) {
            result.put("loginStatus", "disabled");
        } else {
            result.put("loginStatus", "");
        }

        return ResponseEntity.ok(result);
    }

    @GetMapping("/like/{reviewPk}")
    public ResponseEntity<String> clickLike (HttpSession session,@PathVariable Long reviewPk) {
        MemberSessionDto signedMember = (MemberSessionDto) session.getAttribute("signedMember");

        Map<String , Object> reviewLikeDataMap = new HashMap<>();
        reviewLikeDataMap.put("memberPk", signedMember.getMember_pk());
        reviewLikeDataMap.put("reviewPk", reviewPk);

        String result = reviewService.clickLike(reviewLikeDataMap);
        return ResponseEntity.ok(result);
    }


}
