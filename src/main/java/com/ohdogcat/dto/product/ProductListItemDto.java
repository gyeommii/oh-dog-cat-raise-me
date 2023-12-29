package com.ohdogcat.dto.product;

import java.time.LocalDateTime;

import com.ohdogcat.model.Product;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductListItemDto {
	private Long productPk;
	private String productName;
	private Long categoryFk;
	private String imgUrl;
	private Long minPrice;
	private Long sold;
	private LocalDateTime createDate;
	
	public static ProductListItemDto fromEntity(Product product) {
		return ProductListItemDto.builder()
				.productName(product.getProductName())
				.imgUrl(product.getImgUrl())
				.minPrice(product.getMinPrice())
				.build();
	}
	
	public static ProductListItemDto sortProductEntity(Product product) {
		return ProductListItemDto.builder()
				.productName(product.getProductName())
				.imgUrl(product.getImgUrl())
				.minPrice(product.getMinPrice())
				.sold(product.getSold())
				.createDate(product.getCreateDate())
				.build();
	}
	
    public ProductListItemDto(Long productPk, String productName, Long categoryFk, String imgUrl, Long minPrice, Long sold, LocalDateTime createDate) {
        this.productPk = productPk;
        this.productName = productName;
        this.categoryFk = categoryFk;
        this.imgUrl = imgUrl;
        this.minPrice = minPrice;
        this.sold = sold;
        this.createDate = createDate;
    }

	

}
