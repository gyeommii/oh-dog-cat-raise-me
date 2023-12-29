package com.ohdogcat.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ohdogcat.dto.cart.CartAddDto;
import com.ohdogcat.dto.cart.CartListDto;
import com.ohdogcat.dto.cart.CartUpdateOptionDto;
import com.ohdogcat.dto.member.MemberSessionDto;
import com.ohdogcat.model.Cart;
import com.ohdogcat.repository.CartDao;

import io.micrometer.common.util.internal.logging.AbstractInternalLogger;
import jakarta.servlet.jsp.jstl.sql.Result;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class CartService {
	private final CartDao cartDao;
	
	// 상세 페이지에서 장바구니 담을 때
	public String addTocart(List<CartAddDto> cartAddDto, long memberFk) {
		log.debug("addTocart(member_fk ={})", memberFk);

		List<Cart> cartList = new ArrayList<>();
		String result = "";

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
					return result = "overStock"; 
				} else if(countTotal > 10) {
					return result = "overCount"; 
				} else {
					log.debug("updateCartCount()");
					cartDao.updateAmountCount(cartItem);
					result = "add";
				}
				
			// 장바구니에 없는 상품이라면
			} else {
				log.debug("insertCartNewItem()");
				cartDao.insertCartNewItem(cartItem);
				result = "add";
			}
		}
		return result;
	}

	public List<CartListDto> getCartList(Long member) {
		log.debug("getCartList()");
		List<CartListDto> cartList = cartDao.selectCartListByMember(member);
		
		// 장바구니 옵션 수량 & 재고 수량 비교
		for(CartListDto list : cartList) {
			// 옵션 수량 > 재고 수량이면 재고 수량으로 변경
			if(list.getCount() > list.getStock()) {
				cartDao.updateNewCount(Cart.builder()
						.member_fk(member)
						.option_fk(list.getOption_fk())
						.count(list.getStock())
						.build());
			// 품절인 경우 장바구니 수량 0
			} else if(list.getStock() == 0) {
				cartDao.updateNewCount(Cart.builder()
						.member_fk(member)
						.option_fk(list.getOption_fk())
						.count(0L)
						.build());
			// 품절 상품의 재고가 들어온 경우 장바구니 수량 1
			} else if(list.getCount() == 0 && list.getStock() > 0 ) {
				cartDao.updateNewCount(Cart.builder()
						.member_fk(member)
						.option_fk(list.getOption_fk())
						.count(1L)
						.build());
			}
		}
		
		cartList = cartDao.selectCartListByMember(member);
		return cartList;
	}

	public int deleteCartItem(Long option_fk, Long member) {
		log.debug("deleteCartItem(option ={}, memeber={})", option_fk, member);
		int result = cartDao.deleteCartItemByOptionAndMember(Cart.builder()
				.option_fk(option_fk)
				.member_fk(member)
				.build());
		return result;
	}
	
	// 장바구니 페이지에서 수량 변경할 때
	public String updateCartCount(long memberFk, Cart cart) {
		log.debug("updateCartItem()");
		String result = "";
		cart.setMember_fk(memberFk);
		long stock = cartDao.selectStockByOption(cart.getOption_fk());

		if(cart.getCount() > stock) {
			return result = "overStock"; 
		} else if(cart.getCount() > 10) {
			return result = "overCount"; 
		} else {
			cartDao.updateNewCount(cart);
			result = "add";
		}	
		return result;
	}

	public int updateCartOpiton(CartUpdateOptionDto updateOptionDto) {
		log.debug("updateCartOpiton()");
		int result = 0;
		Cart cart = cartDao.selectCartByMemberAndOption(updateOptionDto.toEntity());
		if(cart != null) {
			return result;
		} else {
			result = cartDao.updateOption(updateOptionDto);
			return result;
		}
	}
}
