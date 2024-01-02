package com.ohdogcat.dto.purchase;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PurchaseStatusChangeDto {
    private Integer status_fk;
    private Long purchase_pk;
}
