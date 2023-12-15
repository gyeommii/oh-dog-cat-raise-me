package com.ohdogcat.model;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Product {
	private Long productPk;
	private String productName;
	private Long categoryFk;
	private String imgUrl;
	private Long minPrice;
	private Long sold;
	private LocalDateTime createDate;
	
}
