package com.ohdogcat.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductOption {
	private Long optionPk;
	private String optionName;
	private Long productFk;
	private Long stock;
	private Long price;

}
