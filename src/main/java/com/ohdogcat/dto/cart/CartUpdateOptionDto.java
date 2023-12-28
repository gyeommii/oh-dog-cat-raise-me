package com.ohdogcat.dto.cart;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartUpdateOptionDto {
	private Long member_fk;
	private Long beforeOption_fk;
	private Long afterOption_fk;
}
