package com.ohdogcat.service;

import com.ohdogcat.dto.member.MemberAddressUpdateDto;
import com.ohdogcat.dto.purchase.MemberPointDto;
import com.ohdogcat.dto.purchase.OptionInfoToCreateOrderDto;
import com.ohdogcat.dto.purchase.OptionOrderDto;
import com.ohdogcat.dto.purchase.OrderInfoDto;
import com.ohdogcat.dto.purchase.OrderParameterDto;
import com.ohdogcat.exception.OutOfStockExeption;
import com.ohdogcat.model.Address;
import com.ohdogcat.model.Member;
import com.ohdogcat.model.Membership;
import com.ohdogcat.model.Payment;
import com.ohdogcat.model.ProductOption;
import com.ohdogcat.model.Purchase;
import com.ohdogcat.model.PurchaseProduct;
import com.ohdogcat.repository.AddressDao;
import com.ohdogcat.repository.MemberDao;
import com.ohdogcat.repository.ProductDao;
import com.ohdogcat.repository.PurchaseDao;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PurchaseService {

    private final MemberDao memberDao;
    private final AddressDao addressDao;
    private final ProductDao productDao;
    private final PurchaseDao purchaseDao;

    public Long addAddress(MemberAddressUpdateDto addressInfo) {
        Address address = addressInfo.toAddress();
        Long result = addressDao.registerAddress(address);
        log.debug("result={}", result);
        return address.getAddress_pk();
    }


    public Map<String, Object> checkOrderInfoToPurchase(Long memberPk, List<Long> options_in_cart) {
//        Product 정보

        Map<String, Object> result = new HashMap<>();

        retrieveMemberInfo(memberPk, result);

        OrderParameterDto infoToOrder = OrderParameterDto.builder()
            .member_fk(memberPk)
            .optionList(options_in_cart)
            .build();

        List<OptionOrderDto> products = productDao.selectProductInfoForOrder(infoToOrder);
        result.put("products", products);

        calculatePriceAndCreateOrderName(products,  result);

        return result;
    }

    private void calculatePriceAndCreateOrderName(List<OptionOrderDto> products,
        Map<String, Object> result) {
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
    }

    public Map<String, Object> getOrderFromDetail(Long memberPk,
        List<OptionInfoToCreateOrderDto> optionList) {
//        Product 정보

        Map<String, Object> result = new HashMap<>();

        retrieveMemberInfo(memberPk, result);

        List<OptionOrderDto> products = new ArrayList<>();

        for (OptionInfoToCreateOrderDto dto : optionList) {
            OptionOrderDto product = productDao.selectProductInfoForOrderFromDetail(
                dto.getOption_fk());
            product.setCount(dto.getCount());
            products.add(product);
        }

        result.put("products", products);

        calculatePriceAndCreateOrderName(products, result);

        return result;
    }

    private void retrieveMemberInfo(Long memberPk, Map<String, Object> result) {
        Member member = memberDao.getUserInfoAtOrder(memberPk);
        result.put("member", member);

        Address address = addressDao.getAddressByAddressPk(member.getAddress_fk());
        result.put("address", address);

        Membership membership = memberDao.getUserMembership(member);
        result.put("membership", membership);

        List<Address> addressOrdered = addressDao.getAddressOrdered(memberPk);
        result.put("addressOrdered", addressOrdered);
    }

    @Transactional(rollbackFor = {RuntimeException.class})
    public void createOrderThroughCart(OrderInfoDto infoToOrder) {
        if (infoToOrder.getOrderType().equals("c")) {
            List<PurchaseProduct> purchaseProducts = createOrderCommonFlow(infoToOrder);
//          카트에서 제거
            purchaseProducts.forEach(el -> {

            });
        } else if (infoToOrder.getOrderType().equals("d")) {
            createOrderCommonFlow(infoToOrder);
        }
    }

    private List<PurchaseProduct> createOrderCommonFlow(OrderInfoDto infoToOrder)
        throws OutOfStockExeption {
        Purchase purchase = infoToOrder.toPurchase();
//          주문 생성
        Long result = purchaseDao.insertPurchase(purchase);
        log.debug("구매 생성 :: {}", result == 1 ? "success" : "fail");

        List<PurchaseProduct> purchaseProducts = infoToOrder.toPurchaseProducts(
            purchase.getPurchase_pk());

        Payment payment = infoToOrder.toPayment(purchase.getPurchase_pk());
//            멤버 멤버쉽 가져오기
        Membership membership = memberDao.getUserMembership(
            Member.builder().member_pk(infoToOrder.getMemberFk()).build());
//            포인트 적립금 계산

        MemberPointDto memberPointDto = MemberPointDto.toMemberPointDto(payment,
            membership.getPoint_rate(),
            purchase.getMember_fk());

        payment.setAccumulated_point(memberPointDto.getAccumulated_point());
//            결제 내역 생성
        Long resultPayment = purchaseDao.insertPayment(payment);
        log.debug("resultPayment={}", resultPayment);

        purchaseProducts.forEach(purchaseDao::insertPurchasedProduct);
//          유저 포인트 사용
        if (payment.getUsed_point() > 0) {
            memberDao.spendPoint(memberPointDto);
        }
//          유저 포인트 적립
        if (payment.getAccumulated_point() > 0) {
            memberDao.accumulatePoint(memberPointDto);
        }

//          옵션 재고 변경
        purchaseProducts.forEach(el -> {
            ProductOption optionInfo = productDao.selectOptionByOptionPk(el.getOption_fk());

            if (optionInfo.getStock() < el.getCount()) {
                throw new OutOfStockExeption(
                    "선택 수량이 현재 재고(" + optionInfo.getStock() + ")보다 많습니다. 재고 수량을 확인해주세요.");
            }

            Long stockUpdateResult = productDao.updateOptionStock(el);
            log.debug("stockUpdateResult={}", stockUpdateResult);
        });

        return purchaseProducts;
    }

}
