<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>ohdogcat</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
          crossorigin="anonymous">
</head>
<body>
    
<!-- Header -->
<%@ include file="/WEB-INF/views/fragments/header.jspf" %>
    
<main>
    <!-- 멍멍이/야옹이 버튼 -->
    <div class="text-center mt-5">
        <!-- 버튼 그룹 -->
    </div>

    <!-- 정렬 옵션 버튼 -->
    <div class="text-center mt-3">
        <!-- 정렬 버튼 -->
    </div>

    <!-- 상품 컨테이너 -->
    <div class="container">
        <div class="row">
            <div class="col-12 text-center my-5">
                <h3 id="newTitle">🐶멍 상품</h3>
            </div>
        </div>
        <!-- 상품 목록 여기 -->
        <div id="productsContainer" class="row">
        
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
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
<script>
    function fetchBestProducts(petType, orderBy) {
    axios.get('/product/collection/best', {
        params: {
            petType: petType,
            orderBy: orderBy
        }
    })
    .then(function (response) {
        console.log(response.data);
        showProductsCards(response.data);
    })
    .catch(function (error) {
        console.error('제품 데이터를 가져오는 중 오류 발생:', error);
    });
}

    function showProductsCards(products) {
        var productsContainer = document.getElementById('productsContainer');
        productsContainer.innerHTML = ''; // 초기화
    
        products.forEach(function (product) {
            var cardHtml = `
                <div class="col-md-4 col-sm-6 col-xs-12">
                    <div class="card mb-4 box-shadow">
                        <img class="card-img-top" src="${product.imgUrl}" alt="Product image">
                        <div class="card-body">
                            <h5 class="card-title">${product.productName}</h5>
                            <p class="card-text">가격: ${product.minPrice}</p>
                            <div class="d-flex justify-content-between align-items-center">
                                <div class="btn-group">
                                    <button type="button" onclick="" class="btn btn-sm btn-outline-secondary"></button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            `;
            productsContainer.innerHTML += cardHtml;
        });
    }
    
    document.addEventListener('DOMContentLoaded', function() {
        fetchBestProducts(1, 'create_date'); 
    });


    
</script>

</body>
</html>
