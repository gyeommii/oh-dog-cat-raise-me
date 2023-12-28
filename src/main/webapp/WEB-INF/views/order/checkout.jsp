<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>

<html>
<head>
    <meta charset="UTF-8">
    <title>ohdogcat - 구매하기</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
          crossorigin="anonymous">
    <link href="../css/order/order-page.css" rel="stylesheet"/>

</head>

<body>

<%@ include file="../fragments/header.jspf" %>

<main>
    <div class="order-wrapper outer-wrapper-content mb-3">
        <h2 class="order-header text-center">주문서</h2>
        <div>
            <div class="accordion accordion-flush inner-wrapper my-3" id="accordionExample">
                <div class="accordion-item" style="border: 0px;">
                    <div class="small_header accordion-header">
                        <h4>주문 상품</h4>
                        <button id="accordion-button-id" class="accordion-button" type="button"
                                data-bs-toggle="collapse" data-bs-target="#collapseOne"
                                aria-controls="collapseOne" accordion-flush>
                            ▲
                        </button>
                    </div>
                    <div id="collapseOne" class="accordion-collapse collapse show"
                         data-bs-parent="#accordionExample">
                        <div class="accordion-body" style="padding-top: 0px">
                            <!-- 구매 상품 리스트 작성 -->
                            <c:forEach var="product" items="${products}">
                                <div class="list-group-item mt-3 pb-4 option-info-to-order"
                                        <c:if test="${product.count <= product.stock}">
                                            data-count=${product.count}
                                            data-optionfk=${product.option_fk}
                                        </c:if>
                                        <c:if test="${product.count > product.stock}">
                                            data-count=${product.stock}
                                            data-optionfk=${product.option_fk}
                                        </c:if>

                                     style="border-bottom: 1px lightgray solid">
                                    <div class="text-center p-2">
                                        <div class="row fw-semibold">
                                            <!-- 1. 상품 영역  -->
                                            <div class="col-8">
                                                <div class="row">
                                                    <!-- 상품 사진 -->
                                                    <div class="col-2">
                                                        <img src="${product.img_url}"
                                                             class="img-fluid rounded"
                                                             alt="product Img">
                                                    </div>
                                                    <!-- 상품 이름-->
                                                    <div class="col-9 align-self-center fw-normal"
                                                         style="text-align: left;">
                                                        <div class="row">
                                                            <div class="col">
                                                                <div class="fw-semibold"
                                                                     style="font-size: 16px;">${product.product_name}
                                                                </div>
                                                                <div class="fs-6 pt-1 text-black-50">
                                                                    옵션:
                                                                    [${product.option_name}] ${product.price}
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <!-- 2. 수량 영역 -->
                                            <div class="col-2 align-self-center">
                                                <div class="input-group justify-content-center">
                                                    <div type="text"
                                                         class="text-center align-bottom fs-6"
                                                         id="count"
                                                         style="border: 0; width: 40px; font-size: 0.8em; height: 30px;">
                                                        <c:if test="${product.count <= product.stock}">
                                                            <span>${product.count}</span> 개
                                                        </c:if>
                                                        <c:if test="${product.count > product.stock}">
                                                            <span>${product.stock}</span> 개
                                                        </c:if>
                                                    </div>

                                                </div>
                                            </div>
                                            <!-- 3. 주문 금액 영역 -->
                                            <div class="col-2 align-self-center">
                                                <div style="font-size: 18px;">${product.count * product.price}</div>
                                                <div class="fs-6 fw-normal text-black-50">무료배송</div>
                                            </div>
                                        </div>
                                    </div>
                                    <c:if test="${product.count > product.stock}">
                                        <div class="text-danger text-opacity-50 text-end">
                                            선택한 수량(${product.count})이 재고(${product.stock})보다 많습니다.
                                            최대 구매 가능 개수로 변경되었습니다.
                                        </div>
                                    </c:if>
                                </div>
                            </c:forEach>
                            <%--li 끝--%>
                        </div>
                    </div>
                </div>
            </div>
            <!-- 주문자 정보 컴포넌트 -->
            <div class="my-3">
                <div class="small_header accordion-header" style="justify-content: flex-start;">
                    <h4>주문자 정보</h4>
                </div>
                <div class="text-secondary text-opacity-75 text-end font-small fw-lighter information-div">
                    정보 수정은 '마이 페이지 > 내 정보 수정하기'에서 가능합니다
                </div>
                <div class="outer-wrapper-content">

                    <div class="py-1 inner-wrapper-content">
                        <span class="display-key fw-bold font-small">보내는 분 아이디</span>
                        <div id="member_id"
                             class="inner-content fw-light text-opacity-75 font-small">
                            ${member.member_id}
                        </div>
                    </div>

                    <div class="py-1 inner-wrapper-content">
                        <span class="display-key fw-bold font-small">전화번호</span>
                        <div id="member_phone"
                             class="inner-content fw-light text-opacity-75 font-small">
                            ${member.phone}
                        </div>
                    </div>

                    <div class="py-1 inner-wrapper-content">
                        <span class="display-key fw-bold font-small">이메일</span>
                        <div id="member_email"
                             class="inner-content fw-light text-opacity-75 font-small">
                            ${member.email}
                        </div>
                    </div>
                </div>
            </div>
            <!-- 배송지 정보 컴포넌트 -->
            <div class="my-3">
                <div class="small_header accordion-header" style="justify-content: flex-start;">
                    <h4>배송지 정보</h4>
                </div>
                <div class="text-secondary text-opacity-75 text-end font-small fw-lighter information-div">
                    기본 배송지와 최근 5개 주문 내역의 배송지만 보여집니다. 추가하기 버튼을 눌러 배송지를 추가하실 수 있습니다.
                </div>
                <div class="outer-wrapper-content">
                    <div class="py-1 inner-wrapper-content">
                        <span class="display-key fw-bold font-small">배송지</span>
                        <div class="input-group">
                            <select id="addr-selected" class="form-select form-select-sm"
                                    style="font-size: 12px;"
                                    aria-label="Small select example">
                                <option value=${address.address_pk} selected>
                                    ${address.address} ${address.detail_addr}
                                    | ${address.recipient}
                                    <span class="text-secondary">(기본 배송지)</span>
                                </option>
                                <c:if test="${not empty addressOrdered}">
                                    <c:forEach var="addr" items="${addressOrdered}">
                                        <option value=${addr.address_pk}>
                                                ${addr.address} ${addr.detail_addr}
                                            | ${addr.recipient}
                                            <span class="text-lighter">(최근 주문 내역)</span>
                                        </option>
                                    </c:forEach>
                                </c:if>
                            </select>
                            <input type="button" id="daumPostOpenBtn"
                                   class="btn btn-outline-warning font-small text-wrap"
                                   value="추가하기"><br>
                        </div>
                    </div>
                </div>
                <div id="add-address" class="card d-none" style="width: 100%;">
                    <div class="card-body">
                        <h5 class="card-header">주소지 추가하기</h5>
                        <div class="card-body">
                            <input class="form-control form-control-sm" type="text form-control"
                                   id="input-postcode" placeholder="우편번호" readonly>
                            <input class="form-control form-control-sm" type="text"
                                   id="input-address" placeholder="주소" readonly>
                            <input class="form-control form-control-sm" type="text"
                                   id="input-detail-addr" placeholder="상세주소">
                            <input class="form-control form-control-sm" type="text form-control"
                                   id="input-recipient" placeholder="수취인">
                            <div class="input-group">
                                <button id="daumPostOpenBtnInDiv"
                                        class="btn btn-outline-secondary font-small btn-sm">다른
                                    주소로
                                    변경
                                </button>
                                <button id="exec-add-addr"
                                        class="btn btn-outline-secondary font-small btn-sm">배송지
                                    옵션으로
                                    추가하기
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- 배송지 완료 -->
            <!-- 적립금 사용 div-->

            <div class="my-3">
                <div class="small_header accordion-header" style="justify-content: flex-start;">
                    <h4>My 포인트</h4>
                </div>
                <div class="text-secondary text-opacity-75 text-end font-small fw-lighter information-div">
                    회원 등급에 따라 다른 적립금이 적용됩니다.
                </div>
                <div class="outer-wrapper-content">

                    <div class="py-1 inner-wrapper-content">
                        <span class="display-key fw-bold font-small">사용 가능 포인트</span>
                        <div class="inner-content fw-light text-opacity-75 font-small">
                            <span class="float-end fs-3" id="memberPoint">
                                <span id="pointCanUse"><f:formatNumber value="${member.point}"
                                                                       pattern="#,###"/></span>원</span>
                        </div>
                    </div>

                    <div class="py-1 inner-wrapper-content input-group my-1">
                        <div class="display-key mr-1 font-small">
                            <span class="fw-bold">ㄴ</span>
                            <span class="fw-bold font-small">사용할 포인트</span></div>
                        <input class="inner-content form-control fw-light text-opacity-75 font-small"
                               id="point-to-use-input" placeholder="숫자만 입력해주세요."/>
                        <button class="btn btn-outline-secondary" id="applyPointBtn" disabled>적용
                        </button>
                    </div>

                    <hr/>
                    <div class="py-1 inner-wrapper-content">
                        <div class="display-key fw-bold font-small fs-6">예상 잔여 포인트</div>
                        <div class="inner-content fw-light text-opacity-75 font-small input-group">
                            <div class="font-small fs-5"><span id="reservedPoint"
                                                               class="font-small fs-5"><f:formatNumber
                                    value="${member.point}"
                                    pattern="#,###"/></span>원
                            </div>
                        </div>
                    </div>
                </div>
            </div>


            <div class="my-3">
                <div class="small_header accordion-header" style="justify-content: flex-start;">
                    <h4>결제하기</h4>
                </div>
                <div class="flex-box inner-wrapper-content my-3">
                    <div class="display-key fw-bold font-small my-1"> 결제 선택</div>
                    <%--                    test --%>
                    <div class="btn-group" role="group" aria-label="Basic radio toggle button group"
                         style="width: 60%">
                        <input type="radio" class="btn-check" name="btnradio" id="kakaopayBtn"
                               autocomplete="off">
                        <label class="btn btn-outline-primary" for="kakaopayBtn">kakao</label>

                        <input type="radio" class="btn-check" name="btnradio" id="banktransferBtn"
                               autocomplete="off">
                        <label class="btn btn-outline-primary" for="banktransferBtn">무통장 입금</label>
                    </div>
                    <%--                  test  --%>
                </div>
                <div id="kakaopay-window" class="d-none">

                    <%--                    --%>
                    <div class="alert alert-warning alert-dismissible fade show" role="alert">
                        <div class="alert-text fw-bold">결제 진행</div>
                        <p class="mb-0">결제하기 버튼을 눌러 구매를 완료해주세요!</p>
                    </div>
                    <%--                    --%>
                </div>
                <div id="account-window" class="d-none">
                    <div class="flex-box inner-wrapper-content my-3">
                        <div class="small_header accordion-header"
                             style="justify-content: flex-start;">
                            <h6>무통장 입금</h6>
                        </div>
                        <div class="text-secondary text-opacity-75 text-end font-small fw-lighter information-div">
                            무통장 입금 입금 확인 후 배송됩니다.
                        </div>
                    </div>
                    <div id="divAlert">
                        <div id="alertBankTransferInfo"
                             class="alert alert-warning alert-dismissible fade show" role="alert">
                            <strong>2023년 12월 29일</strong>까지 미입금 시 자동취소 됩니다.
                            <button type="button" class="btn-close" data-bs-dismiss="alert"
                                    aria-label="Close"></button>
                        </div>
                    </div>
                    <div class="outer-wrapper-content">
                        <div class="py-1 inner-wrapper-content">
                            <span class="display-key fw-bold font-small">신한</span>
                            <div class="inner-content fw-light text-opacity-75 font-small">
                                110-433-830637 (심채원)
                            </div>
                        </div>

                        <div class="py-1 inner-wrapper-content">
                            <span class="display-key fw-bold font-small">국민</span>
                            <div class="inner-content fw-light text-opacity-75 font-small">
                                854702-02-01164494 (유은겸)
                            </div>
                        </div>

                        <div class="py-1 inner-wrapper-content">
                            <span class="display-key fw-bold font-small">새마을금고</span>
                            <div class="inner-content fw-light text-opacity-75 font-small">
                                9003-2005-1951-6 (임유정)
                            </div>
                        </div>
                    </div>
                </div>

            </div>

        </div>

        <!-- fixed박스 -->
        <div id="order-result-div">
            <h4>결제 금액</h4>
            <div style="border: 1px gray solid; padding: 10px;">
                <div class="flex-box mb-3">
                    <h5>주문금액</h5>
                    <h5><span id="totalPrice"><f:formatNumber value="${totalPrice}"
                                                              pattern="#,###"/></span>원</h5>
                </div>
                <div class="flex-box">
                    <h6 class="text-secondary">적립금 사용</h6>
                    <h5 class="text-secondary"><span class="text-secondary"
                                                     id="pointToUse">0</span>원
                    </h5>
                </div>
                <hr/>
                <div class="flex-box">
                    <h6 class="fw-bold text-center">최종<br/>결제금액</h6>
                    <h5><span id="priceToPay"><f:formatNumber value="${totalPrice}"
                                                              pattern="#,###"/></span>원</h5>
                </div>
            </div>
        </div>
        <div id="success-complete-card" class="card d-none" style="width: 100%;">
            <div class="card-body">
                <h5 class="card-title">결제 완료</h5>
                <h6 class="card-subtitle mb-2 text-body-secondary">결제가 완료되었습니다!</h6>
                <p class="card-text">구매를 완료하시려면 하단 구매 완료 버튼을 눌러주세요!</p>
            </div>
        </div>
        <input id="orderName" type="hidden" name="orderName" value="${orderName}"/>
        <div class="input-group my-3">
            <input id="order-submit-btn" type="submit"
                   class="btn btn-outline-success form-control" value="결제 하기" disabled>
            <a class="btn btn-outline-success form-control" href="/ohdogcat/">메인으로 가기</a>
        </div>

    </div>
</main>


<script src="../js/order/iamport.js"></script>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>

<script src="../js/order/save-order-info.js"></script>
<script src="../js/order/order-pay.js"></script>
<script src="../js/member/postcode.v2.js"></script>
<script src="../js/order/calculatePrice.js"></script>
<script src="../js/order/order-page.js"></script>

</body>
</html>
