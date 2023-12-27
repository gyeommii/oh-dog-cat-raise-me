package com.ohdogcat.repository;

import com.ohdogcat.model.Address;
import java.util.List;

public interface AddressDao {

    Long registerAddress(Address address);

    Address getAddressByAddressPk(Long addressPk);

    List<Address> getAddressOrdered(Long memberFk);

}
