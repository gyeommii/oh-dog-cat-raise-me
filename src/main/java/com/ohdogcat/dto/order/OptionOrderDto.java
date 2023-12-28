package com.ohdogcat.dto.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OptionOrderDto {
    private Long option_fk;
    private Long product_fk;
    private Integer count;
    private Integer stock;
    private Integer price;
    private String product_name;
    private String img_url;
    private String option_name;
}