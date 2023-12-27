package com.ohdogcat.service;

import com.ohdogcat.model.Address;
import com.ohdogcat.model.Member;
import com.ohdogcat.model.Membership;
import com.ohdogcat.repository.AddressDao;
import com.ohdogcat.repository.MemberDao;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final MemberDao memberDao;
    private final AddressDao addressDao;

    public Map<String, Object> checkOrderInfoToPurchase(Long memberPk) {
//        Product 정보

        Map<String, Object> result = new HashMap<>();

        Member member = memberDao.getUserInfoAtOrder(memberPk);
        result.put("member", member);

        Address address = addressDao.getAddressByAddressPk(member.getAddress_fk());
        result.put("address", address);

        Membership membership = memberDao.getUserMembership(member);
        result.put("membership", membership);

        return result;
    }


}
