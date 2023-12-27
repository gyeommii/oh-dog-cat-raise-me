package com.ohdogcat.web;

import com.ohdogcat.dto.member.MemberSessionDto;
import com.ohdogcat.dto.option.OptionOrderDto;
import com.ohdogcat.service.OrderService;
import jakarta.servlet.http.HttpSession;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;

    @GetMapping
    public void getOrder(HttpSession session, Model model, @RequestBody OptionOrderDto optionToOrder) {
        MemberSessionDto signedMember = (MemberSessionDto) session.getAttribute("signedMember");
        log.debug("signedMember={}", signedMember);

        Map<String, Object> result = orderService.checkOrderInfoToPurchase(signedMember.getMember_pk());
        model.addAllAttributes(result);
    }
}
