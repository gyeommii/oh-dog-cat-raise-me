package com.ohdogcat.dto.post;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostListItemDto {
	private Long post_pk;
	private String title;
	private String content;
	private String author;
	private LocalDateTime created_time;
	private LocalDateTime modified_time;
	private String post_img_url;
	private Long post_category_fk;
	
}
