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
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
    <link href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200"
          rel="stylesheet"/>
    <link href="../css/font.css" rel="stylesheet">
    <link href="../css/nav.css" rel="stylesheet">
    <style>
        img,a{
          width: 1000px;
        }
    </style>

</head>
<body>
<!--top nav -->
<%@ include file="../fragments/top-nav.jspf" %>
<%@ include file="../fragments/header.jspf" %>
<%@ include file="../fragments/bottom-nav.jspf" %>

<main>

    <main>
        <div class="container text-center">
            <img src="./images/404.png" alt="404 에러 페이지"/>
            <div>
            <a class="btn btn-outline-warning my-3 fs-4">메인 페이지 가기</a></li>
            </div>
        </div>
    </main>
</main>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>
</body>
</html>
