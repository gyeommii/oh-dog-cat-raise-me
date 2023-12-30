package com.ohdogcat.service;

import java.util.List;

import com.ohdogcat.dto.member.MemberAddressUpdateDto;
import com.ohdogcat.dto.member.MemberChangeInfoDto;
import com.ohdogcat.dto.member.MemberInfoDto;
import com.ohdogcat.dto.wishlist.WishListDto;
import com.ohdogcat.model.WishList;

public interface MyPageService {

    MemberInfoDto getMemberMyPageInfo(Long memberPk);

    Boolean updateUserInfo(MemberChangeInfoDto dto);

    Boolean updateUserAddress(MemberAddressUpdateDto dto);

	List<WishListDto> getWishiList(Long memberPk);
    

}
