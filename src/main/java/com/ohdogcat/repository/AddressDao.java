package com.ohdogcat.repository;

import com.ohdogcat.model.Address;

public interface AddressDao {

    Long registerAddress(Address address);

    Address getAddressByAddressPk(Long addressPk);

}
