<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>

<html>
<head>
    <meta charset="UTF-8">
    <title>로그인</title>
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
<%@include file="../fragments/header.jspf" %>

<main id="login-form-wrapper m-3" style="width: 50%; margin: auto; padding-top: 3%;">
    <div class="mb-3 card p-3" style="width: 70%; margin:auto">
        <h2 class="my-4" style="margin: auto">로그인</h2>
        <form class="mb-3 card-body" method="post">
            <div class="mb-3 my-3">
                <label for="inputUserId" class="form-label py-2"><img class="login-icon my-1"
                                                                      src="../images/user.png"/>아이디<span
                        class="text-danger">*</span></label>
                <input type="text" class="form-control my-1" id="inputUserId" name="member_id"
                       required>
            </div>
            <div class="mb-3 my-2">
                <label for="inputPw" class="form-label"><img class="login-icon my-1"
                                                             src="../images/locker.png"/>비밀번호<span
                        class="text-danger">*</span></label>
                <input type="password" class="form-control my-1" id="inputPw" name="password"
                       required>
            </div>
            <div class="d-grid gap-2 d-md-flex justify-content-md-end">
                <c:url var="findMemberIdUrl" value="/user/find/memberid"/>
                <c:url var="findPasswordUrl" value="/user/find/password"/>

                <a class="btn btn-primary btn-sm" href="${findMemberIdUrl}">아이디 찾기</a>
                <a class="btn btn-secondary btn-sm" href="${findPasswordUrl}">비밀번호 재설정</a>
            </div>
            <div class="mb-3" style="margin-top: 7%;">
                <div id="login-result-desc">아이디(로그인 전용 아이디) 또는 비밀번호를 잘못
                    입력했습니다.<br/>
                    입력하신 내용을 다시 확인해주세요.
                </div>
                <input type="submit" id="loginBtn" class="btn btn-outline-success form-control"
                       value="로그인"/>
            </div>
        </form>
    </div>
</main>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>

<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
<script src="../js/member/signin.js"></script>

</body>
</html>
