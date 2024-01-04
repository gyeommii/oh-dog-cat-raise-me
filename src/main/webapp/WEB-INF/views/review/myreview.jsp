<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내가 작성한 리뷰</title>
<link
    href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
    rel="stylesheet"
    integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
    crossorigin="anonymous">
<link
    href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css"
    rel="stylesheet" />
<link
    href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200"
    rel="stylesheet" />
<link href="../css/font.css" rel="stylesheet">
<link href="../css/nav.css" rel="stylesheet">
<link href="../css/mypage.css" rel="stylesheet">

</head>
<body>


    <!--top nav -->
    <%@ include file="../fragments/top-nav.jspf"%>
    <!-- Header-->
    <%@ include file="../fragments/header.jspf"%>
    <!-- bottom nav-->
    <%@ include file="../fragments/bottom-nav.jspf"%>

    <div class="outer-container row">
        <div class="row">
            <div class="col-2">
                <%@include file="../fragments/MyPageNav.jsp"%>
            </div>
            <div class="col-9">
                <div class="container mt-5">
                    <h3 class="text-center mb-4">내가 작성한 리뷰 목록</h3>
                </div>
                <c:choose>
                    <c:when test="${empty myReview}">
                        <div class="container card my-5 border-0"
                            id="emptyCart"
                            style="border-radius: 24px; box-shadow: 0px 0px 10px 0px rgb(230, 230, 230);">
                            <div class="card-body py-5">
                                <div class="row fw-semibold text-center"
                                    style="font-size: 20px;">
                                    <div class="col my-5">
                                        <p style="font-size: 50px">📝</p>
                                        작성된 리뷰가 없습니다.
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <table
                            class="table table-hover text-center card-body">
                            <thead>
                                <tr>
                                    <th class="text-nowrap">상품이름</th>
                                    <th class="text-nowrap">옵션이름</th>
                                    <th class="text-nowrap">펫이름</th>
                                    <th class="text-nowrap">냥멍</th>
                                    <th class="text-nowrap">내용</th>
                                    <th class="text-nowrap">이미지</th>
                                    <th class="text-nowrap">평점</th>
                                    <th class="text-nowrap">수정시간</th>
                                    <th class="text-nowrap">삭제</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:set var="itemsPerPage" value="3" />
                                <c:set var="currentPage"
                                    value="${param.pageNumber == null ? 1 : param.pageNumber}" />
                                <c:set var="startIndex"
                                    value="${(currentPage - 1) * itemsPerPage}" />
                                <c:set var="endIndex"
                                    value="${startIndex + itemsPerPage - 1}" />
                                <!-- 페이지당 항목 표시 -->
                                <c:forEach var="myReview"
                                    items="${myReview}"
                                    begin="${(currentPage - 1) * itemsPerPage}"
                                    end="${currentPage * itemsPerPage - 1}">
                                    <tr>
                                        <td class="text-nowrap">${myReview.product_name}</td>
                                        <td class="text-nowrap">${myReview.option_name}</td>
                                        <td class="text-nowrap">${myReview.pet_name}</td>
                                        <td class="text-nowrap">${myReview.pet_type}</td>
                                        <td>${myReview.content}</td>
                                        <td
                                            style="height: 200px; width: 200px"><c:url
                                                var="imgGetUrl"
                                                value="/image">
                                                <c:param name="imgUrl"
                                                    value="${myReview.img}" />
                                            </c:url> <c:choose>
                                                <c:when
                                                    test="${empty myReview.img}">
                                                    <img
                                                        class="img-fluid card-img"
                                                        src="../images/review_default_img.png"
                                                        style="height: 200px; width: 200px"
                                                        alt="이미지 X">
                                                </c:when>
                                                <c:otherwise>
                                                    <img
                                                        class="img-fluid card-img"
                                                        src="${imgGetUrl}"
                                                        style="height: 200px; width: 200px"
                                                        alt="리뷰 이미지">
                                                </c:otherwise>
                                            </c:choose></td>
                                        <td>${myReview.score}</td>
                                        <td>${myReview.modified_time}</td>
                                        <td><c:url
                                                var="deleteReview"
                                                value="/review/delete">
                                                <c:param
                                                    name="review_pk"
                                                    value="${myReview.review_pk}" />
                                            </c:url> <a
                                            class="btn btn-outline-danger"
                                            onclick="return confirm('삭제하시겠습니까?')"
                                            id="deleteReview"
                                            role="button"
                                            href="${deleteReview}">❌</a></td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </c:otherwise>
                </c:choose>
                <c:if test="${not empty myReview}">
                    <nav aria-label="Page navigation">
                        <ul class="pagination justify-content-center">
                            <c:set var="totalItems"
                                value="${myReview.size()}" />
                            <c:set var="totalPages"
                                value="${(totalItems + itemsPerPage - 1) / itemsPerPage}" />

                            <!-- 이전 페이지로 이동 -->
                            <li
                                class="page-item <c:if test="${currentPage eq 1}">disabled</c:if>">
                                <a class="page-link"
                                href="?pageNumber=${currentPage - 1}"
                                tabindex="-1" aria-disabled="true">Previous</a>
                            </li>

                            <!-- 페이지 링크 -->
                            <c:forEach var="i" begin="1"
                                end="${totalPages}">
                                <li
                                    class="page-item <c:if test="${currentPage eq i}">active</c:if>">
                                    <a class="page-link"
                                    href="?pageNumber=${i}">${i}</a>
                                </li>
                            </c:forEach>

                            <!-- 다음 페이지로 이동 -->
                            <li
                                class="page-item <c:if test="${currentPage  eq totalPages}">disabled</c:if>">
                                <c:choose>
                                    <c:when test="${currentPage eq 1}">
                                        <span class="page-link">Next</span>
                                    </c:when>
                                    <c:otherwise>
                                        <a class="page-link"
                                            href="?pageNumber=${currentPage + 1}">Next</a>
                                    </c:otherwise>
                                </c:choose>
                            </li>
                        </ul>
                    </nav>
                </c:if>
            </div>
        </div>
    </div>
    <!-- Footer-->
    <%@ include file="../fragments/footer.jspf"%>


    <script
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>
    <!--axios없는 jsp에는 axios도 넣어주세용 -->
    <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
    <script src="../js/navcart-count.js"></script>
    <script src="../js/cart-list.js"></script>
    <script type="text/javascript">
        document.addEventListener('DOMContentLoaded', () => {
            const urlParams = new URLSearchParams(window.location.search);
            const isDuplicated = urlParams.get('duplicated');
        
            if (isDuplicated === 'true') {
                alert('이미 상품에 작성한 리뷰가 있습니다.');
            }
            
        });
    </script>

</body>
</html>