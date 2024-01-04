package com.ohdogcat.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/error")
public class ErrorController {
    @GetMapping("/{errorStatus}")
    public void getErrorPage(@PathVariable Integer errorStatus) {
        log.debug ("{}번 에러 발생", errorStatus);
    }

}
