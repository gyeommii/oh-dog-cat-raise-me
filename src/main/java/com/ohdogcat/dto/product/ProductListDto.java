package com.ohdogcat.dto.product;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductListDto {
	private Long productPk;
	private String productName; // 제품명
	private Long categoryFk; // 카테고리
	private String imgUrl; // 이미지URL
	private Long minPrice; // 가격
	private Long sold; // 판매량
	private LocalDateTime createDate; // 상품등록일
	private Long petType; // 펫타입
	private int score; // 리뷰평점
    private Long stock; // 재고 수량 => 0이면 품절
    
   
    
    
}
