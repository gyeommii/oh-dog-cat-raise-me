package com.ohdogcat.web;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ohdogcat.dto.cart.CartAddDto;
import com.ohdogcat.dto.member.MemberSessionDto;
import com.ohdogcat.service.CartService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {
	private final CartService cartService;

	@PostMapping("/add")
	public ResponseEntity<String> addCart(@RequestBody List<CartAddDto> cartAddDto, HttpSession session){
		log.debug("addCart(cartAddDto ={})",cartAddDto);
		
		MemberSessionDto signedMember = (MemberSessionDto) session.getAttribute("signedMember");
		if(signedMember == null) {
			return ResponseEntity.ok("memberNo");
		}
		
		long memberFk = signedMember.getMember_pk();
		boolean result = cartService.saveCartList(cartAddDto, memberFk);
		if(result == true) {
			return ResponseEntity.ok("MY");
		} else {
			return ResponseEntity.ok("MN");
		}
	}

}


