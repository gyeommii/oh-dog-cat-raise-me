package com.ohdogcat.dto.cart;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartAddDto {
	private Long option_fk;
	private Long count;
	
}
