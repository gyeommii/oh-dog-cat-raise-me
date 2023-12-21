package com.ohdogcat.service;

import com.ohdogcat.dto.member.MemberInfoDto;
import com.ohdogcat.model.Address;
import com.ohdogcat.model.Member;
import com.ohdogcat.repository.AddressDao;
import com.ohdogcat.repository.MemberDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class MyPagetServiceImple implements MyPageService {

    private final MemberDao memberDao;
    private final AddressDao addressDao;

    @Override
    public MemberInfoDto getMemberMyPageInfo(Long memberPk) {
        MemberInfoDto memberInfoDto;
        Member member = memberDao.getMemberMyPageInfo(memberPk);
        memberInfoDto = MemberInfoDto.fromMember(member);

        if (member.getAddress_fk() == null) {
            return memberInfoDto;
        }

        Address memberAddress = addressDao.getAddressByAddressPk(member.getAddress_fk());
        MemberInfoDto.fromAddress(memberInfoDto, memberAddress);
        return memberInfoDto;
    }
}
