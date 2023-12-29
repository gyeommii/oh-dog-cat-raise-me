package com.ohdogcat.dto.post;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

import com.ohdogcat.model.Post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
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
	
	public static PostListItemDto fromEntity(Post post) {
		return PostListItemDto.builder()
				.post_pk(post.getPost_pk())
				.title(post.getTitle())
				.author(post.getAuthor())
				.modified_time(post.getModified_time())
				.build();
	}
	

	
}
