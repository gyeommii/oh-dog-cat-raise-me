package com.ohdogcat.service;

import com.ohdogcat.dto.member.MemberAddressUpdateDto;
import com.ohdogcat.dto.member.MemberChangeInfoDto;
import com.ohdogcat.dto.member.MemberInfoDto;
import com.ohdogcat.dto.purchase.PurchaseListDto;
import com.ohdogcat.dto.purchase.PurchaseListPagenationDto;
import java.util.List;
import java.util.Map;

public interface MyPageService {

    MemberInfoDto getMemberMyPageInfo(Long memberPk);

    Boolean updateUserInfo(MemberChangeInfoDto dto);

    Boolean updateUserAddress(MemberAddressUpdateDto dto);

    Map<String, Object> getMemberPurchaseList(PurchaseListPagenationDto pageInfo);

}
