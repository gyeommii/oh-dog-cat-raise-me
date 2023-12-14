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
  	<header class="bg-dark py-5">
	    <div class="container px-4 px-lg-5 my-5">
	        <div class="text-center text-white">
	            <h1 class="display-4 fw-bolder">🐶🐱<br>키워줄개냥?</h1>
	            <p class="lead fw-normal text-white-50 mb-0">oh dog cat raise me </p>
	        </div>
	    </div>
  	</header>
  	
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
				<h3 id="newTitle">🐶멍 신상품 ></h3>
			</div>
		</div>
		<div id="dogNewList" class="row">
			<!--신상품: petType이 강아지인 경우 -->
			<c:forEach var="n" items="${newList}">
				<c:if test="${n.petType eq 1}">
					<div class="col-3">
						<div class="card mb-5" style="width: 17rem;">
							<div class="badge bg-warning text-white position-absolute"
								style="top: 0.5rem; right: 0.5rem">New</div>
							<img src="${n.imgUrl}" class="card-img-top" alt="Product image">
							<div class="card-body">
								<h5 class="card-title">${n.productName}</h5>
								<p class="card-text">${n.minPrice}</p>
							</div>
						</div>
					</div>
				</c:if>
			</c:forEach>
		</div>
		<!-- product-list.js에서 처리. (고양이 버튼 선택 시 보여줄 신상품) -->
		<div id="catNewList" class="row d-none">
		</div>	
	</div> <!-- end 신상품 컨테이너 -->
	
	


 	<!-- 베스트 컨테이너-->
	<div class="container">
		<div id="dogBest" class="row">
			<div class="col-12 text-center my-5">
				<h3 id="bestTitle">🐶멍 베스트 ></h3>
			</div>
		</div>
		
		<div class="row">
			<!--베스트 상품: petType이 강아지인 경우 -->
			<c:forEach var="b" items="${bestList}">
				<c:if test="${b.petType eq 1}">
					<div class="col-3">
						<div class="card mb-5" style="width: 17rem;">
							<div class="badge bg-warning text-white position-absolute"
								style="top: 0.5rem; right: 0.5rem">Best</div>
							<img src="${b.imgUrl}" class="card-img-top" alt="Product image">
							<div class="card-body">
								<h5 class="card-title">${b.productName}</h5>
								<p class="card-text">${b.minPrice}</p>
							</div>
						</div>
					</div>
				</c:if>
			</c:forEach>
		</div>
	</div> <!-- end 베스트 컨테이너 -->


	</main>
			
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
	    		integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
				crossorigin="anonymous"></script>
	<script src="js/product-list.js"></script>
					
    <!-- Footer-->
  	<footer class="py-5 bg-dark">
       <div class="container"><p class="m-0 text-center text-white">Copyright &copy; ohdogcat 2023</p></div>
    </footer>
	</body>
</html>