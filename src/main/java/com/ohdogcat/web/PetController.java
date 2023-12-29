package com.ohdogcat.web;

import java.io.IOException;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.ohdogcat.dto.member.MemberSessionDto;
import com.ohdogcat.dto.member.pet.PetAddDto;
import com.ohdogcat.dto.member.pet.PetListDto;
import com.ohdogcat.dto.member.pet.PetModifyDto;
import com.ohdogcat.model.Pet;
import com.ohdogcat.service.PetService;
import com.ohdogcat.util.FtpImgLoaderUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/mypage")
public class PetController {

  private final PetService petService;
  private final FtpImgLoaderUtil ftpImgLoaderUtil;

  @GetMapping("/pet")
  public String myPet(HttpSession session, Model model) {
    log.debug("myPet()");
    MemberSessionDto memberSessionDto = (MemberSessionDto) session.getAttribute("signedMember");

    if (memberSessionDto != null) {
      long member_fk = memberSessionDto.getMember_pk();      
      List<PetListDto> list = petService.readMemberPet(member_fk);      
      
      log.debug("member_fk={}", member_fk);
      log.debug("list={}", list);
      model.addAttribute("petList", list);
      model.addAttribute("member_fk", member_fk);
      
      return "pet/mypet";

    } else {

      return "redirect:/user/signin";
    }

  }

  @GetMapping("/addpet")
  public String addPet() {
    log.debug("GET - addPet()");

      return "pet/addpet";

  }

  @PostMapping("/addpet")
  public String addPet(@RequestParam(name = "img_file") MultipartFile img_file, HttpSession session,
      HttpServletRequest req, PetAddDto dto) throws IOException {
    log.debug("POST create(dto={})", dto);
    MemberSessionDto memberSessionDto = (MemberSessionDto) session.getAttribute("signedMember");
    if (memberSessionDto != null) {
      long member_fk = memberSessionDto.getMember_pk();
      dto.setMember_fk(member_fk);
      log.debug("POST create(dto={})", dto);

    } else {

      return "redirect:/user/signin";
    }

    if (!img_file.isEmpty()) {
      log.debug("MultipartFile img={}", img_file);

      String imgPath = ftpImgLoaderUtil.upload(img_file, req.getServletPath());
      dto.setImg(imgPath);
      log.debug("addPet(success)");
      log.debug("POST create(dto={})", dto);
      petService.addPet(dto);

      return "redirect:/mypage/pet";

    } else {
      dto.setImg("");
      petService.addPet(dto);


      return "redirect:/mypage/pet";
    }

  }

  @GetMapping("/modifypet")
  public String modifyPet(@RequestParam(name = "pet_pk") long pet_pk, Model model) {
    log.debug("modifyPet()");
    Pet pet = petService.read(pet_pk);

    model.addAttribute("petList", pet);

    return "pet/modifypet";
  }

  @PostMapping("/modifypet")
  public String modifyPet(@RequestParam(name = "img_file") MultipartFile img_file,
      HttpServletRequest req, HttpSession session, PetModifyDto dto) throws IOException {
    log.debug("modifyPet(dto={})", dto);
    MemberSessionDto memberSessionDto = (MemberSessionDto) session.getAttribute("signedMember");
    if (memberSessionDto == null) {

      return "redirect:/user/signin";
    }

    if (!img_file.isEmpty()) {
      log.debug("MultipartFile img={}", img_file);

      String imgPath = ftpImgLoaderUtil.upload(img_file, req.getServletPath());
      dto.setImg(imgPath);
      log.debug("modifyPet(success)");
      log.debug("POST create(dto={})", dto);
      petService.modifyPet(dto);

      return "redirect:/mypage/pet";

    } else {
      dto.setImg("");
      petService.modifyPet(dto);

      return "redirect:/mypage/pet";
    }

  }

  @GetMapping("/deletepet")
  public String deletePet(@RequestParam(name = "pet_pk") long pet_pk) {
    log.debug("deletePet()");
    petService.deletePet(pet_pk);

    return "redirect:/mypage/pet";
  }

}