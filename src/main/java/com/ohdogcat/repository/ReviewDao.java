package com.ohdogcat.repository;

import com.ohdogcat.dto.ReviewListItemDto;
import com.ohdogcat.dto.ReviewSatisfactionDto;
import java.util.List;

public interface ReviewDao {

    ReviewSatisfactionDto getReviewSatisfaction(Long productPk);

    List<ReviewListItemDto> getReviewListDtoAtProduct(Long productPk);

}