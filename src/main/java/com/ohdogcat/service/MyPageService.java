package com.ohdogcat.service;

import com.ohdogcat.dto.member.MemberInfoDto;

public interface MyPageService {

    MemberInfoDto getMemberMyPageInfo(Long memberPk);

}
