package com.ohdogcat.dto.post;

import java.time.LocalDateTime;

import com.ohdogcat.model.Post;
import com.ohdogcat.util.DateTimeUtil;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostListItemDto {
    private Long post_pk;
    private String title;
    private String content;
    private Long member_fk;
    private LocalDateTime created_time;
    private String formattedModifiedTime; // 포맷된 수정 시간 문자열 필드
    private String post_img_url;
    private Long post_category_fk;
    private String memberId;

    public static PostListItemDto fromEntity(Post post) {
        return PostListItemDto.builder()
                .post_pk(post.getPost_pk())
                .title(post.getTitle())
                .member_fk(post.getMember_fk())
                .memberId(post.getMemberId())
                .post_category_fk(post.getPost_category_fk())
                .formattedModifiedTime(DateTimeUtil.formatLocalDateTime(post.getModified_time()))
                .build();
    }
}
