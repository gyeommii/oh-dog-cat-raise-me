package com.ohdogcat.dto.member.review;

import lombok.Builder;
import lombok.Data;

@Data
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
