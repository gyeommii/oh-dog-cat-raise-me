package com.ohdogcat.dto.member.review;

import java.sql.Timestamp;
import com.ohdogcat.model.Review;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewListDto {
  private long review_pk;
  private long member_fk;
  private String product_name;
  private String option_name;
  private String pet_name;
  private String pet_type;
  private String content;
  private String img;
  private int score;
  private Timestamp modified_time;

}
