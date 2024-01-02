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
    	 <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
	     <link href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" rel="stylesheet" />
    	 <link href="css/font.css" rel="stylesheet" >
    	 <link href="css/nav.css" rel="stylesheet" >
	</head>
	<body>
  	<!--top nav -->
	<%@ include file="fragments/top-nav.jspf"%>
	<!-- Header-->
  	<%@ include file ="fragments/header.jspf" %>
    <!-- bottom nav-->
   	<%@ include file="fragments/bottom-nav.jspf"%>

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
	

	<!-- 신상품 컨테이너 -->
	<div class="container">
		<div class="row">
			<div class="col-12 text-center my-5">
				<h3 id="newTitle">🐶멍 신상품</h3>
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
	<!-- end 신상품 컨테이너 -->


	<!-- 베스트 컨테이너-->
	<div class="container">
		<div id="dogBest" class="row">
			<div class="col-12 text-center my-5">
				<h3 id="bestTitle">🐶멍 베스트</h3>
			</div>
		</div>

		<div id="dogBestList" class="row">
			<!--베스트 상품: petType이 강아지인 경우 -->
			<c:forEach var="db" items="${dogBest}">
				<div class="col-3">
					<c:url var="productDetailsPage" value="/product/details">
						<c:param name="productPk" value="${db.productPk}" />
					</c:url>
					<div class="card mb-5"
						onclick="location.href= '${productDetailsPage}'"
						style="width: 17rem; cursor: pointer;">
						<div class="badge bg-warning text-white position-absolute"
							style="top: 0.5rem; right: 0.5rem">Best</div>
						<img src="${db.imgUrl}" class="card-img-top" alt="Product image">
						<div class="card-body">
							<h5 class="card-title">${db.productName}</h5>
							<p class="card-text">${db.minPrice}</p>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>

		<!-- product-list.js에서 처리. (고양이 버튼 선택 시 보여줄 베스트상품) -->
		<div id="catBestList" class="row d-none">
			<!--베스트 상품: petType이 고양이인 경우 -->
			<c:forEach var="cb" items="${catBest}">
				<div class="col-3">
					<c:url var="productDetailsPage" value="/product/details">
						<c:param name="productPk" value="${cb.productPk}" />
					</c:url>
					<div class="card mb-5"
						onclick="location.href= '${productDetailsPage}'"
						style="width: 17rem; cursor: pointer;">
						<div class="badge bg-warning text-white position-absolute"
							style="top: 0.5rem; right: 0.5rem">Best</div>
						<img src="${cb.imgUrl}" class="card-img-top" alt="Product image">
						<div class="card-body">
							<h5 class="card-title">${cb.productName}</h5>
							<p class="card-text">${cb.minPrice}</p>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
	<!-- end 베스트 컨테이너 -->


	</main>
			
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
	    		integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
				crossorigin="anonymous"></script>
	<script src="js/product-list.js"></script>
					
    <!-- Footer-->
	<%@ include file="fragments/footer.jspf"%>

</body>
</html>