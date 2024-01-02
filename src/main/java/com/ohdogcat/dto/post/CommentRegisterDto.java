package com.ohdogcat.dto.post;

import com.ohdogcat.model.Comment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentRegisterDto {
	private Long post_fk;
	private String ctext;
	private Long member_fk;
	
	public Comment toEntity() {
		
		return Comment.builder()
					.post_fk(post_fk)
					.ctext(ctext)
					.member_fk(member_fk)
					.build();
	}

}
