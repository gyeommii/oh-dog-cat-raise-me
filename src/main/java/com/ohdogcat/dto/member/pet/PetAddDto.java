package com.ohdogcat.dto.member.pet;

import com.ohdogcat.model.Pet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PetAddDto {
  private long member_fk;
  private String img;
  private String pet_name;
  private String pet_type;
  private String age;
  private String gender;
  private String chehyeong;

  public Pet toEntity() {

    return Pet.builder().member_fk(member_fk).img(img).pet_name(pet_name).pet_type(pet_type)
        .age(age).gender(gender).chehyeong(chehyeong).build();
  }

}
