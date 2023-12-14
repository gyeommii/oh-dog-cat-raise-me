package com.ohdogcat.web;


import com.ohdogcat.dto.MemberJoinDto;
import com.ohdogcat.dto.MemberLoginDto;
import com.ohdogcat.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
@RequestMapping("/user")
public class MemberController {

    private final MemberService service;

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

        boolean result = service.checkMemberIdUnique(userId);
        return ResponseEntity.ok(result);
    }

    @ResponseBody
    @GetMapping("/checkEmail")
    public ResponseEntity<Boolean> checkEmailUnique(@RequestParam String email) {
        log.debug("duplicationCheckEmail::");
        log.debug("email={}", email);
        boolean result = service.checkEmailUnique(email);
        return ResponseEntity.ok(result);
    }

    // 끝: signup 시 DB에 중복 데이터 체크할 REST API
    @ResponseBody
    @PostMapping("/signup")
    public ResponseEntity<Boolean> signup(@RequestBody MemberJoinDto dto) {
        log.debug("MemberJoinDto={}", dto);
        boolean result = service.join(dto);

        return ResponseEntity.ok(result);
    }

    // signin flow
    @GetMapping("/signin")
    public void signin() {
        log.debug("signin::");
    }

    @ResponseBody
    @PostMapping("/signin")
    public void signin(HttpSession session,@RequestBody MemberLoginDto dto) {
        log.debug("dto={}", dto);

    }

}
