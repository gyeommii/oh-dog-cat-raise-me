package com.ohdogcat.web;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @ResponseBody
    @GetMapping("/checkId")
    public ResponseEntity<Boolean> checkUserIdDuplication(@RequestParam String userId) {
        log.debug("duplicationCheck::");
        log.debug("userId={}", userId);
        return ResponseEntity.ok(false);
    }

    @PostMapping("/signup")
    public void signuppost() {
        log.debug("signup::");
    }

}
