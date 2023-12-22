package com.ohdogcat.dto.member;

import com.ohdogcat.model.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberAddressUpdateDto {
    private Long member_pk;
    private String zonecode;
    private String address;
    private String detail_addr;
    private String recipient;

    public Address toAddress () {
        return Address.builder()
            .zonecode(zonecode)
            .address(address)
            .detail_addr(detail_addr)
            .recipient(recipient)
            .build();
    }
}
