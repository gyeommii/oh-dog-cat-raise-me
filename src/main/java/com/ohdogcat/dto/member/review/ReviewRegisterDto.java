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
public class ReviewRegisterDto {
  private long member_fk;
  private long pet_fk;
  private long option_fk;
  private String content;
  private String img;
  private int score;

  public Review toEntity() {

    return Review.builder().member_fk(member_fk).pet_fk(pet_fk).option_fk(option_fk).content(content).img(img)
        .score(score).build();
  }
}
