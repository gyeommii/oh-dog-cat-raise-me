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

        <!-- 정렬 옵션 버튼 -->
        <div class="text-center mt-3">
            <button id="sortNewest" class="btn btn-primary">신상품순</button>
            <button id="sortBestSelling" class="btn btn-secondary">판매많은 순</button>
            <button id="sortPriceLowToHigh" class="btn btn-success">낮은가격순</button>
            <button id="sortPriceHighToLow" class="btn btn-info">높은가격순</button>
            <button id="sortTopRated" class="btn btn-warning">리뷰평점순</button>
        </div>




        <!-- 상품 컨테이너 -->
    <div class="container">
        <div class="row">
            <div class="col-12 text-center my-5">
                <h3 id="newTitle">🐶멍 상품</h3>
            </div>
        </div>
        <div id="dogNewList" class="row">
            <!--신상품: petType이 강아지인 경우 -->
            <c:forEach var="dn" items="${dogNew}">
                <div class="col-3">
                    <c:url var="productDetailsPage" value="/product/details">
                        <c:param name="productPk" value="${dn.productPk}" />
                    </c:url>
                    <div class="card mb-5"
                        onclick="location.href= '${productDetailsPage}'"
                        style="width: 17rem; cursor: pointer;">
                        <img src="${dn.imgUrl}" class="card-img-top" alt="Product image">
                        <div class="card-body">
                            <h5 class="card-title">${dn.productName}</h5>
                            <p class="card-text">${dn.minPrice}</p>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
        <!-- product-list.js에서 처리. (고양이 버튼 선택 시 보여줄 신상품) -->
        <div id="catNewList" class="row d-none">
            <!--신상품: petType이 고양이인 경우 -->
            <c:forEach var="cn" items="${catNew}">
                <div class="col-3">
                    <c:url var="productDetailsPage" value="/product/details">
                        <c:param name="productPk" value="${cn.productPk}" />
                    </c:url>
                    <div class="card mb-5"
                        onclick="location.href= '${productDetailsPage}'"
                        style="width: 17rem; cursor: pointer;">
                        <img src="${cn.imgUrl}" class="card-img-top" alt="Product image">
                        <div class="card-body">
                            <h5 class="card-title">${cn.productName}</h5>
                            <p class="card-text">${cn.minPrice}</p>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
    <!-- end 상품 컨테이너 -->
    

    


    </main>
            
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
                crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
    <script>
            document.getElementById('sortNewest').addEventListener('click', function() {
                sortProducts('newest');
            });
            document.getElementById('sortBestSelling').addEventListener('click', function() {
                sortProducts('bestSelling');
            });
            document.getElementById('sortPriceLowToHigh').addEventListener('click', function() {
                sortProducts('priceLowToHigh');
            });
            document.getElementById('sortPriceHighToLow').addEventListener('click', function() {
                sortProducts('priceHighToLow');
            });
            document.getElementById('sortTopRated').addEventListener('click', function() {
                sortProducts('topRated');
            });
        
            function sortProducts(sortBy) {
                const petType = btnDog.checked ? 1 : 2;
                axios.get('/product/collection/best', {
                    params: {
                        petType: petType,
                        orderBy: sortBy
                    }
                })
                .then(function (response) {
                    updateProductList(response.data);
                })
                .catch(function (error) {
                    console.error('정렬된 제품을 가져오는 중에 오류가 발생했습니다.', error);
                });
            }
        
            function updateProductList(products) {
                var listToUpdate = btnDog.checked ? 'dogNewList' : 'catNewList';
                var container = document.getElementById(listToUpdate);
                container.innerHTML = ''; // 기존 내용 초기화

                products.forEach(product => {
                    var productDetailsPage = `/product/details?productPk=${products.productPk}`;
                    var card = document.createElement('div');
                    card.className = 'col-3';
                    card.innerHTML = `
                        <div class="card mb-5" onclick="location.href='${productDetailsPage}'" style="width: 17rem; cursor: pointer;">
                            <img src="${products.imgUrl}" class="card-img-top" alt="${products.productName}">
                            <div class="card-body">
                                <h5 class="card-title">${products.productName}</h5>
                                <p class="card-text">${products.minPrice}</p>
                            </div>
                        </div>
                    `;
                    container.appendChild(card); // 카드를 컨테이너에 추가
                });
            }

    </script>

    <script src="js/product-list.js"></script>
                    
    <!-- Footer-->
    <footer class="py-5 bg-dark">
        <div class="container">
            <p class="m-0 text-center text-white">Copyright &copy; ohdogcat 2023</p>
        </div>
    </footer>
</body>

</html>