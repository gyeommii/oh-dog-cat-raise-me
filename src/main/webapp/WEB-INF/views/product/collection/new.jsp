<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>🐾ohdogcat🐾</title>
<link
    href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
    rel="stylesheet"
    integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
    crossorigin="anonymous">

<style>
.label-large {
    font-size: 20px;
}

.checkbox-custom-size {
        width: 20px;
        height: 20px;
    }
.input-small {
        width: 70px;      /* 입력 필드의 너비 */
        height: 30px;      /* 입력 필드의 높이 */
        font-size: 14px;   /* 입력 필드 내부의 텍스트 크기 */
    }
.input-medium {
        width: 200px;      /* 입력 필드의 너비 */
        height: 30px;      /* 입력 필드의 높이 */
        font-size: 14px;   /* 입력 필드 내부의 텍스트 크기 */
    }
    
.submit-small {
    width: 200px;      /* 버튼의 너비 */
    height: 30px;     /* 버튼의 높이 */
    font-size: 12px;  /* 버튼 내부의 텍스트 크기 */
}

</style>   
    
</head>
<body>

    <!-- Header -->
    <%@ include file="/WEB-INF/views/fragments/header.jspf"%>

    <div class="row">
        <div class="col-12 text-center my-5">
            <h3 id="newTitle">🐶멍 신상품</h3>
        </div>
        <div class="text-center mt-3 mb-4">

            <!-- 멍멍이/야옹이 버튼 -->
            <div class="btn-group  btn-group-lg" role="group"
                aria-label="Basic radio toggle button group">
                <input type="radio" class="btn-check" name="btnradio"
                    id="btnradio1" autocomplete="off" checked> <label
                    class="btn btn-outline-warning" for="btnradio1">멍멍이</label>
                <input type="radio" class="btn-check" name="btnradio"
                    id="btnradio2" autocomplete="off"> <label
                    class="btn btn-outline-warning" for="btnradio2">야옹이</label>
            </div>
        </div>

    </div>

    <div class="container-fluid">
        <div class="row">
            <!-- 사이드바 필터 -->
            <nav class="col-md-2 d-none d-md-block sidebar pt-5">
                <div class="sidebar-sticky">
                    <ul class="nav flex-column">
                        <li class="nav-item">
                            <!-- 상품 키워드 검색 폼 -->
                            <form action="/search-url" method="get"><!-- 'action'은 폼데이터를 처리할 서버의 URL, 'method'는 데이터 전송 방식 -->
                                <label>
                                    <input type="text" title="상품 검색" placeholder="상품 키워드" id="keyword" class="input-medium"> 
                                    
                                </label>
                            </form> 
                            
                            <!-- 가격 조건 검색 -->
                            <label>
                                <span>가격</span>
                                <input type="text" title="최소가격" placeholder="0" id="minPrice" class="input-small">
                                <span>~</span>
                                <input type="text" title="최대가격" placeholder="9,990,000" id="maxPrice"class="input-small" >
                            </label>
                            
                            <!-- 품절제외 스위치(체크박스) -->
                            <div class="form-check form-switch">
                                <input class="form-check-input" type="checkbox" role="switch" id="soldOutChecked" checked>
                                <label class="form-check-label" for="soldOutChecked"> 품절상품 제외</label>
                            </div>
                            <div>
                                <!-- 필터 검색 버튼 -->
                                <input type="submit" value="검색" id="submitFilter" class="submit-small">
                            </div>
                        </li>

                    </ul>
                </div>
            </nav>

            <!-- 메인 -->
            <main role="main" class="col-md-9 ml-sm-auto col-lg-10">

                <!-- 상품 컨테이너 -->
                <div class="container">
                    <div class="text-center mt-3 d-md-flex justify-content-md-end">
                        <!-- 정렬 버튼 -->
                        <div class="btn-group" role="group"
                            aria-label="Basic example">
                            <button type="button" id="new" class="btn">신상품순</button>
                            <button type="button" id="best" class="btn">판매량순</button>
                            <button type="button" id="lowest" class="btn">낮은가격순</button>
                            <button type="button" id="highest" class="btn">높은가격순</button>
                            <button type="button" id="topRated" class="btn">리뷰평점순</button>
                        </div>
                    </div>

                    <!-- 상품 목록 -->
                    <div id="productsContainer" class="row">
                        <c:forEach var="product" items="${products}">
                            <div class="col-3">
                                <div class="card mb-5">
                                    <img class="card-img-top"
                                        src="${product.imgUrl}"
                                        alt="Product image">
                                    <div class="card-body">
                                        <h5 class="card-title">${product.productName}</h5>
                                        <p class="card-text">${product.minPrice}</p>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </main>

        </div>
    </div>

    <footer class="py-5 bg-dark">
        <div class="container"></div>
    </footer>

    <script
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
    <script src="../../js/product-sorted.js"></script>
</body>
</html>
