package com.ohdogcat.dto.purchase;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OptionReqDto {
    private List<OptionInfoToCreateOrderDto> optionInfoToCreateOrderDtos;
}
