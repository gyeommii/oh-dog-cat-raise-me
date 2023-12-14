package com.ohdogcat.service;


import com.ohdogcat.dto.MemberJoinDto;

public interface MemberService {

    boolean checkMemberIdUnique(String memberId);

    boolean checkEmailUnique(String email);

    boolean join(MemberJoinDto dto);


}
