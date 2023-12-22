package com.ohdogcat.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ohdogcat.dto.cart.CartAddDto;
import com.ohdogcat.model.Cart;
import com.ohdogcat.repository.CartDao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class CartService {
	private final CartDao cartDao;

	public String addTocart(List<CartAddDto> cartAddDto, long memberFk) {
		log.debug("addTocart(member_fk ={})", memberFk);

		List<Cart> cartList = new ArrayList<>();
		String addResult = "";

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

			// 장바구니에 이미 있는 상품이라면,
			if(cart != null) {
				long countTotal = cart.getCount() + cartItem.getCount(); // 기존 수량 + 추가 수량
				long stock = cartDao.selectStockByOption(cart.getOption_fk());
				log.debug("countTotal={}, stock={}", countTotal, stock);

				// 1. countTotal : 현재 재고를 넘으면 안됨 . 재고 넘으면 바로 리턴!
				// 2. countTotal : 재고가 있다면, 최대 갯수 제한 10개 . 최대 갯수 넘으면 바로 리턴!
				if(countTotal > stock) {
					return addResult = "overStock"; 
				} else if(countTotal > 10) {
					return addResult = "overCount"; 
				} else {
					log.debug("updateCartCount()");
					cartDao.updateCartCount(cartItem);
					addResult = "add";
				}
				
			// 장바구니에 없는 상품이라면
			} else {
				log.debug("insertCartNewItem()");
				cartDao.insertCartNewItem(cartItem);
				addResult = "add";
			}
		}
		return addResult;
	}
}
