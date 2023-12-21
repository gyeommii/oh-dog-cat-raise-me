package com.ohdogcat.web;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.ohdogcat.dto.member.MemberSessionDto;
import com.ohdogcat.dto.member.pet.PetAddDto;
import com.ohdogcat.dto.member.pet.PetModifyDto;
import com.ohdogcat.model.Pet;
import com.ohdogcat.service.PetService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/mypage")
public class PetController {

  private final PetService petService;

  @GetMapping("/pet")
  public String myPet(HttpSession session, Model model) {
    log.debug("myPet()");
    MemberSessionDto memberSessionDto = (MemberSessionDto) session.getAttribute("signedMember");

    if (memberSessionDto != null) {
      long member_fk = memberSessionDto.getMember_pk();
      List<Pet> list = petService.readMemberPet(member_fk);

      model.addAttribute("petList", list);

      return "pet/mypet";

    } else {

      return "redirect:/user/signin";
    }

  }

  @GetMapping("/addpet")
  public String addPet(@RequestParam(name = "member_fk") long member_fk, Model model) {
    log.debug("GET - addPet()");

    return "pet/addpet";
  }

  @PostMapping("/addpet")
  public String addPet(HttpSession session, PetAddDto dto) {
    log.debug("POST create(dto={})", dto);

    MemberSessionDto memberSessionDto = (MemberSessionDto) session.getAttribute("signedMember");
    if (memberSessionDto != null) {
      long member_fk = memberSessionDto.getMember_pk();
      dto.setMember_fk(member_fk);
    }
    petService.addPet(dto);

    return "redirect:/mypage/pet";
  }

  @GetMapping("/modifypet")
  public String modifyPet(@RequestParam(name = "pet_pk") long pet_pk, Model model) {
    Pet pet = petService.read(pet_pk);

    model.addAttribute("petList", pet);

    return "pet/modifypet";
  }

  @PostMapping("/modifypet")
  public String modifyPet(PetModifyDto dto) {
    log.debug("modifyPet(dto={})", dto);
    petService.modifyPet(dto);

    return "redirect:/mypage/pet";
  }

  @GetMapping("/delete")
  public String deletePet(@RequestParam(name = "pet_pk") long pet_pk) {
    log.debug("deletePet()");
    petService.deletePet(pet_pk);

    return "redirect:/mypage/pet";
  }

}
