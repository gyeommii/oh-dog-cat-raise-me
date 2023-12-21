package com.ohdogcat.repository;

import com.ohdogcat.model.Member;

public interface MemberDao {

    Integer checkMemberIdUnique(String memberId);

    Integer checkEmailUnique(String email);

    Integer join(Member member);

    Member login(Member member);

    String findMemberId(String email);

    Integer updatePassword(Member member);

    Member getMemberMyPageInfo(Long memberPk);

    Integer updateUserInfo(Member member);

    String getMemberPassword(Member member);
}
