package com.ohdogcat.repository;

import com.ohdogcat.dto.member.MemberJoinDto;
import com.ohdogcat.model.Address;

public interface AddressDao {

    Integer registerAddress(Address address);

    Address getAddressByAddressPk(Long addressPk);

}
