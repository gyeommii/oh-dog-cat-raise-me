package com.ohdogcat.web;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ohdogcat.dto.member.MemberSessionDto;
import com.ohdogcat.dto.member.review.ReviewDeleteDto;
import com.ohdogcat.dto.member.review.ReviewListDto;
import com.ohdogcat.dto.member.review.ReviewUpdateDto;
import com.ohdogcat.service.ReviewService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/review")
public class ReviewRestController {

  private final ReviewService reviewService;

  @GetMapping("/all/{option_fk}")
  public ResponseEntity<List<ReviewListDto>> reviewByOptionFk(@PathVariable long option_fk) {
    log.debug("reviewByProductPk()");
    List<ReviewListDto> list = reviewService.selectReviewByOptionFk(option_fk);

    return ResponseEntity.ok(list);
  }

  @PutMapping("/{review_pk}")
  public ResponseEntity<Integer> updateReviewForMember(@PathVariable long review_pk,
      @RequestBody ReviewUpdateDto dto, HttpSession session) {
    MemberSessionDto memberSessionDto = (MemberSessionDto) session.getAttribute("signedMember");
    dto.setMember_fk(memberSessionDto.getMember_pk());
    log.debug("updateReviewForMember()");
    int result = reviewService.updateWhereReviewPkAndMemberFk(dto);

    return ResponseEntity.ok(result);
  }

  @DeleteMapping("/{review_pk}")
  public ResponseEntity<Integer> deleteReviewFromMemberFk(@RequestBody ReviewDeleteDto dto,
      HttpSession session) {
    MemberSessionDto memberSessionDto = (MemberSessionDto) session.getAttribute("signedMember");
    log.debug("deleteReviewFromMemberFk()");
    dto.setMember_fk(memberSessionDto.getMember_pk());

    int result = reviewService.deleteWhereReviewAndMemberFk(dto);

    return ResponseEntity.ok(result);
  }

}
