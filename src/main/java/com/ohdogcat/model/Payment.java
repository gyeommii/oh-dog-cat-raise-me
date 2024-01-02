package com.ohdogcat.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payment {
    private Long payment_pk;
    private Long purchase_fk;
    private Long total_price;
    private Long paid_price;
    private Long used_point;
    private Long accumulated_point;
    private String pay_method;
    private String merchant_uid;
    private Integer pay_success;
}