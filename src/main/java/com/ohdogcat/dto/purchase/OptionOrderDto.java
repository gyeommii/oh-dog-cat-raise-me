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
public class OptionOrderDto {

    //    get("/order/check") 정보 반환
    private Long option_fk;
    private Long product_fk;
    private Integer count;
    private Integer stock;
    private Integer price;
    private Integer isReviewWritten;
    private String product_name;
    private String img_url;
    private String option_name;

    public PurchaseProduct toPurchaseProduct() {
        return PurchaseProduct.builder().option_fk(option_fk).count(count).build();
    }
}