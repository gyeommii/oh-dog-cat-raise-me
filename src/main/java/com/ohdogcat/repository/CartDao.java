package com.ohdogcat.repository;

import com.ohdogcat.model.Cart;

public interface CartDao {

	Cart selectCartByMemberAndOption(Cart cartItem);

	int insertCartNewItem(Cart cartItem);

	int updateCartCount(Cart cartItem);

}
