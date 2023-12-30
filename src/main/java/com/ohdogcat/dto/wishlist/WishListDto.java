package com.ohdogcat.dto.wishlist;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WishListDto {
	private Long member_fk;
	private Long product_fk;
	private String product_name;
	private String img_url;
	private Long min_price;
	private LocalDateTime create_date;	
}
