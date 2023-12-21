package com.ohdogcat.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ohdogcat.dto.cart.CartAddDto;
import com.ohdogcat.dto.member.MemberSessionDto;
import com.ohdogcat.model.Cart;
import com.ohdogcat.repository.CartDao;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class CartService {
	private final CartDao cartDao;

	public boolean saveCartList(List<CartAddDto> cartAddDto, long memberFk) {
		log.debug("saveCartList(member_fk ={})", memberFk);
		
		List<Cart> cartList = new ArrayList<>();

		for (CartAddDto cartItem : cartAddDto) {
			Cart cart = Cart.builder()
					.member_fk(memberFk)
					.option_fk(cartItem.getOption_fk())
					.count(cartItem.getCount())
					.build();
			cartList.add(cart);
		}
		
		for (Cart cartItem : cartList) {
			
			log.debug("Cart(cartItem={})",cartItem);
			Cart cart = cartDao.selectCartByMemberAndOption(cartItem);
			
			if(cart != null) {
				log.debug("updateCartCount()");
				cartDao.updateCartCount(cartItem);
			} else {
				log.debug("insertCartNewItem()");
				cartDao.insertCartNewItem(cartItem);
			}
		}
		
		return false;	


	}
}
