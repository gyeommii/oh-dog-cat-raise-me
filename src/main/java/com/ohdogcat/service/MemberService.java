package com.ohdogcat.service;


import com.ohdogcat.dto.member.MemberJoinDto;
import com.ohdogcat.dto.member.MemberLoginDto;
import com.ohdogcat.dto.member.MemberSessionDto;

public interface MemberService {

    boolean checkMemberIdUnique(String memberId);

    boolean checkEmailUnique(String email);

    boolean join(MemberJoinDto dto);

    MemberSessionDto signin(MemberLoginDto dto);

    String findMemberId(String email);

    boolean resetPassword();
}
