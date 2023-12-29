package com.ohdogcat.repository;

import java.util.List;
import com.ohdogcat.model.Pet;

public interface PetDao {  
  
  // pet_pk로 검색하기
  Pet selectByPetPk(long pet_pk);
  
  // member_fk로 검색하기
  List<Pet> selectByMemberFk(long member_fk);
  
  // 펫 추가하기
  void insertPet(Pet pet);
  
  // 펫 수정하기
  void updatePet(Pet pet);
  
  // 펫 삭제하기
  void deletePet(long pet_pk);
  
}