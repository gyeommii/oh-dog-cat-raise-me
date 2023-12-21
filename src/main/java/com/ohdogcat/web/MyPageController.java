package com.ohdogcat.web;

import com.ohdogcat.dto.member.MemberChangeInfoDto;
import com.ohdogcat.dto.member.MemberInfoDto;
import com.ohdogcat.dto.member.MemberSessionDto;
import com.ohdogcat.service.MyPageService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
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
    public ResponseEntity<String> updateMemberInfo(HttpSession session, MemberChangeInfoDto dto) {
        MemberSessionDto signedUser = (MemberSessionDto) session.getAttribute("signedUser");
        dto.setMember_pk(signedUser.getMember_pk());
        Boolean result = false;
        log.debug("updateAddressupdateAddress={}", dto);
        try {
            result = service.updateUserInfo(dto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        if (Boolean.FALSE.equals(result)) {
            return ResponseEntity.badRequest().body("failed");
        }
        return ResponseEntity.ok("updated");
    }

    @ResponseBody
    @PatchMapping("/address")
    public ResponseEntity<String> updateAddress (HttpSession session) {
        return null;
    }

}
