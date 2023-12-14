package com.ohdogcat.web;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
@RequestMapping("/user")
public class MemberController {


    @GetMapping("/signup")
    public void signup() {
        log.debug("signup::");
    }

    // 시작: signup 시 DB에 중복 데이터 체크할 REST API
    @ResponseBody
    @GetMapping("/checkId")
    public ResponseEntity<Boolean> checkUserIdUnique(@RequestParam String userId) {
        log.debug("duplicationCheck::");
        log.debug("userId={}", userId);
        return ResponseEntity.ok(false);
    }

    @ResponseBody
    @GetMapping("/checkEmail")
    public ResponseEntity<Boolean> checkEmailUnique(@RequestParam String email) {
        log.debug("duplicationCheckEmail::");
        log.debug("email={}", email);
        return ResponseEntity.ok(true);
    }

    // 끝: signup 시 DB에 중복 데이터 체크할 REST API
    @PostMapping("/signup")
    public void signuppost() {
        log.debug("signup::");
    }

}
