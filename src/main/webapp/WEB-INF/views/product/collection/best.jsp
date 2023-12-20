<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ohdogcat</title>
<link
    href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
    rel="stylesheet"
    integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
    crossorigin="anonymous">
</head>
<body>

    <!-- Header -->
    <%@ include file="/WEB-INF/views/fragments/header.jspf"%>

    <main>
        <!-- 멍멍이/야옹이 버튼 -->
        <div class="text-center mt-5">
            <!-- 버튼 그룹 -->
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



        <!-- 상품 컨테이너 -->
        <div class="container">
            <!-- 타이틀 -->
            <div class="row">
                <div class="col-12 text-center my-5">
                    <h3 id="newTitle">🐶멍 베스트</h3>
                </div>
            </div>
            <!-- 정렬 옵션 버튼 -->
            <div
                class="text-center mt-3 d-md-flex justify-content-md-end">
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

    <!-- Footer -->
    <footer class="py-5 bg-dark">
        <div class="container">
            <!-- 푸터 내용 -->
        </div>
    </footer>

    <!-- JavaScript -->
    <script
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
    <script>

// 고양이 버튼 클릭 이벤트 리스너
    document.querySelector("input#btnradio2").addEventListener("click", function() {
        let petType = 2; // 고양이
        let orderBy = 'sold';
        
        const uri = `/ohdogcat/aaa/bbb/best${petType}${orderBy}`;                        
    
        axios.get(uri, {
            params: {
                petType: petType,
                orderBy: orderBy
            }
        })
        .then(function (response) {
            showProductsCards(response.data); // 상품목록 업데이트
            console.log(response.data);
            
        })
        .catch(function (error) {
            console.error('Error:', error);
            alert('제품 목록을 로드하는 데 실패했습니다.');
        });
    }); 
 // 상품 목록
    function showProductsCards(products) {
    var productsContainer = document.getElementById('productsContainer');
    productsContainer.innerHTML = ''; // 초기화

    // products가 배열인지 확인
    if (!Array.isArray(products)) {
        console.error('products는 배열이 아님:', products);
        return; // 배열 아니면 종료
    }

        products.forEach(function (product) {
            var cardHtml = `
                <div class="col-3">
                <div class="card mb-5">
                    <img class="card-img-top" src="${product.imgUrl}" alt="Productimage">
                    <div class="card-body">
                        <h5 class="card-title">${product.productName}</h5>
                        <p class="card-text">${product.minPrice}</p>
                    </div>
                </div>
            </div>
            `;
            productsContainer.innerHTML += cardHtml;
        });
    } //--end showProductsCards --
</script>


</body>
</html>
