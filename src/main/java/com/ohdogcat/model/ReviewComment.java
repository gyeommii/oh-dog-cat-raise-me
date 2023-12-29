package com.ohdogcat.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReviewComment {
  private long comment_pk;
  private long review_fk;
  private long member_fk;
  private String content;
  
}
