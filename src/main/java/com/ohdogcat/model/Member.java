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
public class Member {
    private Long member_pk;
    private String member_id;
    private String password;
    private String email;
    private String phone;
    private String kakao_client_id;
    private Integer point;
    private Integer membership_grade;
    private Long address_fk;
    private LocalDateTime create_date;
    private LocalDateTime last_login;
}
