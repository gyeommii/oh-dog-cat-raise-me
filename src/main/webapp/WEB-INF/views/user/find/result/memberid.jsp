<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>

<html>
<head>
    <meta charset="UTF-8">
    <title>아이디 찾기</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
          crossorigin="anonymous">
</head>
<body>
<%@include file="../../../fragments/header.jspf" %>


<main id="login-form-wrapper m-3" style="width: 50%; margin: auto; padding-top: 3%;">
    <div id="searchDiv" class="mb-3 card p-3" style="width: 70%; margin:auto">
        <div class="mb-3 card-body" style="height: 100px;">
            <h2 class="card-header">아이디 찾기</h2>
            <div style="height: 100px;">
                <p class="card-text align-middle">
                    당신의 아이디는
                    <mark class="fw-bold badge text-bg-info">${memberid}</mark>
                    입니다.
                </p>
            </div>
        </div>
        <div class="text-center  mt-5 input-group">
            <a class="btn btn-outline-warning form-control" href="../password">비밀번호 바꾸러 가기 가기!</a>
            <a class="btn btn-outline-success form-control" href="../../signin">로그인하러 가기!</a>
            <c:url var="signUpUrl" value="/user/signup"/>
            <a class="btn btn-primary btn-sm" href="${signUpUrl}">회원가입할래요!</a>
        </div>
    </div>

</main>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>

</body>
</html>
