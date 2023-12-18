package com.ohdogcat.web;

import com.ohdogcat.dto.member.MemberSessionDto;
import com.ohdogcat.service.KakaoMemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
