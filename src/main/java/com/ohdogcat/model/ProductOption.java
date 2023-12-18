package com.ohdogcat.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductOption {
	private Long optionPk;
	private String optionName;
	private Long productPk;
	private Long stock;
	private Long price;

}
