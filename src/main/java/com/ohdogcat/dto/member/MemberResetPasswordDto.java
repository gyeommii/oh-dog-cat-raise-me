package com.ohdogcat.dto.member;

import com.ohdogcat.model.Member;

public class MemberResetPasswordDto {

    private String email;
    private String member_id;

    public Member toMember() {
        return Member.builder()
            .email(email)
            .member_id(member_id)
            .build();
    }

}
