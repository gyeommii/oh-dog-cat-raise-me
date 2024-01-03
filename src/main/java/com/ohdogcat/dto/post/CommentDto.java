package com.ohdogcat.dto.post;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {
    private Long comments_pk; // 댓글 PK
    private Long post_fk;     // 게시글 FK
    private Long member_fk;   // 작성자 FK
    private String ctext;     // 댓글 내용
    private String createdTime; // 생성된 시간 (문자열로 변환된 값)
    private String modifiedTime; // 수정된 시간 (문자열로 변환된 값)
    private String memberId;

}
