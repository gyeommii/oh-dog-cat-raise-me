package com.ohdogcat.repository;

import java.util.HashMap;
import com.ohdogcat.dto.KakaoMemberDto;
import com.ohdogcat.dto.MemberSessionDto;

public interface KakaoMemberDao {

  // 카카오 아이디 등록하기
  void kakaoInsert(HashMap<String, Object> userInfo);

  // 로그인 세션
  MemberSessionDto kakaoLogin(HashMap<String, Object> userInfo);

}
