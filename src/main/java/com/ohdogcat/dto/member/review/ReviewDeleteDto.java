package com.ohdogcat.dto.member.review;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReviewDeleteDto {
  private long review_pk;
  private long member_fk;

}
