<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<div>
    <div>
        <div>
            <div>
                <p>${member.member_id}</p>
                <p>${review.created_date.year}/${review.created_date.monthValue}/${review.created_date.dayOfMonth}</p>
            </div>
        </div>
    </div>
    <div>
        <span class="review-list__rating">
                          <span class="review-list__rating__unit">
                            <span class="review-list__rating__active" style="width: 100%"></span>
                          </span>
                        </span>
    </div>
    <div data-review-no="49220227" data-goods-no="3481221">
        <div>
            ${review.content}
        </div>
        <!-- 이미지 -->

        <div>
            <c:url var="imgGetUrl" value="/image">
                <c:param name="imgUrl" value="${review.img_url}" />
            </c:url>
            <img src="${imgGetUrl}"/>
        </div>
    </div>
    <div >
        <ul >
            <li>
                <input type="checkbox" name="reviewEvaluation02" id="helpfulCount" value="H" autocomplete="off"/>
                <label for="helpfulCount"
                        onclick="ReviewReactions.toggleReviewReaction('goods', 49220227, 'H');return false;"
                >도움돼요<span>${review.count}</span></label
                >
            </li>
        </ul>
    </div>
    >

    <!-- //댓글-->
</div>