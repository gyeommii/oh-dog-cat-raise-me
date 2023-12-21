package com.ohdogcat.web;

import com.ohdogcat.dto.member.MemberInfoDto;
import com.ohdogcat.dto.member.MemberSessionDto;
import com.ohdogcat.service.MyPageService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
@RequestMapping("/mypage")
public class MyPageController {

    private final MyPageService service;

    @GetMapping("/member")
    public void getMyPage(HttpSession session, Model model) {
        MemberSessionDto signedMember = (MemberSessionDto) session.getAttribute("signedMember");

        MemberInfoDto memberInfo = service.getMemberMyPageInfo(signedMember.getMember_pk());
        model.addAttribute("memberInfo", memberInfo);
    }

    @ResponseBody
    @PatchMapping("/member")
    public void updateAddress(HttpSession session) {
    }

}
