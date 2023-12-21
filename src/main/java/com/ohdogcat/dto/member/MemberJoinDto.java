package com.ohdogcat.dto.member;


import com.ohdogcat.model.Address;
import com.ohdogcat.model.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberJoinDto {

    private String member_id;
    private String password;
    private String email;
    private String phone;
    private String address;
    private String detail_addr;
    private String zonecode;
    private String recipient;

    public Address getAddress() {

        if (address.isEmpty() || detail_addr.isEmpty() || zonecode.isEmpty()
            || recipient.isEmpty()) {
            return null;
        }

        return Address.builder()
            .address(address)
            .detail_addr(detail_addr)
            .zonecode(zonecode)
            .recipient(recipient).build();
    }

    public Member getMember() {
        return Member.builder()
            .member_id(member_id)
            .password(password)
            .email(email)
            .phone(phone)
            .build();
    }
}
