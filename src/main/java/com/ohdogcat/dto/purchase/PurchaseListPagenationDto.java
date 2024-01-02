package com.ohdogcat.dto.purchase;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PurchaseListPagenationDto {

    private Long member_fk;
    private Integer limit = 10;
    private Integer offset = 0;
    private Integer curPage;

}
