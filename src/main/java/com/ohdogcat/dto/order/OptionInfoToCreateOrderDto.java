package com.ohdogcat.dto.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OptionInfoToCreateOrderDto {
    private Long option_fk;
    private Long count;
}
