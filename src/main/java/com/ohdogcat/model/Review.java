package com.ohdogcat.model;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Review {
  private long review_pk;
  private long member_fk;
  private long pet_fk;
  private long option_fk;
  private String content;
  private String img;
  private int score;
  private LocalDateTime created_time;
  private LocalDateTime modified_time;
  private int like_count;

}