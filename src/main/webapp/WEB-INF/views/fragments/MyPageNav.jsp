<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>

<c:url var="memberMyPage" value="/mypage/member"/>
<c:url var="myPetPage" value="/mypage/pet"/>
<c:url var="myPurchasePage" value="/mypage/purchaseList"/>
<c:url var="addPetUrl" value="/mypage/addpet"/>
<c:url var="wishListPage" value="/mypage/wishlist" />
<c:url var="reviewListPage" value="/mypage/review" />

<div id="my-page-nav" class="pt-6">
    <div class="text-center">
        <div><span class="fw-bold">${signedMember.member_id}</span> 님</div>
        <div>💰포인트 : <span id="pointDiv"></span>원</div>
    </div>
    <div>
        <nav id="navbar-example3" role="navigation"
             class="h-100 flex-column align-items-stretch pt-3">
            <nav class="nav nav-pills flex-column">
                <h5 class="nav-link text-bg-light p-2"><a href="${memberMyPage}"
                                                          class="link-body-emphasis">내 정보</a>
                </h5>
                <nav class="nav nav-pills flex-column">
                    <a class="nav-link ms-1 link-body-emphasis" href="${memberMyPage}">내 정보
                        보기</a>
                </nav>
                <h5 class="nav-link text-bg-light p-2"><a href="${myPetPage}"
                                                          class="link-body-emphasis">마이펫</a>
                </h5>
                <nav class="nav nav-pills flex-column">
                    <a class="nav-link ms-1 link-body-emphasis" href="${myPetPage}">나의 펫 리스트</a>
                    <a class="nav-link ms-1 link-body-emphasis" href="${addPetUrl}">펫 추가하기</a>
                </nav>
                <h5 class="nav-link text-bg-light p-2 link-body-emphasis"><a href="${myPurchasePage}">나의 쇼핑</a>
                </h5>
                <nav class="nav nav-pills flex-column">
                    <a class="nav-link ms-1 link-body-emphasis" href="${wishListPage}">위시리스트</a>
                    <a class="nav-link ms-1 link-body-emphasis" href="${myPurchasePage}">구매 내역
                        확인하기</a>
                    <a class="nav-link ms-1 link-body-emphasis" href="${reviewListPage}">내 리뷰 보기</a>
                </nav>
            </nav>
        </nav>
    </div>
</div>