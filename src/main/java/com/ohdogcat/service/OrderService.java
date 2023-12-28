package com.ohdogcat.service;

import com.ohdogcat.dto.order.OptionOrderDto;
import com.ohdogcat.dto.order.OrderInfoDto;
import com.ohdogcat.dto.order.OrderParameterDto;
import com.ohdogcat.model.Address;
import com.ohdogcat.model.Member;
import com.ohdogcat.model.Membership;
import com.ohdogcat.repository.AddressDao;
import com.ohdogcat.repository.MemberDao;
import com.ohdogcat.repository.ProductDao;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderService {

    private final MemberDao memberDao;
    private final AddressDao addressDao;
    private final ProductDao productDao;

    public Map<String, Object> checkOrderInfoToPurchase(Long memberPk, List<Long> options_in_cart) {
//        Product 정보

        Map<String, Object> result = new HashMap<>();

        Member member = memberDao.getUserInfoAtOrder(memberPk);
        result.put("member", member);

        Address address = addressDao.getAddressByAddressPk(member.getAddress_fk());
        result.put("address", address);

        Membership membership = memberDao.getUserMembership(member);
        result.put("membership", membership);

        List<Address> addressOrdered = addressDao.getAddressOrdered(memberPk);
        result.put("addressOrdered", addressOrdered);

        OrderParameterDto infoToOrder = OrderParameterDto.builder()
            .member_fk(memberPk)
            .optionList(options_in_cart)
            .build();

        List<OptionOrderDto> products = productDao.selectProductInfoForOrder(infoToOrder);
        result.put("products", products);

        int totalPrice = 0;

        for (OptionOrderDto item : products) {
            totalPrice += item.getPrice();
        }

        result.put("totalPrice", totalPrice);
        OptionOrderDto mainPd = products.get(0);
        String orderName =
            products.size() > 1 ? mainPd.getProduct_name() + " 외 " + products.size() + "개"
                : mainPd.getProduct_name();
        result.put("orderName", orderName);
        return result;
    }

    public void createOrderThroughCart (Long memberPk, OrderInfoDto infoToOrder) {



    }

}
