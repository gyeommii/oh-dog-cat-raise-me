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
</head>
<body>


<div id="join-form-wrapper" style="width: 80%; margin: auto">
    <div class="mb-3">
        <form method="post">
            <div class="mb-3">
                <div class="mb-3">
                    <label for="inputUserId" class="form-label mx-2">아이디 입력<span
                            class="text-danger">*</span></label>
                    <div class="input-group  mx-2">
                        <input type="text" class="form-control" style="width: 80%"
                               id="inputUserId"
                               aria-describedby="emailHelp">
                        <a id="check-duplicated-id-btn" class="btn btn-outline-success">중복 확인하기</a>
                    </div>
                    <span id="userIdDesc" class="form-text mx-2"></span>
                </div>
                <div class="mb-3">
                    <label for="inputEmail" class="form-label  mx-2">이메일 주소<span
                            class="text-danger">*</span></label>
                    <input type="email" class="form-control mx-2" id="inputEmail"
                           aria-describedby="emailHelp">
                </div>
                <div class="mb-3">
                    <label for="inputPw" class="form-label  mx-2">비밀번호<span
                            class="text-danger">*</span></label>
                    <input type="password" class="form-control mx-2" id="inputPw">
                    <div id="pwHelp" class="form-text mx-2">1) 영문 숫자 혼합. 2)공백 제외 6글자 이상 32글자 이상으로
                        설정.
                    </div>
                </div>
                <div class="mb-3">
                    <label for="inputPwCheck" class="form-label mx-2">비밀번호 확인<span
                            class="text-danger">*</span></label>
                    <input type="password" class="form-control mx-2" id="inputPwCheck">
                    <div id="pwCheckHelp" class="form-text mx-2"></div>
                </div>
                <div class="mb-3">
                    <label for="inputPw" class="form-label mx-2">핸드폰 번호<span
                            class="text-danger">*</span></label>
                    <input type="text" class="form-control mx-2" id="exampleCheck1">
                </div>
            </div>
        </form>
    </div>
    <hr class="border border-2 opacity-75" style="margin-left: 0.5rem;margin-right: -0.5rem"/>
    <div class="mb-3">
        <h3>기본 배송지 입력</h3>
        <div id="address-div" class="mb-3">
            <div class="input-group m-2">
                <input type="text" id="postcode" class="form-control" placeholder="우편번호" readonly>
                <input type="button" class="btn btn-outline-success"
                       onclick="findAddressThrougDaumPost()" value="우편번호 찾기"><br>
            </div>
            <input type="text" id="address" class="form-control m-2" placeholder="주소" readonly>
            <input type="text" id="detailAddress" class="form-control m-2" placeholder="상세주소">

            <div id="address-finder"
                 style="display:none;border:1px solid;width:500px;height:300px;margin:auto;position:relative">
                <img src="//t1.daumcdn.net/postcode/resource/images/close.png" id="btnFoldWrap"
                     style="cursor:pointer;position:absolute;right:0px;top:-1px;z-index:1"
                     onclick="foldDaumPostcode()" alt="접기 버튼">
            </div>
        </div>
        <div class="mb-3">
            <button type="submit" id="submitBtn" class="btn btn-primary form-control mx-2">Submit</button>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>

<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="../js/kakao-addr.js"></script>
<script src="../js/signup.js"></script>

</body>
</html>
