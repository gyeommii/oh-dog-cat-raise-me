package com.ohdogcat.dto.post;

import java.time.LocalDateTime;

import com.ohdogcat.model.Post;
import com.ohdogcat.util.DateTimeUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostDto {
    private Long post_pk;
    private String title;
    private String content;
    private Long member_fk;
    private LocalDateTime created_time;
    private String formattedCreatedTime;
    private String formattedModifiedTime; // 포맷된 수정 시간
    private String post_img_url;
    private Long post_category_fk;
    private String memberId;
    

    public static PostDto fromEntity(Post post) {
        return PostDto.builder()
                .post_pk(post.getPost_pk())
                .title(post.getTitle())
                .content(post.getContent())
                .member_fk(post.getMember_fk())
                .formattedCreatedTime(DateTimeUtil.formatLocalDateTime(post.getCreated_time()))
                .formattedModifiedTime(DateTimeUtil.formatLocalDateTime(post.getModified_time()))
                .post_img_url(post.getPost_img_url())
                .post_category_fk(post.getPost_category_fk())
                .build();
    }
}
