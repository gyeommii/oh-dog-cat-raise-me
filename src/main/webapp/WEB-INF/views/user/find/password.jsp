<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>

<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
          crossorigin="anonymous">
    <style>
      .login-icon {
        width: 13px;
        height: 13px;
        object-fit: contain;
      }
    </style>
</head>
<body>
<%@include file="../../fragments/header.jspf" %>

<main id="login-form-wrapper m-3" style="width: 50%; margin: auto; padding-top: 3%;">
    <div class="text-center mt-5">
        <div class="btn-group  btn-group-lg mb-3" role="group"
             aria-label="Basic radio toggle button group">
            <input type="radio" class="btn-check" id="btnradio1" autocomplete="off">
            <label class="btn btn-outline-warning" for="btnradio1">아이디 찾기</label>

            <input type="radio" class="btn-check" id="btnradio2" autocomplete="off"
                   checked>
            <label class="btn btn-outline-warning" for="btnradio2">비밀번호 찾기</label>
        </div>
    </div>

    <%--비밀번호 재설정--%>
    <div id="searchDiv" class="mb-3 card p-3" style="width: 70%; margin:auto;">
        <h2 class="my-4" style="margin: auto;">비밀번호 찾기</h2>
        <form class="mb-3 card-body" method="get" action="./result/password">
            <div class="mb-3 mt-3">
                <label for="inputEmail" class="form-label py-2"><span
                        class="fs-6">@ </span>이메일 입력</label>
                <input type="email" class="form-control my-1" id="inputEmail" name="email"
                       required>
            </div>
            <div class="mb-3 my-2">
                <label for="inputUserId" class="form-label"><span class="fs-6">📱</span>아이디
                    입력</label>
                <input type="text" class="form-control my-1" id="inputUserId" name="member_id"
                       required>
            </div>
            <div class="d-grid gap-2 d-md-flex justify-content-md-end">
                <c:url var="signInUrl" value="/user/signin"/>
                <a class="btn btn-primary btn-sm" href="${signInUrl}">로그인으로 돌아갈래요!</a>
                <c:url var="signUpUrl" value="/user/signup"/>
                <a class="btn btn-primary btn-sm" href="${signUpUrl}">회원가입할래요!</a>
            </div>
            <div class="mb-1" style="margin-top: 4%;">
                <input type="submit" class="btn btn-outline-success form-control"
                       value="비밀번호 찾기"/>
            </div>
        </form>
    </div>
</main>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>

<script src="../../js/member/find/findPassword.js"></script>

</body>
</html>
