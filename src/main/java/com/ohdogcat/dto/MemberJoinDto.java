package com.ohdogcat.dto;


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
    private String jibun_addr;
    private String buildingName;
    private String detail_addr;
}
