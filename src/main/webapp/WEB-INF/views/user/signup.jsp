<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>

<html>
<head>
    <meta charset="UTF-8">
    <title>회원가입</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
          crossorigin="anonymous">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
    <link href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" rel="stylesheet" />
    <link href="../css/font.css" rel="stylesheet" >
    <link href="../css/nav.css" rel="stylesheet" >
    <link href="../css/mypage.css" rel="stylesheet">

</head>
<body>
<!--top nav -->
<%@ include file="../fragments/top-nav.jspf" %>
<!-- Header-->
<%@ include file="../fragments/header.jspf" %>
<!-- bottom nav-->
<%@ include file="../fragments/bottom-nav.jspf" %>

<div id="join-form-wrapper" style="width: 60%; margin: auto; padding-top: 3%;">
    <h2 class="my-4">회원가입</h2>
    <div class="mb-3">
        <form>
            <div class="mb-3">
                <div class="mb-3">
                    <label for="inputUserId" class="form-label mx-2">아이디 입력<span
                            class="text-danger">*</span></label>
                    <div class="input-group  mx-2">
                        <input type="text" class="form-control" style="width: 80%"
                               id="inputUserId"
                               aria-describedby="userIdDesc" required>
                        <a id="check-duplicated-id-btn" class="btn btn-outline-success">중복 확인하기</a>
                    </div>
                    <div id="userIdDesc" class="form-text mx-2">1) 아이디는 6자 이상. 2) 영어와 숫자의 조합</div>
                </div>
                <div class="mb-3">
                    <label for="inputEmail" class="form-label  mx-2">이메일 주소<span
                            class="text-danger">*</span></label>
                    <input type="email" class="form-control mx-2" id="inputEmail"
                           aria-describedby="emailHelp" required>
                    <div id="emailDesc" class="form-text mx-2"></div>
                </div>
                <div class="mb-3">
                    <label for="inputPw" class="form-label  mx-2">비밀번호<span
                            class="text-danger">*</span></label>
                    <input type="password" class="form-control mx-2" id="inputPw" required>
                    <div id="pwHelp" class="form-text mx-2">1) 영문 숫자 혼합. 2)공백 제외 6글자 이상.</div>
                </div>
                <div class="mb-3">
                    <label for="inputPwCheck" class="form-label mx-2">비밀번호 확인<span
                            class="text-danger">*</span></label>
                    <input type="password" class="form-control mx-2" id="inputPwCheck" required/>
                    <div id="pwCheckHelp" class="form-text mx-2"></div>
                </div>
                <div class="mb-3">
                    <label for="inputPhone" class="form-label mx-2">핸드폰 번호<span
                            class="text-danger">*</span></label>
                    <input type="text" class="form-control mx-2" id="inputPhone" required/>
                    <div id="phoneHelp" class="form-text mx-2">"-"로 구분하여 작성해주세요.</div>
                    <div class="text-secondary text-opacity-50 mx-2">예) 010-0000-0000</div>
                </div>
            </div>
        </form>
    </div>
    <hr class="border border-2 opacity-75" style="margin-left: 0.5rem;margin-right: -0.5rem"/>
    <div class="mb-3">
        <div class="input-group mx-2">
            <h3>기본 배송지 입력</h3>
            <div class="form-text text-secondary mx-5">선택 사항</div>
        </div>
        <div id="address-div" class="mb-3">
            <div class="input-group m-2">
                <input type="text" id="zonecode" class="form-control" placeholder="우편번호" readonly>
                <input type="button" id="daumPostOpenBtn" class="btn btn-outline-success"
                       value="우편번호 찾기"><br>
            </div>
            <input type="text" id="address" class="form-control m-2" placeholder="주소" readonly>
            <input type="text" id="detailAddress" class="form-control m-2" placeholder="상세주소">
            <input type="text" id="recipient" class="form-control m-2" placeholder="배송 받는 사람">
            <div id="addressHelp" class="form-text mx-2">
                * 배송지 입력시 수취인과 상세 주소는 필수입니다.
            </div>
            <a id="address-reset-btn" class="btn btn-outline-warning form-control m-2">배송지 초기화</a>

            <div id="address-finder"
                 style="display:none;border:1px solid;width:500px;height:300px;margin:auto;position:relative">
                <img src="../images/close.png" id="btnFoldWrap"
                     style="cursor:pointer;position:absolute;right:0px;top:-1px;z-index:1"
                     alt="접기 버튼">
            </div>
        </div>
        <div class="mb-3">
            <button type="submit" id="submitBtn" class="btn btn-primary form-control mx-2">Submit
            </button>
        </div>
    </div>
</div>

<%@ include file="../fragments/footer.jspf"%>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>

<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
<script src="../js/member/postcode.v2.js"></script>
<script src="../js/member/kakao-addr.js"></script>
<script src="../js/member/signup.js"></script>
<script src="../js/navcart-count.js"></script>
<script src="../js/cart-list.js"></script>

</body>
</html>
