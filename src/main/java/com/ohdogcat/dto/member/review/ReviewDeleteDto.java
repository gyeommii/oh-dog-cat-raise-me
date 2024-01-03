package com.ohdogcat.dto.member.review;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewDeleteDto {
  private long review_pk;
  private long member_fk;

}
