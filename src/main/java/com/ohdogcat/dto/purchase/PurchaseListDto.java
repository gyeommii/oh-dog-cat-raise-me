package com.ohdogcat.dto.purchase;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseListDto {

    private Long purchase_pk;
    private Long status_pk;
    private Long total_price;
    private String order_name;
    private String pay_method;
    private String purchase_status;
    private String img_url;
    private LocalDateTime purchase_date;

}
