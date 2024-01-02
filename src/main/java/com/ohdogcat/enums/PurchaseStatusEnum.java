package com.ohdogcat.enums;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor

public enum PurchaseStatusEnum {
    BEFORE_PAYMENT(1),
    PAYMENT_COMPLETED(2),
    PREPARE_DELIVERY(3),
    SHIPPING(4),
    DELIVERY_COMPLETED(5),
    CONFIRMED_PURCHASE(6),
    CANCEL_REQUESTED(7),
    CANCELED(8);

    private final Integer status_pk;

    public Integer getStatus_pk() {
        return status_pk;
    }
}
