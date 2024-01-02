package com.ohdogcat.dto.purchase;

import com.ohdogcat.model.PurchaseProduct;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OptionInfoToCreateOrderDto {
//   구매 진행 시 내부에 받는 옵션과 수량 정보
    private Long option_fk;
    private Integer count;

    public PurchaseProduct toPurchaseProduct (Long purchase_pk) {
        return PurchaseProduct.builder().option_fk(option_fk).count(count).purchase_fk(purchase_pk).build();
    }
}
