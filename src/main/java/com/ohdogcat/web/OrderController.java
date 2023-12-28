package com.ohdogcat.web;

import com.ohdogcat.dto.member.MemberSessionDto;
import com.ohdogcat.dto.order.OrderInfoDto;
import com.ohdogcat.service.OrderService;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/checkout")
    public void getOrder(HttpSession session, Model model,
        @RequestParam List<Long> options_in_cart) {
        MemberSessionDto signedMember = (MemberSessionDto) session.getAttribute("signedMember");
        log.debug("signedMember={}", signedMember);

        Map<String, Object> result = orderService.checkOrderInfoToPurchase(
            signedMember.getMember_pk(), options_in_cart);

        model.addAllAttributes(result);

    }

    @PostMapping("/")
    public void createOrder(HttpSession session, @RequestBody OrderInfoDto infoToOrder) {
        MemberSessionDto signedMember = (MemberSessionDto) session.getAttribute("signedMember");
        log.debug("createOrder(OrderInfoDto={})", infoToOrder);


    }
}
