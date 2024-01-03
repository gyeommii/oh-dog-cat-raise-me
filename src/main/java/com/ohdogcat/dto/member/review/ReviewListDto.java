package com.ohdogcat.dto.member.review;

import java.sql.Timestamp;
import com.ohdogcat.model.Review;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReviewListDto {
  private long review_pk;
  private long option_fk;
  private long pet_fk;
  private String content;
  private String img;
  private int score;
  private Timestamp modified_time;

  public static ReviewListDto fromEntity(Review review) {

    return ReviewListDto.builder().review_pk(review.getReview_pk()).option_fk(review.getOption_fk())
        .pet_fk(review.getPet_fk()).content(review.getContent()).img(review.getImg())
        .score(review.getScore()).modified_time(Timestamp.valueOf(review.getModified_time()))
        .build();
  }

}
