package com.ohdogcat.service;

public class MemberServiceImpl implements MemberService{

    @Override
    public boolean checkUserIdDuplication() {
        return false;
    }

    @Override
    public boolean checkEmailDuplication() {
        return false;
    }

    @Override
    public boolean join() {
        return false;
    }
}
