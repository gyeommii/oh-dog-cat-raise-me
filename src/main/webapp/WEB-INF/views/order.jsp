<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>

<html>
<head>
    <meta charset="UTF-8">
    <title>ohdogcat - 구매하기</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
          crossorigin="anonymous">
    <link href="./css/order/order-page.css" rel="stylesheet"/>
</head>

<body>

<%@ include file="fragments/header.jspf" %>

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
                            ▼
                        </button>
                        <!-- ▲ -->
                    </div>
                    <div id="collapseOne" class="accordion-collapse collapse show"
                         data-bs-parent="#accordionExample">
                        <div class="accordion-body">
                            <!-- 구매 상품 리스트 작성 -->
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
                        <div class="inner-content fw-light text-opacity-75 font-small">
                            tlacodnjs667
                        </div>
                    </div>

                    <div class="py-1 inner-wrapper-content">
                        <span class="display-key fw-bold font-small">전화번호</span>
                        <div class="inner-content fw-light text-opacity-75 font-small">
                            010-0000-0000
                        </div>
                    </div>

                    <div class="py-1 inner-wrapper-content">
                        <span class="display-key fw-bold font-small">이메일</span>
                        <div class="inner-content fw-light text-opacity-75 font-small">
                            tlacodnjs667@gmail.com
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
                                <option value="경기도 광명시 철산동 55-1 광복현대아파트 104-289" selected>경기도 광명시
                                    철산동 55-1 광복현대아파트 104-289 (기본 배송지)
                                </option>
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
                                        class="btn btn-outline-secondary font-small btn-sm">다른 주소로
                                    변경
                                </button>
                                <button id="exec-add-addr"
                                        class="btn btn-outline-secondary font-small btn-sm">배송지 옵션으로
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
                            <span>1,000 원</span>
                        </div>
                    </div>

                    <div class="py-1 inner-wrapper-content input-group">
                        <div class="display-key mr-1 font-small">
                            <span class="fw-bold">ㄴ</span>
                            <span class="fw-bold font-small">사용할 포인트</span></div>
                        <input class="inner-content form-control fw-light text-opacity-75 font-small"
                               id="point-to-use-input"/>
                        <button class="btn btn-outline-secondary">적용</button>
                    </div>

                    <hr/>
                    <div class="py-1 inner-wrapper-content">
                        <div class="display-key fw-bold font-small fs-6">예상 잔여 포인트</div>
                        <div class="inner-content fw-light text-opacity-75 font-small input-group">
                            <div class="font-small fs-5">1000원</div>
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
                    <div class="flex-box input-group" style="flex : 1 1 0%">
                        <button class="btn btn-outline-warning form-control" onclick="requestPay()">
                            카카오로 결제하기
                        </button>
                        <button class="btn btn-outline-info form-control">무통장입금 결제하기</button>
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
                <h5>293000원</h5>
            </div>
            <div class="flex-box">
                <h6 class="text-secondary">적립금 사용</h6>
                <div>- 293000원</div>
            </div>
            <hr/>
            <div class="flex-box">
                <h6 class="fw-bold">최종<br/>결제금액</h6>
                <div class="fs-4">293000원</div>
            </div>
        </div>
    </div>
</main>


<script src="https://cdn.iamport.kr/v1/iamport.js"></script>

<script>
  const distinguishCode = `imp24187885`;
  const REST_API_KEY = `6075557175583251`;
  const REST_API_SECRET = `CktVTCKBSlu8kS2gVkwLGrmMriWOGz71p3JDqQoy7uxq0viw5Izm2tFufBvuNIBiTL1VnoZ32KhtLPdS`;

  IMP.init(distinguishCode);

  function requestPay() {
    IMP.request_pay({
      pg: "kakaopay",
      pay_method: "card",
      merchant_uid: `ORD20180131-${crypto.randomUUID}`,   // 주문번호
      name: "노르웨이 회전 의자",
      amount: 64900,                         // 숫자 타입
      buyer_email: "gildong@gmail.com",
      buyer_name: "홍길동",
      buyer_tel: "010-4242-4242",
      buyer_addr: "서울특별시 강남구 신사동",
      buyer_postcode: "01181"
    }, function (rsp) {
      console.log(rsp);
    });
  }

</script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>

<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="./text.js"></script>
</body>
</html>
