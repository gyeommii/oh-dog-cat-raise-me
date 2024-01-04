package com.ohdogcat.model;

import java.time.LocalDateTime;

import com.ohdogcat.util.DateTimeUtil;

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
	private String memberId;
	
	public String getFormattedCreatedTime() {
        return created_time != null ? DateTimeUtil.formatLocalDateTime(created_time) : null;
    }

    public String getFormattedModifiedTime() {
        return modified_time != null ? DateTimeUtil.formatLocalDateTime(modified_time) : null;
    }
}
