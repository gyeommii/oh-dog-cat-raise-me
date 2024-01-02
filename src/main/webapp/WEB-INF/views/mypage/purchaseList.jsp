<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>

<html>
<head>
    <meta charset="UTF-8">
    <title>ohdogcat - mypage</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
          crossorigin="anonymous">
    <link href="../css/order/order-page.css" rel="stylesheet"/>

</head>

<body>

<%@ include file="../fragments/header.jspf" %>
<main>
    <div class="pt-3 mt-3" style="width: 70%; margin: auto">
        <!-- End Page Title -->
        <section class="section">
            <h4 class="display-6">구매 이력보기</h4>
            <div class="card-body pb-3">
                <!-- Table with stripped rows -->
                <p class="lead pb-2">주인놈 떡에 너무 행복한 삶이다냥~</p>
                <table class="table datatable py-3">
                    <thead>
                    <tr>
                        <th><b>주문명</b></th>
                        <th>주문번호</th>
                        <th>결제방법</th>
                        <th>주문상태</th>
                        <th data-type="date" data-format="YYYY/DD/MM">주문날짜</th>
                        <th>주문취소</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="purchase" items="${purchaseList}">
                        <tr class="purchase-list-item" data-id="${purchase.purchase_pk}">
                            <td>
                                <a
                                        class="link-offset-2 link-offset-3-hover link-underline link-underline-opacity-0 link-underline-opacity-75-hover text-dark"
                                        href="../order/${purchase.purchase_pk}"
                                >
                                    <img
                                            src="${purchase.img_url}"
                                            alt=""
                                            class="mx-3"
                                            width="50px"
                                            height="50px"
                                            style="border: 1px solid gray"
                                    />
                                        ${purchase.order_name}</a
                                >
                            </td>
                            <td class="align-middle">${purchase.purchase_pk}</td>
                            <td class="align-middle">${purchase.pay_method}</td>
                            <td class="align-middle">
                                <c:if test="${purchase.status_pk eq 1 or purchase.status_pk eq 2 or purchase.status_pk eq 3}">
                                    <span class="badge bg-warning fs-6">${purchase.purchase_status}</span>
                                </c:if>
                                <c:if test="${purchase.status_pk eq 4 or purchase.status_pk eq 5}">
                                    <span class="badge bg-primary fs-6">${purchase.purchase_status}</span>
                                </c:if>
                                <c:if test="${purchase.status_pk eq 6}">
                                    <span class="badge bg-success fs-6">${purchase.purchase_status}</span>
                                </c:if>
                                <c:if test="${purchase.status_pk eq 7 or purchase.status_pk eq 8}">
                                    <span class="badge bg-danger fs-6">${purchase.purchase_status}</span>
                                </c:if>
                            </td>
                            <td class="align-middle"
                            >${purchase.purchase_date.year}/${purchase.purchase_date.monthValue}/${purchase.purchase_date.dayOfMonth}</td>
                            <td class="align-middle">
                                <button id="delete-btn" class="btn btn-outline-warning">주문 취소
                                </button>
                            </td>
                        </tr>
                    </c:forEach>

                    <%--                    --%>
                    </tbody>
                </table>
                <!-- End Table with stripped rows -->
            </div>
        </section>
        <nav aria-label="Page navigation my-3"  style="margin-bottom: 50px">
            <ul class="pagination justify-content-center">

                <c:url var="listPageUrlByPaginationPrev" value="./purchaseList">
                    <c:param name="curPage" value="${curPage-1}"/>
                </c:url>
                <c:url var="listPageUrlByPaginationNext" value="./purchaseList">
                    <c:param name="curPage" value="${curPage+1}"/>
                </c:url>
                <c:choose>
                <c:when test="${curPage eq 1}">
                <li class="page-item" id="paginationPrev"><a class="page-link"
                                                             href="#none"
                >
                    </c:when>
                    <c:otherwise>
                    <li class="page-item" id="paginationPrev"><a class="page-link"
                                                                 href="${listPageUrlByPaginationPrev}">
                        </c:otherwise>
                        </c:choose>
                        Previous</a></li>
                    <c:forEach begin="1" end="${page}" var="pageCount" step="1">
                    <c:url var="listPageUrlByPagination" value="./purchaseList">
                        <c:param name="curPage" value="${pageCount}"/>
                    </c:url>

                    <c:if test="${pageCount eq curPage}">
                    <li class="page-item active" aria-current="page"><a class="page-link"
                                                                        href="${listPageUrlByPagination}">${pageCount}</a>
                    </li>
                    </c:if>
                    <c:if test="${pageCount ne curPage}">
                    <li class="page-item"><a class="page-link"
                                             href="${listPageUrlByPagination}">${pageCount}</a></li>
                    </c:if>
                    </c:forEach>
                    <c:choose>
                    <c:when test="${curPage eq page}">
                    <li class="page-item" id="paginationNext"><a class="page-link"
                                                                 href="#none">Next</a>
                    </li>
                    </c:when>
                    <c:otherwise>
                    <li class="page-item" id="paginationNext"><a class="page-link"
                                                                 href="${listPageUrlByPaginationNext}">Next</a>
                    </li>
                    </c:otherwise>
                    </c:choose>
            </ul>
        </nav>
    </div>
</main>


<%@ include file="../fragments/footer.jspf" %>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>

<script src="../js/order/orderList.js"></script>

</body>
</html>
