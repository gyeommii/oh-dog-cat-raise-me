package com.ohdogcat.web;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ohdogcat.dto.cart.CartAddDto;
import com.ohdogcat.dto.cart.CartListDto;
import com.ohdogcat.dto.cart.CartUpdateOptionDto;
import com.ohdogcat.dto.member.MemberSessionDto;
import com.ohdogcat.model.Cart;
import com.ohdogcat.service.CartService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {
	private final CartService cartService;

	@PostMapping("/add")
	@ResponseBody
	public ResponseEntity<String> addCart(@RequestBody List<CartAddDto> cartAddDto, HttpSession session){
		log.debug("addCart(cartAddDto ={})",cartAddDto);
		
		MemberSessionDto signedMember = (MemberSessionDto) session.getAttribute("signedMember");
		if(signedMember == null) {
			return ResponseEntity.ok("notLogin");
		}
		
		long memberFk = signedMember.getMember_pk();
		String result = cartService.addTocart(cartAddDto, memberFk);
		
		return ResponseEntity.ok(result); 
	
	}
	
	@GetMapping("/list")
	public void toCartList() {
		log.debug("toCartList()");	
	}
	
	@GetMapping("/list/all/items")
	@ResponseBody
	public ResponseEntity<List<CartListDto>> getCart(HttpSession session){
		log.debug("getCart()");
		MemberSessionDto signedMember = (MemberSessionDto) session.getAttribute("signedMember");
		long memberFk = signedMember.getMember_pk();
		List<CartListDto> cartList = cartService.getCartList(memberFk);
		log.debug("cartList={}", cartList);
		return ResponseEntity.ok(cartList);
	}
	
	@DeleteMapping("/list/delete/{option_fk}")
	@ResponseBody
	public ResponseEntity<Integer> deleteCart(@PathVariable Long option_fk, HttpSession session){
		log.debug("deleteCart()");
		MemberSessionDto signedMember = (MemberSessionDto) session.getAttribute("signedMember");
		long memberFk = signedMember.getMember_pk();
		int result = cartService.deleteCartItem(option_fk, memberFk);
		log.debug("update결과={}",result);
		return ResponseEntity.ok(result);
		
	}
		
	
	@PutMapping("/list/update/{option_fk}")
	@ResponseBody
	public ResponseEntity<String> updateCount(@PathVariable Long option_fk,@RequestBody Cart cart, HttpSession session){
		log.debug("updateCart(option_fk={},cart={})",option_fk, cart);
		MemberSessionDto signedMember = (MemberSessionDto) session.getAttribute("signedMember");
		long memberFk = signedMember.getMember_pk();
		String reuslt = cartService.updateCartCount(memberFk, cart);
		return ResponseEntity.ok(reuslt);
	}
	
	@PutMapping("/list/update/{beforeOption_fk}/{afterOption_fk}")
	@ResponseBody
	public ResponseEntity<Integer> updateOption(@PathVariable Long beforeOption_fk ,@PathVariable Long afterOption_fk, HttpSession session){
		log.debug("updateOption={beforeOption_fk={}, afterOption_fk={}}", beforeOption_fk, afterOption_fk);
		MemberSessionDto signedMember = (MemberSessionDto) session.getAttribute("signedMember");
		long memberFk = signedMember.getMember_pk();
		CartUpdateOptionDto updateOptionDto = 
				CartUpdateOptionDto.builder()
				.beforeOption_fk(beforeOption_fk)
				.afterOption_fk(afterOption_fk)
				.member_fk(memberFk)
				.build();
		int result = cartService.updateCartOpiton(updateOptionDto);
		return ResponseEntity.ok(result);
	}
	

}


