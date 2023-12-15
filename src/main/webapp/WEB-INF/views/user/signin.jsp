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
<div class="p-4" style="height: 50%">
    <h3 class="text-center">키워주개냥!</h3>
</div>


<main id="login-form-wrapper m-3" style="width: 50%; margin: auto">
    <div class="mb-3 card p-3" style="width: 70%; margin:auto">
        <form class="mb-3 card-body" method="post">
            <div class="mb-3">
                <label for="inputUserId" class="form-label p-2"><img class="login-icon m-2"
                                                                     src="../images/user.png"/>아이디<span
                        class="text-danger">*</span></label>
                <input type="text" class="form-control mx-2" id="inputUserId" name="member_id"
                       required>
            </div>
            <div class="mb-3">
                <label for="inputPw" class="form-label mx-2"><img class="login-icon m-2"
                                                                  src="../images/locker.png"/>비밀번호<span
                        class="text-danger">*</span></label>
                <input type="password" class="form-control mx-2" id="inputPw" name="password"
                       required>
            </div>
            <div id="login-result-desc" class="text-danger d-none">아이디(로그인 전용 아이디) 또는 비밀번호를 잘못
                입력했습니다.<br/>
                입력하신 내용을 다시 확인해주세요.
            </div>
            <input type="submit" id="loginBtn" class="btn btn-outline-success" value="로그인"/>
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
