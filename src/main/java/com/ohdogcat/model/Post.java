package com.ohdogcat.model;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Post {
	private Long post_pk;
	private String title;
	private String content;
	private Long member_fk;
	private LocalDateTime created_time;
	private LocalDateTime modified_time;
	private String post_img_url;
	private Long post_category_fk;
	private String memberId;
}
