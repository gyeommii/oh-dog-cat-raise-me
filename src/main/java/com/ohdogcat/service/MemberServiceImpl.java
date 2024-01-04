package com.ohdogcat.service;

import com.ohdogcat.dto.member.MemberJoinDto;
import com.ohdogcat.dto.member.MemberLoginDto;
import com.ohdogcat.dto.member.MemberResetPasswordDto;
import com.ohdogcat.dto.member.MemberSessionDto;
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
public class MemberServiceImpl implements MemberService {

    private final MemberDao memberDao;
    private final AddressDao addressDao;

    @Override
    public boolean checkMemberIdUnique(String memberId) {
        log.debug("memberId = " + memberId);
        Integer cnt = memberDao.checkMemberIdUnique(memberId);
        log.debug("cnt = " + cnt);
        return (cnt == 0);
    }

    @Override
    public boolean checkEmailUnique(String email) {
        Integer cnt = memberDao.checkEmailUnique(email);
        log.debug("cnt={}", cnt);
        return (cnt == 0);
    }

    @Override
    public boolean join(MemberJoinDto dto) {
        log.debug("join={}", dto);
        Address address = dto.getAddress();
        Member member = dto.getMember();

        if (address != null) {
            Long result = addressDao.registerAddress(address);
            log.debug("result = {}", result);
            log.debug("address_pk={}", address.getAddress_pk());
            member.setAddress_fk(address.getAddress_pk());
        }

        Integer isMemberCreated = memberDao.join(member);
        log.debug("isMemberCreated::{}", isMemberCreated);

        return isMemberCreated == 1;
    }

    @Override
    public MemberSessionDto signin(MemberLoginDto dto) {
        log.debug("dto={}", dto);
        Member memberInDb = memberDao.login(dto.toMember());
        return MemberSessionDto.fromMember(memberInDb);
    }

    @Override
    public String findMemberId(String email) {
        String member_id = memberDao.findMemberId(email);

        return member_id;
    }

    @Override
    public boolean resetPassword(MemberResetPasswordDto dto) {
        Integer re =  memberDao.updatePassword(dto.toMember());
        log.debug("re={}", re);
        return re == 1;
    }
}
