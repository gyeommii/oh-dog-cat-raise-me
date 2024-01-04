<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>ohdogcat</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
          crossorigin="anonymous">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css"
          rel="stylesheet"/>
    <link href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200"
          rel="stylesheet"/>
    <link href="../css/font.css" rel="stylesheet">
    <link href="../css/nav.css" rel="stylesheet">
    <link href="../css/mypage.css" rel="stylesheet">

</head>
<body>
<!--top nav -->
<%@ include file="../fragments/top-nav.jspf" %>
<!-- Header-->
<%@ include file="../fragments/header.jspf" %>
<!-- bottom nav-->
<%@ include file="../fragments/bottom-nav.jspf" %>

<main class="outer-container row">
    <div class="col-2">
        <%@include file="../fragments/MyPageNav.jsp" %>
    </div>

    <div class="container col-8">
        <section class="section">
        <div class="row">
            <h1 class="fs-1 fw-bold py-3 display-6 pt-4">❣️WISH LIST(${count})</h1>
            <c:forEach var="wish" items="${wishList}">
                <div class="col-md-3" data-id="${wish.product_fk}">
                    <c:url var="productDetailsPage" value="../product/details">
                        <c:param name="productPk" value="${wish.product_fk}"/>
                    </c:url>
                    <div class="card mb-5 item"
                         onclick="toPrductDetailPage(event,'${productDetailsPage}')"
                         style="width: 17rem; cursor: pointer;">
                        <img src="${wish.img_url}" class="card-img-top" alt="Product image">
                        <div class="card-body row">
                            <div class="col-10">
                                <h5 class="card-title fw-bold fs-6">${wish.product_name}</h5>
                                <p class="card-text fs-6">
                                    <f:formatNumber value="${wish.min_price}" pattern="#,###"/>원
                                </p>
                            </div>
                            <div class="col-2 m-0 p-1 text-center align-self-center">
                                <button class="bi bi-suit-heart-fill m-0 p-0 btnWish"
                                        id="btnWish_${wish.product_fk}" data-id="${wish.product_fk}"
                                        style="font-size: 24px;"></button>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
            </div>
        </section>
        <c:if test="${count==0}">
            <div class="container card mb-5 border-0" id="emptyCart"
                 style="border-radius: 24px; box-shadow: 0px 0px 10px 0px rgb(230, 230, 230);">
                <div class="card-body py-5">
                    <div class="row fw-semibold text-center" style="font-size: 20px;">
                        <div class="col my-5">
                            <p style="font-size: 50px;">❣️</p>
                            찜 상품이 없습니다.
                        </div>
                    </div>
                </div>
            </div>
        </c:if>
    </div>
</main>
<!-- Footer-->
<%@ include file="../fragments/footer.jspf" %>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
<script src="../js/navcart-count.js"></script>
<script src="../js/wishlist.js"></script>
<script>
  function toPrductDetailPage(event, detailPage) {
    if (!event.target.classList.contains("btnWish")) {
      location.href = detailPage;
    }
  }
</script>
</body>
</html>