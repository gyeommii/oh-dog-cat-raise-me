package com.ohdogcat.dto.product;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductPetTypeDto {
	private Long productPk;
	private String productName;
	private Long categoryFk;
	private String imgUrl;
	private Long minPrice;
	private Long sold;
	private LocalDateTime createDate;
	private Long petType;
}
