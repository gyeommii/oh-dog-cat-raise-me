package com.ohdogcat.web;

import com.ohdogcat.dto.member.MemberAddressUpdateDto;
import com.ohdogcat.dto.member.MemberSessionDto;
import com.ohdogcat.dto.purchase.OptionInfoToCreateOrderDto;
import com.ohdogcat.dto.purchase.OrderInfoDto;
import com.ohdogcat.service.PurchaseService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/order")
public class PurchaseController {

    private final PurchaseService purchaseService;

    @ResponseBody
    @PostMapping("/address")
    public ResponseEntity<Long> addAddress(@RequestBody
    MemberAddressUpdateDto addressInfo) {
        log.debug("addAddress(addressInfo={})", addressInfo);
        Long addressPk = purchaseService.addAddress(addressInfo);

        return ResponseEntity.ok(addressPk);
    }

    @GetMapping("/checkout")
    public void getOrder(HttpSession session, Model model,
        @RequestParam List<Long> optionfk, @RequestParam String ordertype) {
        MemberSessionDto signedMember = (MemberSessionDto) session.getAttribute("signedMember");
        log.debug("signedMember={}", signedMember);
        log.debug("optionfk={} , optionfk={}", optionfk, ordertype);

        Map<String, Object> result = purchaseService.checkOrderInfoToPurchase(
            signedMember.getMember_pk(), optionfk);
        result.put("orderType", ordertype);
        model.addAllAttributes(result);
    }

    @ResponseBody
    @PostMapping("/detail")
    public ResponseEntity<String> getOrderFromDetail(HttpSession session, Model model, @RequestBody
    List<OptionInfoToCreateOrderDto> optionInfoToCreateOrderDtos) {

        log.debug("optionInfoToCreateOrderDtos={}", optionInfoToCreateOrderDtos);

        session.setAttribute("optionsToOrder", optionInfoToCreateOrderDtos);

        return ResponseEntity.ok("./direct?ordertype=d");
    }

    @GetMapping("/direct")
    public String getOrder(HttpSession session, Model model, @RequestParam String ordertype) {
        MemberSessionDto signedMember = (MemberSessionDto) session.getAttribute("signedMember");
        List<OptionInfoToCreateOrderDto> optionList = (List<OptionInfoToCreateOrderDto>) session.getAttribute("optionsToOrder");
        session.removeAttribute("optionsToOrder");

        Map<String, Object> result = purchaseService.getOrderFromDetail(signedMember.getMember_pk(), optionList);
        result.put("orderType", ordertype);
        model.addAllAttributes(result);

        return "/order/direct";
    }

    @ResponseBody
    @PostMapping("/")
    public ResponseEntity<String> createOrder(HttpSession session,
        @RequestBody OrderInfoDto infoToOrder,
        HttpServletRequest req, HttpServletResponse res)
        throws IOException {
        MemberSessionDto signedMember = (MemberSessionDto) session.getAttribute("signedMember");

        infoToOrder.setMemberFk(signedMember.getMember_pk());

        purchaseService.createOrderThroughCart(infoToOrder);

        return ResponseEntity.ok("../");
    }
}
