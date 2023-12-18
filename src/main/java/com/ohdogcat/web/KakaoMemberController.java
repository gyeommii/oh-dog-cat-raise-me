package com.ohdogcat.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.ohdogcat.dto.MemberSessionDto;
import com.ohdogcat.service.KakaoMemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/user/signin")
public class KakaoMemberController {

  private final KakaoMemberService kakaoMemberService; // 의존성 주입(ReqiredArgsConstructor)

  @GetMapping("/kakaoLogin")
  public String kakaoLogin(HttpSession session,
      @RequestParam(value = "code", required = false) String code) throws Exception {

    String token = kakaoMemberService.getAccessToken(code);
    MemberSessionDto tokenData = kakaoMemberService.getUserInfo(token);
    log.debug("token={}", token);
    log.debug("tokenData={}", tokenData);

    session.setAttribute("signedMember", tokenData);

    return "redirect:/";

  }

}
