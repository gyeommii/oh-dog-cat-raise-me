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

    public Map<String, Object> getReviewDataAtProductPage(Map<String, Object> reviewDataMap) {
        Map<String, Object> result = new HashMap<>();

        ReviewSatisfactionDto satisfaction = reviewDao.getReviewSatisfaction(
            (Long) reviewDataMap.get("productPk"));
        result.put("satisfaction", satisfaction);

        List<ReviewListItemDto> reviewListItems = reviewDao.getReviewListDtoAtProduct(
            reviewDataMap);
        result.put("reviewList", reviewListItems);

        return result;
    }

    public String clickLike(Map<String, Object> reviewLikeDataMap) {
// db에 있나 체크
        Integer isDuplicated = reviewDao.checkDuplicateLike(reviewLikeDataMap);

        if (isDuplicated != 0) {
            Integer result = reviewDao.deleteLike(reviewLikeDataMap);

            if (result == 0) {
                return "delete_failed";
            }

            return "deleted";
        }

        Integer result = reviewDao.createLike(reviewLikeDataMap);
        if (result == 0) {
            return "create_failed";
        }
        return "created";
    }

}
