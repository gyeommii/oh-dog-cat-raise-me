package com.ohdogcat.service;

import com.ohdogcat.dto.ReviewListItemDto;
import com.ohdogcat.dto.ReviewSatisfactionDto;
import com.ohdogcat.repository.ReviewDao;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class ReviewService {

    private final ReviewDao reviewDao;

    public Map<String, Object> getReviewDataAtProductPage(Long productPk) {
        Map<String, Object> result = new HashMap<>();

        ReviewSatisfactionDto satisfaction = reviewDao.getReviewSatisfaction(productPk);
        result.put("satisfaction", satisfaction);

        List<ReviewListItemDto> reviewListItems = reviewDao.getReviewListDtoAtProduct(productPk);
        result.put("reviewList", reviewListItems);

        return result;
    }

}
