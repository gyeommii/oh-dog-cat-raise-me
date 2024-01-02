package com.ohdogcat.service;

import com.ohdogcat.dto.member.MemberAddressUpdateDto;
import com.ohdogcat.dto.purchase.MemberPointDto;
import com.ohdogcat.dto.purchase.OptionInfoToCreateOrderDto;
import com.ohdogcat.dto.purchase.OptionOrderDto;
import com.ohdogcat.dto.purchase.OrderInfoDto;
import com.ohdogcat.dto.purchase.OrderParameterDto;
import com.ohdogcat.dto.purchase.PurchaseProductDto;
import com.ohdogcat.exception.OutOfStockExeption;
import com.ohdogcat.model.Address;
import com.ohdogcat.model.Member;
import com.ohdogcat.model.Membership;
import com.ohdogcat.model.Payment;
import com.ohdogcat.model.ProductOption;
import com.ohdogcat.model.Purchase;
import com.ohdogcat.model.PurchaseProduct;
import com.ohdogcat.model.PurchaseStatus;
import com.ohdogcat.repository.AddressDao;
import com.ohdogcat.repository.CartDao;
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
//@Transactional(readOnly = true)
public class PurchaseService {

    private final MemberDao memberDao;
    private final AddressDao addressDao;
    private final ProductDao productDao;
    private final PurchaseDao purchaseDao;
    private final CartDao cartDao;


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

        List<OptionOrderDto> products = productDao.selectProductInfoForOrderFromCart(infoToOrder);
        result.put("products", products);

        calculatePriceAndCreateOrderName(products, result);

        return result;
    }

    private void calculatePriceAndCreateOrderName(List<OptionOrderDto> products,
        Map<String, Object> result) {
        int totalPrice = 0;

        for (OptionOrderDto item : products) {
            totalPrice += (item.getPrice() * item.getCount());
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
            product.setOption_fk(dto.getOption_fk());
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
    public Long createOrderThroughCart(OrderInfoDto infoToOrder) {
        Long purchasePk = 0l;
        if (infoToOrder.getOrderType().equals("c")) {
            log.debug("orderInfo={}", infoToOrder);
            PurchaseProductDto purchaseProductDto = createOrderCommonFlow(infoToOrder);
            log.debug("purchaseProducts={}", purchaseProductDto);
//          카트에서 제거

            purchasePk = purchaseProductDto.getPurchasePk();

            purchaseProductDto.getPurchaseProducts().forEach(purchaseProduct -> {
                purchaseProduct.setMember_fk(infoToOrder.getMemberFk());
                log.debug("purchaseProduct={}", purchaseProduct);
                cartDao.deleteCartItemByOptionAndMember(purchaseProduct);
            });
        } else if (infoToOrder.getOrderType().equals("d")) {
            PurchaseProductDto purchaseProductDto = createOrderCommonFlow(infoToOrder);
            purchasePk = purchaseProductDto.getPurchasePk();
        }

        return purchasePk;
    }

    private PurchaseProductDto createOrderCommonFlow(OrderInfoDto infoToOrder)
        throws OutOfStockExeption {
        PurchaseProductDto purchaseProductDto = new PurchaseProductDto();

        Purchase purchase = infoToOrder.toPurchase();
        log.debug("purchase={}", purchase);

//          주문 생성
        Long result = purchaseDao.insertPurchase(purchase);
        purchaseProductDto.setPurchasePk(purchase.getPurchase_pk());

        log.debug("구매 생성 :: {}", result == 1 ? "success" : "fail");

        List<PurchaseProduct> purchaseProducts = infoToOrder.toPurchaseProducts(
            purchase.getPurchase_pk());

        purchaseProductDto.setPurchaseProducts(purchaseProducts);

        Payment payment = infoToOrder.toPayment(purchase.getPurchase_pk());
//            멤버 멤버쉽 가져오기
        Membership membership = memberDao.getUserMembership(
            Member.builder().member_pk(infoToOrder.getMemberFk()).build());
//            포인트 적립금 계산

        MemberPointDto memberPointDto = MemberPointDto.toMemberPointDto(payment,
            membership.getPoint_rate(),
            purchase.getMember_fk());

        payment.setAccumulated_point(memberPointDto.getAccumulated_point());

        log.debug("payment={}", payment);
//            결제 내역 생성
        Long resultPayment = purchaseDao.insertPayment(payment);
        log.debug("resultPayment={}", resultPayment);

        purchaseProducts.forEach(purchaseDao::insertPurchasedProduct);
//          유저 포인트 사용
        if (payment.getUsed_point() > 0) {
            memberDao.spendPoint(memberPointDto);
        }
//          유저 포인트 적립은 이후 구매 확정 시 추가할 예정

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

        return purchaseProductDto;
    }

    public Map<String, Object> showPurchaseDetail(Long memberPk, Long purchasePk) {
        Map<String, Object> result = new HashMap<>();
        Member member = memberDao.getUserInfoAtOrder(memberPk);
        result.put("member", member);

//        주소 저장

        Purchase purchase = purchaseDao.getPurchaseInfo(purchasePk);
        result.put("purchase", purchase);

        Address address = addressDao.getAddressByAddressPk(purchase.getAddress_fk());
        result.put("address", address);

        List<OptionOrderDto> products = purchaseDao.getProductByPurchasePk(purchasePk);
        result.put("products", products);

        Payment payment = purchaseDao.retrievePaymentByPurchaseFk(purchasePk);
        result.put("payment", payment);

        PurchaseStatus purchaseStatus = purchaseDao.getPurchaseStatusByPk(purchase.getStatus_fk());
        result.put("purchaseStatus", purchaseStatus);

        return result;

    }

}
