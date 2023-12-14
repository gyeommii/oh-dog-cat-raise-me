package com.ohdogcat.repository;

public interface MemberDao {
    Integer checkMemberIdUnique (String memberId);
    Integer checkEmailUnique (String email);

}
