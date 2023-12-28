package com.ohdogcat.dto.order;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderInfoDto {
    private Long memberFk;
    private Long totalPrice;
    private Long pointUsed;
    private List<OptionInfoToCreateOrderDto> optionList;
    private Long addressFk;
    private String orderName;
    private Long paidPrice;
    private String payMethod;
    private String paymentSuccess;
    private String merchantUid;
    private String orderType;
}
