package com.ohdogcat.dto.cart;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartListDto {
	private Long pet_type;
	private Long product_pk;
	private String product_name;
	private String img_url;
	private Long min_price; // product 가격
	private Long option_fk;
	private String option_name;
	private Long count; // cart 수량
	private Long stock;// option 재고
	private Long price; // option 가격
}
