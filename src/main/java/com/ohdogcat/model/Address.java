package com.ohdogcat.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Address {
    private Long address_pk;
    private String address;
    private String zonecode;
    private String detail_addr;
    private String recipient;
    private String created_date;
}
