package com.ohdogcat.dto.member.review;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReviewDetailFindDto {
  private long option_fk;
  private long member_fk;

}
