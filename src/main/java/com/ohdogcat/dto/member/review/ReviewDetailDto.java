package com.ohdogcat.dto.member.review;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReviewDetailDto {
  private String member_id;
  private long pet_pk;
  private String pet_name;
  private String pet_type;
  private long product_pk;
  private String product_name;
  private String img_url;
  private long option_pk;
  private String option_name;
  private long price;
  private long purchase_status;

}
