package com.ohdogcat.repository;

import com.ohdogcat.model.Member;
import com.ohdogcat.model.Membership;

public interface MemberDao {

    Integer checkMemberIdUnique(String member_id);

    Integer checkEmailUnique(String email);

    Integer join(Member member);

    Member login(Member member);

    String findMemberId(String email);

    Integer updatePassword(Member member);

    Member getMemberMyPageInfo(Long member_pk);
//    메셔드명 변경하기

    Integer updateUserInfo(Member member);

    String getMemberPhone(Member member);

    Long updateUserDefaultAddress(Member member);

    Member getUserInfoAtOrder(Long member_pk);

    Membership getUserMembership (Member member);
}
