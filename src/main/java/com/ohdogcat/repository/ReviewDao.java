package com.ohdogcat.repository;

import java.util.List;
import com.ohdogcat.dto.member.review.ReviewDetailDto;
import com.ohdogcat.dto.member.review.ReviewDetailFindDto;
import com.ohdogcat.dto.member.review.ReviewListDto;
import com.ohdogcat.model.Review;

public interface ReviewDao {
  
  List<ReviewDetailDto> selectReviewDetailViews(ReviewDetailFindDto dto);  
  
  void insertReview(Review review);
  
  int updateWhereReviewPkAndMemberFk(Review review);
  
  int deleteWhereReviewAndMemberFk(Review review);
  
  List<ReviewListDto> selectReviewByMemberFk(long member_fk);  
  
}