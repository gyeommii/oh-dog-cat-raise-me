package com.ohdogcat.service;

import com.ohdogcat.dto.member.MemberAddressUpdateDto;
import com.ohdogcat.dto.member.MemberChangeInfoDto;
import com.ohdogcat.dto.member.MemberInfoDto;

public interface MyPageService {

    MemberInfoDto getMemberMyPageInfo(Long memberPk);

    Boolean updateUserInfo(MemberChangeInfoDto dto);

    Boolean updateUserAddress(MemberAddressUpdateDto dto);

}
