package com.ohdogcat.service;


public interface MemberService {

    boolean checkMemberIdUnique(String memberId);

    boolean checkEmailUnique(String email);

    boolean join();


}
