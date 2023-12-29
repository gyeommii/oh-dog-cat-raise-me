package com.ohdogcat.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.ohdogcat.dto.member.review.ReviewDetailDto;
import com.ohdogcat.dto.member.review.ReviewListDto;
import com.ohdogcat.dto.member.review.ReviewRegisterDto;
import com.ohdogcat.repository.ReviewDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class ReviewService {
  
  private final ReviewDao reviewDao; 
  
  public List<ReviewDetailDto> selectReviewDetailViews(long member_fk) {
    log.debug("selectReviewDetailViews(ReviewJoinDto={})", member_fk);
    List<ReviewDetailDto> reviewDetailDto = reviewDao.selectReviewDetailViews(member_fk);
    
    return reviewDetailDto;
  }
  
  public void insertReview (ReviewRegisterDto dto) {
    log.debug("insertReview(dto={})", dto);
    
    reviewDao.insertReview(dto.toEntity());
  }

  public List<ReviewListDto> selectReviewByProductPk(long productPk) {
    List<ReviewListDto> list = reviewDao.selectReviewByProductPk(productPk);
    
    return list;
  }

  
}
