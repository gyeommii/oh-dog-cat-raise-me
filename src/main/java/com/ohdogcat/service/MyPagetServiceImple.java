package com.ohdogcat.service;

import com.ohdogcat.dto.member.MemberAddressUpdateDto;
import com.ohdogcat.dto.member.MemberChangeInfoDto;
import com.ohdogcat.dto.member.MemberInfoDto;
import com.ohdogcat.model.Address;
import com.ohdogcat.model.Member;
import com.ohdogcat.repository.AddressDao;
import com.ohdogcat.repository.MemberDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
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
        Member member = dto.toMemberToCheck();

        String phone = memberDao.getMemberPhone(member);

        log.debug(phone);

        if (phone == null) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        log.debug("헤헤헤헤헿");
        if (phone.equals(dto.getPhone())) {
            dto.setPhone(null);
        }

        return memberDao.updateUserInfo(dto.toMemberToChange()) == 1;
    }

    @Override
    public Boolean updateUserAddress(MemberAddressUpdateDto dto) {
        log.debug("updateUserAddress(dto={})", dto);

        Address address = dto.toAddress();
        addressDao.registerAddress(address);

        Member memberToUpdateAddress = Member.builder()
            .member_pk(dto.getMember_pk())
            .address_fk(address.getAddress_pk()).build();

        Long result = memberDao.updateUserDefaultAddress(memberToUpdateAddress);

        return result == 1;
    }
}
