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
public class MemberLoginDto {

    private String member_id;
    private String password;

    public Member toMember() {
        return Member.builder()
            .member_id(member_id)
            .password(password)
            .build();
    }
}
