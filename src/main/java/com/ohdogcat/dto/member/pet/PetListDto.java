package com.ohdogcat.dto.member.pet;

import com.ohdogcat.model.Pet;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PetListDto {
  private long pet_pk;
  private long member_fk;
  private String img;
  private String pet_name;
  private String pet_type;
  private String age;
  private String gender;
  private String chehyeong;

  public static PetListDto fromEntity(Pet pet) {

    return PetListDto.builder().pet_pk(pet.getPet_pk()).member_fk(pet.getMember_fk())
        .img(pet.getImg()).pet_name(pet.getPet_name()).pet_type(pet.getPet_type()).age(pet.getAge())
        .gender(pet.getGender()).chehyeong(pet.getChehyeong()).build();
  }

}
