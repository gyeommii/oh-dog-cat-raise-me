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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/purchase")
public class PurchaseController {

    private final PurchaseService purchaseService;
    private final String OPTION_AND_COUNT_IN_SESSION = "optionsToOrder";

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
        log.debug("dd={}", model.getAttribute("products"));
    }

    @GetMapping("/direct")
    public String getOrder(HttpSession session, Model model, @RequestParam String ordertype) {
        log.debug("log.debug");
        MemberSessionDto signedMember = (MemberSessionDto) session.getAttribute("signedMember");
        List<OptionInfoToCreateOrderDto> optionList = (List<OptionInfoToCreateOrderDto>) session.getAttribute(
            OPTION_AND_COUNT_IN_SESSION);
        log.debug("optionList={}", optionList);

        Map<String, Object> result = purchaseService.getOrderFromDetail(signedMember.getMember_pk(),
            optionList);
        result.put("orderType", ordertype);

        model.addAllAttributes(result);
        log.debug("dd={}", model.getAttribute("products"));
        return "/purchase/checkout";
    }

    @ResponseBody
    @PostMapping("/checkout")
    public ResponseEntity<String> getOrderFromDetail(HttpSession session, Model model, @RequestBody
    List<OptionInfoToCreateOrderDto> optionInfoToCreateOrderDtos) {
        log.debug("optionInfoToCreateOrderDtos={}", optionInfoToCreateOrderDtos);
        session.setAttribute(OPTION_AND_COUNT_IN_SESSION, optionInfoToCreateOrderDtos);

        return ResponseEntity.ok("../purchase/direct?ordertype=d");
    }


    @ResponseBody
    @PostMapping("/")
    public ResponseEntity<String> createOrder(HttpSession session,
        @RequestBody OrderInfoDto infoToOrder,
        HttpServletRequest req, HttpServletResponse res)
        throws IOException {
        MemberSessionDto signedMember = (MemberSessionDto) session.getAttribute("signedMember");
        log.debug("infoToOrder={}", infoToOrder);
        infoToOrder.setMemberFk(signedMember.getMember_pk());
        log.debug("infoToOrder22222={}", infoToOrder);

        Long purchasePk = purchaseService.createOrderThroughCart(infoToOrder);
        log.debug("구매 완료");
        if (infoToOrder.getOrderType().equals("d")) {
            session.removeAttribute(OPTION_AND_COUNT_IN_SESSION);
        }
        return ResponseEntity.ok("./" + purchasePk + "?at=O");
    }

    @GetMapping("/{purchasePk}")
    public String showPurchaseDetail(HttpSession session, Model model,
        @PathVariable Long purchasePk,
        @RequestParam(defaultValue = "O") String at) {
        // "O" => 구매 후 && "L" => 리스트 선택
        MemberSessionDto signedMember = (MemberSessionDto) session.getAttribute("signedMember");
        Map<String, Object> result = purchaseService.showPurchaseDetail(signedMember.getMember_pk(),
            purchasePk);
        result.put("at", at);

        model.addAllAttributes(result);

        log.debug("statusatcont={}", model.getAttribute("purchaseStatus"));
        log.debug("atatcont={}", model.getAttribute("at"));
        log.debug("paymentatcont={}", model.getAttribute("payment"));

        return "/purchase/detail";
    }
}
