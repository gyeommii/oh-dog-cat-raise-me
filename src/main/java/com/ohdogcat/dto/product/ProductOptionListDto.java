package com.ohdogcat.dto.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductOptionListDto {
	private Long option_Pk;
	private String option_Name;
	private Long stock;
	private Long price;
}
