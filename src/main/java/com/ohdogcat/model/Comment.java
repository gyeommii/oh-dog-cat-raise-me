package com.ohdogcat.model;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Comment {
	private Long comments_pk;
	private Long post_fk;
	private Long member_fk;
	private String ctext;
	private LocalDateTime created_time;
	private LocalDateTime modified_time;
}
