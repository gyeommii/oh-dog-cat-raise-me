package com.ohdogcat.repository;

import java.util.List;

import com.ohdogcat.dto.wishlist.WishListDto;
import com.ohdogcat.model.WishList;

public interface WishListDao {

	List<WishListDto>selectWishListByMember(Long member_fk);

	WishList selectWishByMemberAndProduct(WishList wishList);

	boolean deleteWishByMemberAndProduct(WishList wishList);

	boolean insertWish(WishList wishList);

}
