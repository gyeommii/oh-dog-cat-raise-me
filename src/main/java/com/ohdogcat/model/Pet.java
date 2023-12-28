package com.ohdogcat.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pet {
  private long pet_pk;
  private long member_fk;
  private String img;
  private String pet_name;
  private String pet_type;
  private String age;
  private String gender;
  private String chehyeong;

}
