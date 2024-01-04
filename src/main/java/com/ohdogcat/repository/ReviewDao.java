package com.ohdogcat.repository;

import com.ohdogcat.dto.ReviewListItemDto;
import com.ohdogcat.dto.ReviewSatisfactionDto;
import java.util.List;
import java.util.Map;

public interface ReviewDao {

    ReviewSatisfactionDto getReviewSatisfaction(Long productPk);

    List<ReviewListItemDto> getReviewListDtoAtProduct(Map<String, Object> reviewDataMap);

    Integer checkDuplicateLike(Map<String, Object> reviewLikeDataMap);

    Integer createLike(Map<String, Object> reviewLikeDataMap);

    Integer deleteLike(Map<String, Object> reviewLikeDataMap);

}