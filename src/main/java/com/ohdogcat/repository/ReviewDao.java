package com.ohdogcat.repository;

import com.ohdogcat.dto.ReviewListItemDto;
import com.ohdogcat.dto.ReviewSatisfactionDto;
import com.ohdogcat.dto.member.review.ReviewDetailDto;
import com.ohdogcat.dto.member.review.ReviewDetailFindDto;
import com.ohdogcat.dto.member.review.ReviewListDto;
import com.ohdogcat.model.Review;
import java.util.List;
import java.util.Map;

public interface ReviewDao {

    List<ReviewDetailDto> selectReviewDetailViews(ReviewDetailFindDto dto);

    void insertReview(Review review);

    int deleteWhereReviewAndMemberFk(Review review);

    List<ReviewListDto> selectReviewByMemberFk(long member_fk);


    ReviewSatisfactionDto getReviewSatisfaction(Long productPk);

    List<ReviewListItemDto> getReviewListDtoAtProduct(Map<String, Object> reviewDataMap);

    Integer checkDuplicateLike(Map<String, Object> reviewLikeDataMap);

    Integer createLike(Map<String, Object> reviewLikeDataMap);

    Integer deleteLike(Map<String, Object> reviewLikeDataMap);

}