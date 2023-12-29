package com.ohdogcat.repository;

import java.util.List;
import com.ohdogcat.dto.member.review.ReviewDetailDto;
import com.ohdogcat.dto.member.review.ReviewListDto;
import com.ohdogcat.model.Review;

public interface ReviewDao {
  
  // 리뷰 적을 때 필요한 정보 가져오기
  List<ReviewDetailDto> selectReviewDetailViews(long member_fk);
  
  // 옵션의 리뷰 리스트 나타내기 - 상품 디테일 페이지
  List<ReviewListDto> selectReviewByProductPk(long productPk);
  
  // 리뷰 등록하기
  void insertReview(Review review);
  
  // 리뷰 수정하기
  void updateWhereReviewPkAndMemberFk(Review review);
  
  // 리뷰 삭제하기
  void deleteWhereReviewAndMemberFk(Review review);
  
  // member_fk로 리뷰 목록 나타내기 - 마이페이지
  Review selectByMemberFkByReview(long member_fk);
  
}