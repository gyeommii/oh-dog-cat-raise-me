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
        private String password;
        private String email;
        private String phone;
        private String address;
        private String buildingName;
        private String detail_addr;
}
