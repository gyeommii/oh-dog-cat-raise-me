package com.ohdogcat.model;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Purchase {
    private Long purchase_pk;
    private Long member_fk;
    private Long status_fk;
    private Long address_fk;
    private Long total_price;
    private String order_name;
    private LocalDateTime purchase_date;
}

