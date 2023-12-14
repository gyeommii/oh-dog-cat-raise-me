package com.ohdogcat.service;

import com.ohdogcat.dto.MemberJoinDto;
import com.ohdogcat.model.Address;
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
        Integer result = addressDao.registerAddress(address);
        log.debug("result = {}", result);
        log.debug("address_pk={}", address.getAddress_pk());

        return true;
    }
}
