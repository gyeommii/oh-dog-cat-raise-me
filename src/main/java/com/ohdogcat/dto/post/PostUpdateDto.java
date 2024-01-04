package com.ohdogcat.dto.post;

import com.ohdogcat.model.Post;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostUpdateDto {
	
	private Long post_pk;
	private String title;
	private String content;
	private Long member_fk;
	
	public Post toEntity() {
		return Post.builder()
				.post_pk(post_pk)
				.title(title)
				.content(content)
				.member_fk(member_fk)
				.build();
	}

}
