package com.ohdogcat.dto.member.review;

import lombok.Data;

@Data
public class ReviewRegisterDto {
  private long member_fk;
  private long pet_fk;
  private long option_fk;
  private String content;
  private String img;
  private int score;

}
