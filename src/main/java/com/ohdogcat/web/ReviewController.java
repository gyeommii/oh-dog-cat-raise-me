package com.ohdogcat.web;

import java.io.IOException;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import com.ohdogcat.dto.member.MemberSessionDto;
import com.ohdogcat.dto.member.review.ReviewDetailDto;
import com.ohdogcat.dto.member.review.ReviewRegisterDto;
import com.ohdogcat.service.ReviewService;
import com.ohdogcat.util.FtpImgLoaderUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/mypage")
public class ReviewController {

  private final ReviewService reviewService;
  private final FtpImgLoaderUtil ftpImgLoaderUtil;

  @GetMapping("/reviewregister")
  public String reviewRegister(HttpSession session, Model model) {
    MemberSessionDto memberSessionDto = (MemberSessionDto) session.getAttribute("signedMember");
    log.debug("memberSessionDto.getMember_pk()={}", memberSessionDto.getMember_pk());
    long member_fk = memberSessionDto.getMember_pk();

//    List<ReviewDetailDto> reviewDetailDto = reviewService.selectReviewDetailViews(member_fk);
//    log.debug("reviewDetailDto={}", reviewDetailDto);
//
//    model.addAttribute("forReviewer", reviewDetailDto);

    return "review/reviewregister";
  }



  // 리뷰 작성
  @PostMapping("/reviewregister")
  public String reviewRegister(MultipartFile img_file, ReviewRegisterDto dto,
      HttpServletRequest req, HttpSession session) throws IOException {
    log.debug("reviewRegister(dto={}, session={}", dto);
    MemberSessionDto memberSessionDto = (MemberSessionDto) session.getAttribute("signedMember");
    dto.setMember_fk(memberSessionDto.getMember_pk());
    
    if (!img_file.isEmpty()) {
      log.debug("MultipartFile img={}", img_file);

      String imgPath = ftpImgLoaderUtil.upload(img_file, req.getServletPath());
      dto.setImg(imgPath);
      log.debug("reviewRegister(dto={})", dto);
      reviewService.insertReview(dto);
    } else {
      dto.setImg("");
      reviewService.insertReview(dto);
    }

    return "redirect:/";
  }

}
