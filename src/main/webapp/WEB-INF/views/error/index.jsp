<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>오잉!</title>
    <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
            crossorigin="anonymous">
    <link
            href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css"
            rel="stylesheet"/>
    <link
            href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200"
            rel="stylesheet"/>

    <c:url var="fontCss" value="/css/font.css" />
    <c:url var="navCss" value="/css/nav.css" />

    <link href="${fontCss}" rel="stylesheet">
    <link href="${navCss}" rel="stylesheet">

    <style>
      .wid1000 {
        width: 800px;
      }
      #errorImg{
        object-fit: contain;
      }
    </style>
</head>
<body>


<!--top nav -->

<%@ include file="../fragments/top-nav.jspf" %>
<!-- Header-->
<%@ include file="../fragments/header.jspf" %>
<!-- bottom nav-->
<%@ include file="../fragments/bottom-nav.jspf" %>
<main>
    <div class="container text-center">
        <c:url var="errorImageUrl" value="/images/error/${errorStatus}.png" />

        <img id="errorImg" class="wid1000 mt-5" src="${errorImageUrl}"
             alt="${errorStatus} 에러 페이지"/>
        <div>
            <a class="btn btn-outline-warning my-3 fs-4 wid1000" href="${mainPage}">메인 페이지 가기</a>
        </div>
        <div class="my-5">
            <h3>다양한 에러 코드를 체험해보세요우</h3>
            <c:url var="errorPage400" value="/error/400"/>
            <c:url var="errorPage404" value="/error/404"/>
            <c:url var="errorPage500" value="/error/500"/>
            <c:url var="errorPage501" value="/error/501"/>
            <c:url var="errorPage502" value="/error/502"/>
            <c:url var="errorPage503" value="/error/503"/>
            <c:url var="errorPage504" value="/error/504"/>
            <c:url var="errorPage505" value="/error/505"/>
            <a class="btn btn-outline-danger" href="${errorPage400}">400번</a>
            <a class="btn btn-outline-danger" href="${errorPage404}">404번</a>
            <a class="btn btn-outline-danger" href="${errorPage500}">500번</a>
            <a class="btn btn-outline-danger" href="${errorPage501}">501번</a>
            <a class="btn btn-outline-danger" href="${errorPage502}">502번</a>
            <a class="btn btn-outline-danger" href="${errorPage503}">503번</a>
            <a class="btn btn-outline-danger" href="${errorPage504}">504번</a>
            <a class="btn btn-outline-danger" href="${errorPage505}">505번</a>
        </div>
    </div>
</main>
<!-- Footer-->
<%@ include file="../fragments/footer.jspf" %>
<script
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>
<!--axios없는 jsp에는 axios도 넣어주세용 -->
<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>

<c:url var="navcartJs" value="/js/navcart-count.js" />
<c:url var="cartListJs" value="/js/cart-list.js" />

<script src="${navcartJs}"></script>
<script src="${cartListJs}"></script>
</body>
</html>