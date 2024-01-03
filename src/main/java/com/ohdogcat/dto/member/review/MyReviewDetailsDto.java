package com.ohdogcat.dto.member.review;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MyReviewDetailsDto {
  private String product_name;
  private String option_name;
  private String pet_name;
  private String pet_type;
  
}
