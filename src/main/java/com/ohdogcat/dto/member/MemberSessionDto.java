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
public class MemberSessionDto {

    private Long member_pk;
    private String member_id;

    public static MemberSessionDto fromMember(Member member) {
        if (member == null) {
            return null;
        }

        return MemberSessionDto.builder()
            .member_pk(member.getMember_pk())
            .member_id(member.getMember_id())
            .build();
    }

    public Member toMember() {
        return Member.builder()
            .member_pk(member_pk)
            .member_id(member_id)
            .build();
    }

}
