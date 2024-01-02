package com.ohdogcat.dto.purchase;

import com.ohdogcat.model.Payment;
import com.ohdogcat.model.Purchase;
import com.ohdogcat.model.PurchaseProduct;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderInfoDto {

    // 처음 request로 넘어오는 값
    private Long memberFk;
    private Long totalPrice;
    private Long pointUsed;
    private Long addressFk;
    private List<OptionInfoToCreateOrderDto> optionList;
    private Long paidPrice;
    private String orderName;
    private String payMethod;
    private String paymentSuccess;
    private String merchantUid;
    private String orderType; // 카트에서 넘어오나 바로구매를 하나

    public Purchase toPurchase() {
        return Purchase.builder()
            .member_fk(memberFk)
            .address_fk(addressFk)
            .status_fk(payMethod.equals("무통장입금") ? 1 : 2)
            .total_price(totalPrice)
            .order_name(orderName)
            .build();
    }

    public List<PurchaseProduct> toPurchaseProducts(Long purchase_pk) {
        List<PurchaseProduct> result = new ArrayList<>();
        for (OptionInfoToCreateOrderDto optionInfoToCreateOrderDto : optionList) {
            result.add(optionInfoToCreateOrderDto.toPurchaseProduct(purchase_pk));
        }
        return result;
    }

    public Payment toPayment(Long purchase_pk) {
        return Payment.builder()
            .purchase_fk(purchase_pk)
            .total_price(totalPrice)
            .paid_price(paidPrice)
            .used_point(pointUsed)
            .pay_method(payMethod)
            .merchant_uid(merchantUid)
            .pay_success(paymentSuccess.equals("success") ? 1 : 0)
            .build();
    }

    private Long getPurchaseStatus() {
        if (paymentSuccess.equals("success")) {
            return 2L;
        } else {
            return 1L;
        }
    }
}
