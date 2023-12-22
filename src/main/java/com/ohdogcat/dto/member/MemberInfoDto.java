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
public class MemberInfoDto {

    private String member_id;
    private String email;
    private String phone;
    private String address;
    private Long address_pk;
    private String kakao_client_id;
    private String detail_addr;
    private String zonecode;
    private String recipient;

    public static void fromMember(MemberInfoDto target, Member origin) {
        target.setMember_id(origin.getMember_id());
        target.setEmail(origin.getEmail());
        target.setPhone(origin.getPhone());
        target.setKakao_client_id(origin.getKakao_client_id());
    }

    public static MemberInfoDto fromMember(Member origin) {
        MemberInfoDto dto = MemberInfoDto.builder().build();
        fromMember(dto, origin);
        return dto;
    }

    public static void fromAddress(MemberInfoDto target, Address origin) {
        target.setAddress_pk(origin.getAddress_pk());
        target.setAddress(origin.getAddress());
        target.setDetail_addr(origin.getDetail_addr());
        target.setZonecode(origin.getZonecode());
        target.setRecipient(origin.getRecipient());
    }

    public static MemberInfoDto fromAddress(Address origin) {
        MemberInfoDto dto = MemberInfoDto.builder().build();
        fromAddress(dto, origin);
        return dto;
    }
}
