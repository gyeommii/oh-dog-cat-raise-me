package com.ohdogcat.dto.member.review;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewDetailFindDto {
  private long option_fk;
  private long member_fk;

}
