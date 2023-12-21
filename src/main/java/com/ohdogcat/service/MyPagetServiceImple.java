package com.ohdogcat.service;

import com.ohdogcat.dto.member.MemberChangeInfoDto;
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

    @Override
    public Boolean updateUserInfo(MemberChangeInfoDto dto) throws IllegalArgumentException {
        String phone = memberDao.getMemberPassword(dto.toMember());

        if (phone == null) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        if (phone.equals(dto.getPhone())) {
            dto.setPhone(null);
        }

        return memberDao.updateUserInfo(dto.toMember()) == 1;
    }
}
