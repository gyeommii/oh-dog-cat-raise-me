package com.ohdogcat.dto.member.review;

import com.ohdogcat.model.Review;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReviewUpdateDto {
  private long review_pk;
  private long member_fk;
  private String content;
  private String img;
  private int score;

  public Review toEntity() {

    return Review.builder().review_pk(review_pk).member_fk(member_fk).content(content).img(img).score(score).build();
  }

}
