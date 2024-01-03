<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>

<html>
<head>
    <meta charset="UTF-8">
    <title>ohdogcat - 주문내역</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
          crossorigin="anonymous">
    <link href="../css/order/order-page.css" rel="stylesheet"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
    <link href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200"
          rel="stylesheet"/>
    <link href="../css/font.css" rel="stylesheet">
    <link href="../css/nav.css" rel="stylesheet">
    <link href="../css/mypage.css" rel="stylesheet">

</head>
<body>
<!--top nav -->
<%@ include file="../fragments/top-nav.jspf" %>
<!-- Header-->
<%@ include file="../fragments/header.jspf" %>
<!-- bottom nav-->
<%@ include file="../fragments/bottom-nav.jspf" %>
<main class="outer-container row">
    <div class="col-2">
        <%@include file="../fragments/MyPageNav.jsp" %>
    </div>
    <div class="mb-3 col-8 container my-page-container">
        <h2 class="order-header text-center">주문 내역 확인하기</h2>
        <c:if test="${at eq 'O'}">
            <div class="alert alert-warning alert-dismissible fade show" role="alert">
                <strong>구매 완료!</strong>
                <br/>냥 냥냥 냥냥냥냥 냥냥냥 냥냥냥냥! (똑똑한 집사들은 이해할 거라고 믿는다냥!)
                <button type="button" class="btn-close" data-bs-dismiss="alert"
                        aria-label="Close"></button>
            </div>
        </c:if>
        <div>
            <div class="accordion accordion-flush inner-wrapper my-3" id="accordionExample">
                <div class="accordion-item" style="border: 0px">
                    <div class="small_header accordion-header flex-box-start">
                        <span class="fw-bolder py-3 fs-4">${purchaseStatus.purchase_status}</span>

                        <span class="d-flex align-items-center p-2">
                  <svg
                          xmlns="http://www.w3.org/2000/svg"
                          width="12"
                          height="12"
                          fill="currentColor"
                          class="bi bi-play-fill"
                          viewBox="0 0 12 12"
                          style="opacity: 0.2; border-radius: 50%"
                  >
                    <path
                            d="m11.596 8.697-6.363 3.692c-.54.313-1.233-.066-1.233-.697V4.308c0-.63.692-1.01 1.233-.696l6.363 3.692a.802.802 0 0 1 0 1.393z"
                    />
                  </svg>
                </span>
                        <div class="d-flex align-items-end">
                            <span class="fw-normaler py-3 fs-5 text-secondary align-text-bottom">${purchase.purchase_date.year}년 ${purchase.purchase_date.monthValue}월 ${purchase.purchase_date.dayOfMonth}일 구매</span>
                        </div>
                    </div>
                    <div id="collapseOne" class="accordion-collapse collapse show"
                         data-bs-parent="#accordionExample">
                        <div class="accordion-body" style="padding-top: 0px">
                            <!-- 구매 상품 리스트 작성 -->
                            <%--                            <div--%>
                            <%--                                    class="list-group-item mt-3 pb-4 option-info-to-order"--%>
                            <%----%>
                            <%--                            >--%>
                            <c:forEach var="product" items="${products}">
                                <div class="text-center p-2 list-group-item mt-3 option-info-to-order"
                                     style="border-bottom: 1px lightgray solid">
                                    <div class="row fw-semibold">
                                        <!-- 1. 상품 영역  -->
                                        <div class="flex-box-start"></div>
                                        <div class="col-8">
                                            <div class="row align-self-center">
                                                <!-- 상품 사진 -->
                                                <div class="col-2">
                                                    <img
                                                            src="${product.img_url}"
                                                            class="img-fluid rounded"
                                                            alt="product Img"
                                                            style="width: 100%"
                                                    />
                                                </div>
                                                <!-- 상품 이름-->
                                                <div class="col-9 align-self-center fw-normal"
                                                     style="text-align: left">
                                                    <div class="row">
                                                        <div class="col">
                                                            <div class="fw-semibold"
                                                                 style="font-size: 16px">${product.product_name}
                                                            </div>
                                                            <div class="fs-6 pt-1">옵션:
                                                                [${product.option_name}]
                                                                    ${product.price}
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <!-- 2. 수량 영역 -->
                                        <div class="col-2 align-self-center">
                                            <div class="input-group justify-content-center">
                                                <div
                                                        type="text"
                                                        class="text-center align-bottom fs-6"
                                                        id="count"
                                                        style="border: 0; width: 40px; font-size: 0.8em; height: 30px"
                                                >
                                                    <span>${product.count}</span> 개
                                                </div>
                                            </div>
                                        </div>
                                        <!-- 3. 주문 금액 영역 -->
                                        <div class="col-2 align-self-center">
                                            <div style="font-size: 18px">${product.count * product.price}</div>
                                            <div class="fs-6 fw-normal">무료배송</div>
                                            <hr/>
                                            <c:choose>
                                                <c:when test="${purchaseStatus.status_pk eq 6}">
                                                    <a class="btn btn-outline-primary btn-sm mb-2"
                                                       href="../review/${product.option_fk}">리뷰 작성
                                                    </a>
                                                </c:when>
                                                <c:otherwise>
                                                    <a class="btn btn-secondary btn-sm mb-2"
                                                       href="#">리뷰 작성
                                                    </a>
                                                </c:otherwise>
                                            </c:choose>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                    <%--                    </div>--%>
                </div>
            </div>
            <!-- 주문자 정보 컴포넌트 -->
            <div class="my-3">
                <div class="small_header accordion-header" style="justify-content: flex-start">
                    <h4>주문자 정보</h4>
                </div>
                <div class="text-secondary text-opacity-75 text-end font-small fw-normaler information-div">
                    정보 수정은 '마이 페이지 > 내 정보 수정하기'에서 가능합니다
                </div>
                <div class="outer-wrapper-content">
                    <div class="py-1 inner-wrapper-content">
                        <span class="display-key fw-bold font-small">보내는 분 아이디</span>
                        <div id="member_id"
                             class="inner-content fw-normal text-opacity-75 font-small">${member.member_id}
                        </div>
                    </div>

                    <div class="py-1 inner-wrapper-content">
                        <span class="display-key fw-bold font-small">전화번호</span>
                        <div id="member_phone"
                             class="inner-content fw-normal text-opacity-75 font-small">
                            ${member.phone}
                        </div>
                    </div>

                    <div class="py-1 inner-wrapper-content">
                        <span class="display-key fw-bold font-small">이메일</span>
                        <div class="inner-content fw-normal text-opacity-75 font-small">
                            ${member.email}
                        </div>
                    </div>
                </div>
            </div>
            <!-- 배송지 정보 컴포넌트 -->
            <div class="my-3">
                <div class="small_header accordion-header" style="justify-content: flex-start">
                    <h4>배송지 정보</h4>
                </div>
                <div class="outer-wrapper-content pt-3">
                    <div class="py-1 inner-wrapper-content">
                        <span class="display-key fw-bold font-small">우편번호</span>
                        <div class="inner-content fw-normal text-opacity-75 font-small">${address.zonecode}</div>
                    </div>

                    <div class="py-1 inner-wrapper-content">
                        <span class="display-key fw-bold font-small">주소</span>
                        <div class="inner-content fw-normal text-opacity-75 font-small">${address.address}
                        </div>
                    </div>

                    <div class="py-1 inner-wrapper-content">
                        <span class="display-key fw-bold font-small">상세주소</span>
                        <div class="inner-content fw-normal text-opacity-75 font-small">${address.detail_addr}
                        </div>
                    </div>

                    <div class="py-1 inner-wrapper-content">
                        <span class="display-key fw-bold font-small">받는 사람</span>
                        <div class="inner-content fw-normal text-opacity-75 font-small">${address.recipient}</div>
                    </div>
                </div>
            </div>
            <!-- 배송지 완료 -->
            <!-- 적립금 사용 div-->

            <div class="my-3 pb-4">
                <div class="small_header accordion-header" style="justify-content: flex-start">
                    <h4>결제 정보</h4>
                </div>
                <div class="py-1 inner-wrapper-content pt-3">
                    <span class="display-key fw-bold font-small">결제수단</span>
                    <div class="inner-content fw-normal text-opacity-75 font-small">${payment.pay_method}
                    </div>
                </div>
                <div class="py-1 inner-wrapper-content">
                    <span class="display-key fw-bold font-small">총 상품 가격</span>
                    <div id="member_email"
                         class="inner-content fw-normal text-opacity-75 font-small">${payment.total_price}원
                    </div>
                </div>
                <div class="py-1 inner-wrapper-content">
                    <span class="display-key fw-bold font-small">포인트 사용 금액</span>
                    <div class="inner-content fw-normal text-opacity-75 font-small">${payment.used_point}원
                    </div>
                </div>
                <div class="py-1 inner-wrapper-content">
                    <span class="display-key fw-bold font-small">실제 결제 금액</span>
                    <div class="inner-content fw-normal text-opacity-75 font-small">${payment.paid_price}원
                    </div>
                    <div class="text-secondary text-opacity-75 text-end font-small fw-lighter information-div">
                        무통장 입금을 선택하신 경우, 입금이 확인된 후 결제가 이루어진 것으로 판단됩니다.
                    </div>
                </div>
                <hr/>
                <div class="py-1 inner-wrapper-content">
                    <span class="display-key fw-bold font-small">포인트 적립 금액</span>
                    <div class="inner-content fw-normal text-opacity-75 font-small row-2 text-center">
                        ${payment.accumulated_point}원
                    </div>
                    <span class="badge bg-success">적립 완료</span>

                    <span class="badge bg-warning mx-2">적립 예정</span>
                    <div class="text-secondary text-opacity-75 text-end font-small fw-lighter information-div">
                        포인트 적립은 구매 확정 시 완료됩니다.
                    </div>
                </div>
            </div>

            <div class="container d-flex pt-3" style="justify-content: space-between">
                <a id="toListBtn" class="btn btn-outline-success" style="width: 17%"
                   href="../mypage/purchaseList">
                    <svg
                            xmlns="http://www.w3.org/2000/svg"
                            width="16"
                            height="16"
                            fill="currentColor"
                            class="bi bi-arrow-bar-left"
                            viewBox="0 0 16 16"
                    >
                        <path
                                fill-rule="evenodd"
                                d="M12.5 15a.5.5 0 0 1-.5-.5v-13a.5.5 0 0 1 1 0v13a.5.5 0 0 1-.5.5M10 8a.5.5 0 0 1-.5.5H3.707l2.147 2.146a.5.5 0 0 1-.708.708l-3-3a.5.5 0 0 1 0-.708l3-3a.5.5 0 1 1 .708.708L3.707 7.5H9.5a.5.5 0 0 1 .5.5"></path>
                    </svg>
                    내 주문목록 가기
                </a>
                <button id="cancelPurchaseBtn" class="btn btn-outline-success" style="width: 15%"
                        <c:choose>
                            <c:when test="${purchase.status_fk eq 1 or purchase.status_fk eq 2 or purchase.status_fk eq 3}"/>
                            <c:otherwise>disabled</c:otherwise>
                        </c:choose>
                >결제취소
                </button>
            </div>
        </div>
    </div>
</main>

<input type="hidden" value="${purchase.purchase_pk}" id="purchasePk"/>

<%@ include file="../fragments/footer.jspf" %>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
<script src="../js/order/purchase-detail.js"></script>
<script src="../js/navcart-count.js"></script>
<script src="../js/cart-list.js"></script>

</body>
</html>
