<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>

<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        
            <title>ohdogcat</title>
            <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
                  rel="stylesheet"
                  integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
                  crossorigin="anonymous">

<style>

.horizontal-list { 
    list-style-type: none;
    padding: 0;
    margin: 0;
    display: flex;
    justify-content: flex-end;
}

.horizontal-list li {
    margin-left: 10px;
}
</style>

    </head>
    <body>
        
    <!-- Header-->
    <%@ include file ="/WEB-INF/views/fragments/header.jspf" %>
        
        <main>
        <!-- 멍멍이/야옹이 버튼 -->
        <div class="text-center mt-5">
            <div class="btn-group  btn-group-lg" role="group" aria-label="Basic radio toggle button group">
              <input type="radio" class="btn-check" name="btnradio" id="btnradio1" autocomplete="off" checked>
              <label class="btn btn-outline-warning" for="btnradio1">멍멍이</label>
            
              <input type="radio" class="btn-check" name="btnradio" id="btnradio2" autocomplete="off" >
              <label class="btn btn-outline-warning" for="btnradio2">야옹이</label>
            </div>
        </div>
        
            <div class="container mt-5">
                <div class="row">
                    <div class="col-12 text-center my-5">
                        <h3 id="newTitle">신상품</h3>
                    </div>
                </div>
                <!-- 상품 총수량 & 정렬  -->
                <div>
                    <div>
                        <div>
                            <ul class="horizontal-list">
                                <li id="totalProductCount">총0건</li>
                                <!-- 수정된 부분: ID를 추가함 -->
                                <li><a href="" id="new"
                                    class="sort-option">신상품순</a></li>
                                <li><a href="" id="best"
                                    class="sort-option">판매량순</a></li>
                                <li><a href="" id="lowest"
                                    class="sort-option">낮은 가격순</a></li>
                                <li><a href="" id="highest"
                                    class="sort-option">높은 가격순</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
                
                <!-- Product(상품) 목록 -->
                <div class="row" id="productContainer">
                    <c:forEach var="product" items="${products}">
                        <div class="col-3">
                            <div class="card mb-4">
                                <img src="${product.imgUrl}"
                                    class="card-img-top" alt="Product Image">
                                <div class="card-body">
                                    <h5 class="card-title">${product.productName}</h5>
                                    <p class="card-text">${product.minPrice}원</p>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </main>
    
        <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
        <script
            src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
            crossorigin="anonymous"></script>
    
        <script>
            document.addEventListener('DOMContentLoaded', function () {
                // 정렬 옵션 클릭 이벤트
                document.querySelectorAll('a.sort-option').forEach(element => {
                    element.addEventListener('click', function (e) {
                        e.preventDefault();
                        var sortType = this.id;
    
                        // Axios 요청
                        axios.get('/ohdogcat/product/collection/sort', {
                            params: {
                                sortType: sortType
                            }
                        })
                        .then(function (response) {
                            updateProductList(response.data.products); // 제품 목록 업데이트
                            updateProductCount(response.data.count); // 제품 수 업데이트
                        })
                        .catch(function (error) {
                            console.error('Error:', error);
                            alert('제품 목록을 로드하는 데 실패했습니다.');
                        });
                    });
                });
    
                // 제품 목록 업데이트 함수
                function updateProductList(products) {
                    var productContainer = document.getElementById('productContainer');
                    productContainer.innerHTML = '';
    
                    products.forEach(product => {
                        var cardHtml = '<div class="col-3">' +
                                           '<div class="card mb-4">' +
                                               '<img src="' + product.imgUrl + '" class="card-img-top" alt="Product Image">' +
                                               '<div class="card-body">' +
                                                   '<h5 class="card-title">' + product.productName + '</h5>' +
                                                   '<p class="card-text">' + product.minPrice + '원</p>' +
                                               '</div>' +
                                           '</div>' +
                                       '</div>';
                        productContainer.innerHTML += cardHtml;
                    });
                }
    
                // 제품 수 업데이트 함수
                function updateProductCount(count) {
                    var totalCountElement = document.getElementById('totalProductCount');
                    totalCountElement.innerText = '총 ' + count + '건';
                }
            });
        </script>
    </body>
    </html>
