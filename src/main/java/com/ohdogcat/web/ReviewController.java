package com.ohdogcat.web;

import java.io.IOException;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.ohdogcat.dto.member.MemberSessionDto;
import com.ohdogcat.dto.member.review.ReviewDetailDto;
import com.ohdogcat.dto.member.review.ReviewDetailFindDto;
import com.ohdogcat.dto.member.review.ReviewListDto;
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
@RequestMapping("/review")
public class ReviewController {

  private final ReviewService reviewService;
  private final FtpImgLoaderUtil ftpImgLoaderUtil;

  // 리뷰 작성 페이지
  @GetMapping("")
  public String reviewRegister(@RequestParam(name = "option_fk" ) long option_fk, HttpSession session, ReviewDetailFindDto dto, Model model) {
    MemberSessionDto memberSessionDto = (MemberSessionDto) session.getAttribute("signedMember");
    log.debug("memberSessionDto.getMember_pk()={}", memberSessionDto.getMember_pk());
    long member_fk = memberSessionDto.getMember_pk();
    dto.setMember_fk(member_fk);
    dto.setOption_fk(option_fk);
    List<ReviewDetailDto> reviewDetailDto = reviewService.selectReviewDetailViews(dto);
    log.debug("reviewDetailDto={}", reviewDetailDto);

    model.addAttribute("forReviewer", reviewDetailDto);

    return "review/reviewregister";
  }

  // 리뷰 작성
  @PostMapping("/reviewregister")
  public String reviewRegister(MultipartFile img_file, HttpSession session,
      HttpServletRequest req, ReviewRegisterDto dto) throws IOException {
    MemberSessionDto memberSessionDto = (MemberSessionDto) session.getAttribute("signedMember");
    dto.setMember_fk(memberSessionDto.getMember_pk());

    if (!img_file.isEmpty()) {
      String imgPath = ftpImgLoaderUtil.upload(img_file, req.getServletPath());
      dto.setImg(imgPath);
    } else {
      dto.setImg("");
    }
    reviewService.insertReview(dto);

    return "redirect:/";
  }  
  
}
