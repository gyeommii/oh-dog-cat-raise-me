<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" 
			  rel="stylesheet" 
			  integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" 
			  crossorigin="anonymous">
	</head>
	<body>
        <!-- 전체상품 컨테이너 -->
    <div class="container">
        <div class="row">
            <div class="col-12 text-center my-5">
                <h3 id="newTitle">🐶멍 전체상품</h3>
            </div>
        </div>
        <!-- 상품 총수량 & 정렬  -->
            <div>
                <div>
                    <div>
                        <ul class="horizontal-list">
                            <li id="totalProductCount">총건</li>
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
            <div id="dogNewList" class="row">
            <!--상품: petType이 강아지인 경우 -->
            <c:forEach var="dn" items="${dogNew}">
                <div class="col-3">
                    <c:url var="productDetailsPage" value="/product/details">
                        <c:param name="productPk" value="${dn.productPk}" />
                    </c:url>
                    <div class="card mb-5"
                        onclick="location.href= '${productDetailsPage}'"
                        style="width: 17rem; cursor: pointer;">
                        <div class="badge bg-warning text-white position-absolute"
                            style="top: 0.5rem; right: 0.5rem">New</div>
                        <img src="${dn.imgUrl}" class="card-img-top" alt="Product image">
                        <div class="card-body">
                            <h5 class="card-title">${dn.productName}</h5>
                            <p class="card-text">${dn.minPrice}</p>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
        <!-- product-listall.js에서 처리. (고양이 버튼 선택 시 보여줄 상품) -->
        <div id="catNewList" class="row d-none">
            <!--상품: petType이 고양이인 경우 -->
            <c:forEach var="cn" items="${catNew}">
                <div class="col-3">
                    <c:url var="productDetailsPage" value="/product/details">
                        <c:param name="productPk" value="${cn.productPk}" />
                    </c:url>
                    <div class="card mb-5"
                        onclick="location.href= '${productDetailsPage}'"
                        style="width: 17rem; cursor: pointer;">
                        <div class="badge bg-warning text-white position-absolute"
                            style="top: 0.5rem; right: 0.5rem">New</div>
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
    <!-- end 전체상품 컨테이너 -->
		
		
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" 
				integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" 
				crossorigin="anonymous"></script>
	</body>
</html>