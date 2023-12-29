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
  void updateReview(Review review);
  
  // 리뷰 삭제하기
  void deleteReview();
  
  // member_fk로 리뷰 목록 나타내기 - 마이페이지
  Review selectByMemberFkByReview(long member_fk);
  
  // pet_fk로 리뷰 목록 나타내기 - 마이페이지
  Review selectByPetFk(long pet_fk);  
  
  // 리뷰 좋아요 순
  Review selectByOptionFkOrderByLikeCount(long option_fk);
  
  // 리뷰 작성 가능 목록 보여주기
  Review selectByReviewRegistrable();
  
  
  // 리뷰 좋아요
  int likeCount();

}