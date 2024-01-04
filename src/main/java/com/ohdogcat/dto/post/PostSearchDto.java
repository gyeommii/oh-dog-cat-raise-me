package com.ohdogcat.dto.post;

import lombok.Data;

@Data
public class PostSearchDto {
	
	private Long postCategory;
	private String searchCategory;
	private String keyword;
}
