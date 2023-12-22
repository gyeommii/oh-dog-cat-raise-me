package com.ohdogcat.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import com.ohdogcat.dto.member.pet.PetAddDto;
import com.ohdogcat.dto.member.pet.PetModifyDto;
import com.ohdogcat.model.Pet;
import com.ohdogcat.repository.PetDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class PetService {

  private final PetDao petDao;  
  
  public Pet read(long id) {
    Pet pet = petDao.selectById(id);
    
    return pet;
  }
  
  public List<Pet> readMemberPet(long member_fk) {
    List<Pet> pet = petDao.selectByMemberFk(member_fk);
    
    return pet;    
  }
  
  public void addPet(PetAddDto dto) {
    log.debug("addPet(dto={})", dto);    
    
    
    petDao.insertPet(dto.toEntity()); 
  }
  
  public void modifyPet(PetModifyDto dto) {
    log.debug("modifyPet(pet_pk={})", dto);
    
    petDao.updatePet(dto.toEntity());
  }
  
  public void deletePet(long pet_pk) {
    log.debug("delete(pet_pk={})", pet_pk);
    
    petDao.deletePet(pet_pk);    
  }
  
  
}
