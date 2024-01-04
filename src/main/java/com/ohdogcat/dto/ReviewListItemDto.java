package com.ohdogcat.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewListItemDto {
    private Long review_pk;
    private Long member_fk;
    private Long score;
    private Integer isLike;
    private Integer likeCount;
    private String content;
    private String image_url;
    private String member_id;
    private String option_name;
    private String pet_name;
    private String pet_type;
    private String age;
    private String gender;
    private String chehyeong;
    private LocalDateTime created_time;
}
