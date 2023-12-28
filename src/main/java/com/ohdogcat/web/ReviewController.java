package com.ohdogcat.web;

import java.io.IOException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import com.ohdogcat.dto.member.MemberSessionDto;
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
  public String reviewRegister() {
    
    return "review/reviewregister";
  }
  
  //리뷰 작성  
  @PostMapping("/reviewregister")
  public String reviewRegister(MultipartFile img_file, ReviewRegisterDto dto, HttpServletRequest req, HttpSession session) throws IOException{
    log.debug("reviewWrite(dto={}, session={}", dto, session);
    MemberSessionDto memberSessionDto = (MemberSessionDto) session.getAttribute("signedMember");
    if(memberSessionDto != null) {
      long member_fk = memberSessionDto.getMember_pk();
      dto.setMember_fk(member_fk);
    }
    
    if (!img_file.isEmpty()) {
      log.debug("MultipartFile img={}", img_file);

      String imgPath = ftpImgLoaderUtil.upload(img_file, req.getServletPath());
      dto.setImg(imgPath);
      log.debug("reviewRegister(success)");
      log.debug("reviewRegister(dto={})", dto);

      return null;

    } else {
      dto.setImg("");
      
      
      return null;
    }
    
  }

}
