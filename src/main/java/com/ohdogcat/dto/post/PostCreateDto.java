package com.ohdogcat.dto.post;

import com.ohdogcat.model.Post;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostCreateDto {
	private String title;
	private String content;
	private Long member_fk;
	private Long post_category_fk;

	public Post toEntity() {
		return Post.builder()
				.title(title)
				.content(content)
				.member_fk(member_fk)
				.post_category_fk(post_category_fk)
				.build();
	}
}
