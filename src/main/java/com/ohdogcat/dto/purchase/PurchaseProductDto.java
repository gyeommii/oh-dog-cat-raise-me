package com.ohdogcat.dto.purchase;

import com.ohdogcat.model.PurchaseProduct;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PurchaseProductDto {
    private List<PurchaseProduct> purchaseProducts;
    private Long purchasePk;
}
