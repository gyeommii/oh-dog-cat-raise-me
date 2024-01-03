package com.ohdogcat.repository;

import java.util.List;
import com.ohdogcat.dto.member.review.ReviewDeleteDto;
import com.ohdogcat.dto.member.review.ReviewDetailDto;
import com.ohdogcat.dto.member.review.ReviewDetailFindDto;
import com.ohdogcat.model.Review;

public interface ReviewDao {
  
  List<ReviewDetailDto> selectReviewDetailViews(ReviewDetailFindDto dto);
  
  List<Review> selectReviewByOptionFk(long option_fk);
  
  void insertReview(Review review);
  
  int updateWhereReviewPkAndMemberFk(Review review);
  
  int deleteWhereReviewAndMemberFk(ReviewDeleteDto dto);
  
  List<Review> selectReviewByMemberFk(long member_fk);
  
  int selectScoreAverage(long option_fk);
  
}