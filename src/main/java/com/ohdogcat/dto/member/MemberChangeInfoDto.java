package com.ohdogcat.dto.member;


import com.ohdogcat.model.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberChangeInfoDto {

    private Long member_pk;
    private String password;
    private String newPassword;
    private String phone;

    public Member toMemberToCheck () {
        return Member.builder().password(password).phone(phone).member_pk(member_pk).build();
    }

    public Member toMemberToChange () {
        return Member.builder().password(newPassword).phone(phone).member_pk(member_pk).build();
    }

}
