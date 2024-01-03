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
<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
<link href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" rel="stylesheet" />
<link href="../css/font.css" rel="stylesheet" >
<link href="../css/nav.css" rel="stylesheet" >

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

{
  margin: 0;
  padding: 10px;
  box-sizing: border-box;
}

li {
  list-style-type: none;
}

.post-container {
  display: flex;
  align-items: center;
  border-radius: 10px;
  border: 2px solid #d7d7d7;
  margin: 15px;
}

.post-title {
  font-size: 20px;
}

.post-number {
  font-size: 15px;
  padding: 15px;
}

.pagination-container {
  display: flex;
  justify-content: center;
}

/* 추가적인 CSS 스타일링 */
.sidebar-sticky {
    border: 1px solid;
    border-color: #D3D3D3;
    background-color: ; /* 배경색 변경 */
    border-radius: 5px; /* 모서리 둥글게 */
    padding: 10px; /* 내부 패딩 */
    
}

.nav-item {
    margin-bottom: 10px; /* 아이템 간 마진 */
}

/* 레이블 및 텍스트 스타일링 */
label {
    display: block; /* 블록 레벨 요소로 만들기 */
    color: #495057; /* 글꼴 색상 변경 */
    margin-bottom: 5px; /* 아래쪽 마진 추가 */
}

input[type='text'] {
    width: 100%; /* 가로 길이 100% */
}

/* 검색 버튼 스타일링 */
#submitFilter {
    width: 100%; /* 가로 길이 100% */
}

/* 스위치 커스텀 스타일 */
.custom-switch:checked {
    background-color: #FFA500; /* 변경할 색상 */
    border-color: #FFA500; /* 변경할 색상 */
}

/* 검색 버튼 커스텀 스타일 */
.btn-custom {
    background-color: #FFA500; /* 버튼 배경 색상 */
    color: white; /* 버튼 텍스트 색상 */
}
/* 검색 버튼 클릭 시 스타일 */
.btn-custom:hover {
    background-color: #FFB914; /* 클릭 시 배경 색상 변경 */
    color: white; /* 클릭 시 텍스트 색상*/
}

/* 선택된 상태의 스위치 핸들 색상 */
.custom-switch:checked + .form-check-label::before {
    background-color: white; /* 핸들 색상 */
    border-color: white; /* 핸들 테두리 색상 */
}

/* 추가적인 CSS 스타일링 */
.pagination-container {
    display: flex;
    justify-content: center;
    margin-top: 20px;
    color: white;
}

.pagination-container .btn {
    margin: 0 5px; /* 버튼 간의 여백 */
    background-color: #FFA500; /* 변경할 색상 */
    border-color: #FFA500; /* 변경할 색상 */
    color: white;
    
}






</style>   
    
</head>
<body>

    <!--top nav -->
    <%@ include file="../fragments/top-nav.jspf"%>
    <!-- Header-->
    <%@ include file ="../fragments/header.jspf" %>
    <!-- bottom nav-->
    <%@ include file="../fragments/bottom-nav.jspf"%>

    <div class="row">
        <div class="col-12 text-center my-5">
            <h3 id="newTitle">🐶멍 전체상품</h3>
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
                            <!-- 상품 키워드 검색 -->
                            <form action="/search-url" method="get" class="mb-3">
                                <input type="text" title="상품 검색" placeholder="상품 키워드" id="keyword" class="form-control mb-2"> 
                            </form> 
                            
                            <!-- 가격 조건 검색 -->
                            <div class="mb-3">
                                <span>가격</span>
                                <input type="text" title="최소가격" placeholder="0" id="minPrice" class="form-control mt-1 mb-1">
                                <span>⠀~</span>
                                <input type="text" title="최대가격" placeholder="9,990,000" id="maxPrice" class="form-control mt-1 mb-1">
                            </div>
                            
                            <!-- 품절제외 스위치(체크박스) -->
                            <div class="form-check form-switch mb-3">
                                <input class="form-check-input custom-switch" type="checkbox" role="switch" id="soldOutChecked" checked>
                                <label class="form-check-label" for="soldOutChecked">품절상품 제외</label>
                            </div>
                            <div>
                                <!-- 필터 검색 버튼 -->
                                <input type="submit" value="검색" id="submitFilter" class="btn btn-custom">
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
                            <button type="button" id="sold" class="btn">판매량순</button>
                            <button type="button" id="lowest" class="btn">낮은가격순</button>
                            <button type="button" id="highest" class="btn">높은가격순</button>
                            <button type="button" id="topRated" class="btn">리뷰평점순</button>
                        </div>
                    </div>

                    <!-- 상품 목록 -->
                    <div id="productsContainer" class="row">
                        <c:forEach var="product" items="${products}">
                            <div class="col-3 product-card" data-product-pk="${product.productPk}">
                                <div class="card mb-5">
                                    <img class="card-img-top" src="${product.imgUrl}" alt="Product image">
                                    <div class="card-body">
                                        <h5 class="card-title">${product.productName}</h5>
                                        <p class="card-text">${product.minPrice}</p>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>

                
                <div class="pagination-container">
                    <button class="prev-button">◀</button>
                        <div class="number-button-wrapper">
                            <button class="page-number-button" id="pageNumberBtn">1</button>
                        </div>
                    <button class="next-button">▶</button>
                </div>
            </main>

        </div>
    </div>

    <script
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
    <script src="../js/product-sorted.js"></script>
    <script>let totalPages = ${totalPages};</script>
    
    <script src="../js/navcart-count.js"></script>
    
    <script src="../js/cart-list.js"></script>
    
    <!-- Footer-->
    <%@ include file="../fragments/footer.jspf"%>
    
    
</body>
</html>
