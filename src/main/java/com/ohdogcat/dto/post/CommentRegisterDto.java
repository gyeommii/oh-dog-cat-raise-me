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
    private Long comments_pk; // 댓글 PK
    private Long post_fk;     // 게시글 FK
    private String ctext;     // 댓글 내용
    private Long member_fk;   // 작성자 FK

    public Comment toEntity() {
        return Comment.builder()
                    .comments_pk(comments_pk)
                    .post_fk(post_fk)
                    .ctext(ctext)
                    .member_fk(member_fk)
                    .build();
    }
}

