package com.ohdogcat.dto.purchase;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PurchaseCancelInfoDto {
    private Long member_fk;
    private Long purchase_fk;
    private Long status_fk;
}
