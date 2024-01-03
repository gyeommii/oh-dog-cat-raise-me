package com.ohdogcat.dto.member.review;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewDetailDto {
  private long pet_pk;
  private String pet_name;
  private String pet_type;
  private String product_name;
  private String img_url;
  private String option_name;
  private long status_pk;

}
