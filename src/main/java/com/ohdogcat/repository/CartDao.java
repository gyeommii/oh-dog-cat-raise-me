package com.ohdogcat.repository;

import java.util.List;

import com.ohdogcat.dto.cart.CartListDto;
import com.ohdogcat.dto.cart.CartUpdateOptionDto;
import com.ohdogcat.model.Cart;

public interface CartDao {

	Cart selectCartByMemberAndOption(Cart cartItem);

	int insertCartNewItem(Cart cartItem);

	int updateAmountCount(Cart cartItem);

	Long selectStockByOption(long option_fk);

	List<CartListDto> selectCartListByMember(Long member);

	int deleteCartItemByOptionAndMember(Cart cartItem);

	int updateNewCount(Cart cartItem);
	
	int updateOption(CartUpdateOptionDto updateOptionDto);

}
