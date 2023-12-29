package com.ohdogcat.dto.cart;

import com.ohdogcat.model.Cart;

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
	
	public Cart toEntity() {
		return Cart.builder()
				.member_fk(member_fk)
				.option_fk(afterOption_fk)
				.build();
	}
}
