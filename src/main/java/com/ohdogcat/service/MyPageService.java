package com.ohdogcat.service;

import com.ohdogcat.dto.member.MemberAddressUpdateDto;
import com.ohdogcat.dto.member.MemberChangeInfoDto;
import com.ohdogcat.dto.member.MemberInfoDto;
import com.ohdogcat.dto.purchase.PurchaseListDto;
import com.ohdogcat.dto.purchase.PurchaseListPagenationDto;
import java.util.List;

public interface MyPageService {

    MemberInfoDto getMemberMyPageInfo(Long memberPk);

    Boolean updateUserInfo(MemberChangeInfoDto dto);

    Boolean updateUserAddress(MemberAddressUpdateDto dto);

    List<PurchaseListDto> getMemberPurchaseList(PurchaseListPagenationDto pageInfo);

}
