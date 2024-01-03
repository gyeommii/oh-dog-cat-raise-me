package com.ohdogcat.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.ohdogcat.dto.member.review.MyReviewDetailsDto;
import com.ohdogcat.dto.member.review.ReviewDeleteDto;
import com.ohdogcat.dto.member.review.ReviewDetailDto;
import com.ohdogcat.dto.member.review.ReviewDetailFindDto;
import com.ohdogcat.dto.member.review.ReviewListDto;
import com.ohdogcat.dto.member.review.ReviewRegisterDto;
import com.ohdogcat.dto.member.review.ReviewUpdateDto;
import com.ohdogcat.enums.PurchaseStatusEnum;
import com.ohdogcat.model.Review;
import com.ohdogcat.repository.ReviewDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class ReviewService {
  
  private final ReviewDao reviewDao; 
  
  // 리뷰에 필요한 목록 불러오기
  public List<ReviewDetailDto> selectReviewDetailViews(ReviewDetailFindDto dto) {
    log.debug("selectReviewDetailViews(dto={})", dto);
    List<ReviewDetailDto> list = reviewDao.selectReviewDetailViews(dto);
        // 스테이터스의 값이 같으면
//    if (  == PurchaseStatusEnum.CONFIRMED_PURCHASE.getStatus_pk()) {
//      log.debug("reviewDetailDto dto={}, Enum={}", list.get(0).getStatus_pk(), PurchaseStatusEnum.CONFIRMED_PURCHASE.getStatus_pk());
//      
//    } else {
      // 다르면??????????????????????          
//    }
    
  return list;
  }
  
  // 리뷰 작성
  public void insertReview (ReviewRegisterDto dto) {
    log.debug("insertReview(dto={})", dto);
    
    reviewDao.insertReview(dto.toEntity());
  }  

  // 리뷰 수정
  public int updateWhereReviewPkAndMemberFk(ReviewUpdateDto dto) {
    int result = reviewDao.updateWhereReviewPkAndMemberFk(dto.toEntity());
    
    return result;
  }
  
  // 리뷰 삭제
  public int deleteWhereReviewAndMemberFk(ReviewDeleteDto dto) {
    int result = reviewDao.deleteWhereReviewAndMemberFk(dto.toEntity());
    
    return result;
  }
  
  // 내가 쓴 리뷰 보기
  public List<ReviewListDto> selectReviewByMemberFk(long member_fk) {
    List<ReviewListDto> list = reviewDao.selectReviewByMemberFk(member_fk);
    
    return list;
  }  
  
}
