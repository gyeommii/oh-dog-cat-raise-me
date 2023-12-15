package com.ohdogcat.service;


import com.ohdogcat.dto.MemberJoinDto;
import com.ohdogcat.dto.MemberLoginDto;
import com.ohdogcat.dto.MemberSessionDto;

public interface MemberService {

    boolean checkMemberIdUnique(String memberId);

    boolean checkEmailUnique(String email);

    boolean join(MemberJoinDto dto);

    MemberSessionDto signin(MemberLoginDto dto);


}
