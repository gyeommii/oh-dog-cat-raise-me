package com.ohdogcat.dto.member.review;

import com.ohdogcat.model.Review;
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

  public Review toEntity() {
    
    return Review.builder().review_pk(review_pk).member_fk(member_fk).build();
  }
  
}
