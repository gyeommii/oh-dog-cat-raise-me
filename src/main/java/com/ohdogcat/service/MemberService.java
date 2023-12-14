package com.ohdogcat.service;


public interface MemberService {

    boolean checkUserIdDuplication();

    boolean checkEmailDuplication();

    boolean join();


}
