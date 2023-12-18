package com.ohdogcat.repository;

import com.ohdogcat.dto.member.MemberSessionDto;
import java.util.HashMap;

public interface KakaoMemberDao {

  // 카카오 아이디 등록하기
  void kakaoInsert(HashMap<String, Object> userInfo);

  // 로그인 세션
  MemberSessionDto kakaoLogin(HashMap<String, Object> userInfo);

}
