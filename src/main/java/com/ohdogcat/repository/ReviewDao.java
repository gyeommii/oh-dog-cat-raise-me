package com.ohdogcat.repository;

import com.ohdogcat.model.Review;

public interface ReviewDao {
  
  // member_fk로 리뷰 목록 나타내기 - 마이페이지
  Review selectByMemberFk(long member_fk);
  
  // pet_fk로 리뷰 목록 나타내기 - 마이페이지
  Review selectByPetFk(long pet_fk);
  
  // 옵션의 리뷰 리스트 나타내기 - 상품 디테일 페이지 // 등록일 순
  Review selectByOptionFkOrderByReviewPkDesc(long option_fk);
  
  // 리뷰 좋아요 순
  Review selectByOptionFkOrderByLikeCount(long option_fk);
  
  // 리뷰 작성 가능 목록 보여주기
  Review selectByReviewRegistrable();
  
  // 구매 확정 확인하기?
  Review selectReviewJoinPurchase();
  
  // 리뷰 등록하기
  void insertReview();
  
  // 리뷰 수정하기
  void updateReview();
  
  // 리뷰 삭제하기
  void deleteReview();
  
  // 리뷰 좋아요
  int likeCount();

}