package com.ohdogcat.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class KakaoMemberDto {
  private String member_id;
  private String kakao_client_id;
  private String email;

}
