package com.ohdogcat.web;

import com.ohdogcat.dto.member.MemberAddressUpdateDto;
import com.ohdogcat.dto.member.MemberChangeInfoDto;
import com.ohdogcat.dto.member.MemberInfoDto;
import com.ohdogcat.dto.member.MemberSessionDto;
import com.ohdogcat.dto.wishlist.WishListDto;
import com.ohdogcat.service.MyPageService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.Console;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    public ResponseEntity<String> updateMemberInfo(HttpSession session, @RequestBody MemberChangeInfoDto dto) {

        MemberSessionDto signedMember = (MemberSessionDto) session.getAttribute("signedMember");
        dto.setMember_pk(signedMember.getMember_pk());
        Boolean result = false;

        try {
            result = service.updateUserInfo(dto);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        if (Boolean.FALSE.equals(result)) {
            return ResponseEntity.badRequest().body("failed");
        }
        return ResponseEntity.ok("updated");
    }

    @ResponseBody
    @PatchMapping("/address")
    public ResponseEntity<String> updateAddress(HttpSession session,@RequestBody MemberAddressUpdateDto dto) {
        log.debug("updateAddress(dto={})", dto);
        MemberSessionDto signedMember = (MemberSessionDto) session.getAttribute("signedMember");

        dto.setMember_pk(signedMember.getMember_pk());

        Boolean result = service.updateUserAddress(dto);

        if (!result) {
            return ResponseEntity.badRequest().body("failed");
        }

        return ResponseEntity.ok("success");
        
    }
    
    /* 유정 */
    @GetMapping("/wishlist")
    public void getWishList(HttpSession session, Model model) {
    	log.debug("getWishList()");
    	MemberSessionDto signedMember = (MemberSessionDto) session.getAttribute("signedMember");
    	List<WishListDto> wishList = service.getWishiList(signedMember.getMember_pk());
    	log.debug("getWishList(count={})", wishList.size());
    	model.addAttribute("count", wishList.size());
    	model.addAttribute("wishList", wishList);
    }
   

}
