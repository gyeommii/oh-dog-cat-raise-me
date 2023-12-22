<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
	<!-- Header-->
	<%@ include file="../fragments/header.jspf"%>

	<main class="mt-3">
		<div class="container">
			<div class="row py-5">

				<!-- 상품 사진 영역 -->
				<div class="col-md-6">
					<img src="${p.imgUrl}" class="img-fluid d-block w-100"
						alt="Product details">
				</div>

				<!-- 상품 정보 영역 -->
				<div class="col-md-6">
					<div class="card shadow-sm">

						<!-- 멍멍/냐옹 뱃지 -->
						<h5 class="card-header pt-3"
							style="background: none; border-bottom: none;">
							<div class="badge bg-warning text-white "
								style="top: 0.5rem; right: 0.5rem;">
								<c:if test="${p.petType == 1 }"> 멍멍이 </c:if>
								<c:if test="${p.petType == 2 }"> 야옹이 </c:if>
							</div>
						</h5>

						<!-- 상품 정보 -->
						<div class="card-body">
							<input class="d-none" id="productPk" value="${p.productPk}" />
							<p class="card-title fw-semibold pb-2 fs-3">${p.productName}</p>
							<p
								class="card-text border-bottom pb-3 text-danger fw-semibold fs-3">
								<f:formatNumber value="${p.minPrice}" pattern="#,###" />
								원
							</p>
							<p class="card-text fw-semibold fs-5">배송정보</p>
							<p class="card-text border-bottom pb-3 text-black-50 ">무료배송 │
								평균 3일 이내 배송 ( 주말 / 공휴일 제외 )</p>

							<!-- 옵션 리스트 -->
							<div class="dropdown pb-3">
								<button class="btn btn-outline text-black dropdown-toggle w-100" id="btnOption"
									style="border-color: lightgray; background: none;"
									type="button" data-bs-toggle="dropdown" aria-expanded="false">
									상품 옵션을 선택하세요</button>
								<!--상품별 옵션 리스트 추가 될 영역 -->
								<ul class="dropdown-menu w-100" id="optionList">
								</ul>
							</div>

							<!--옵션 선택 시 추가 될 영역-->
							<!-- 옵션명 / 가격 / 수량조절 버튼 있어야함 -->
							<div class="card" style="border: 0;" id="optionAddArea">
							</div>

							<!-- 총 금액 -->
							<div class="card-text my-4" id="prd_total_price">
								<input type="hidden" id="totalCnt" value="0" name="totalCnt">
								<input type="hidden" id="totalPrc" value="0" name="totalPrc">
								<span class="card-text pb-3 pt-3 fs-5 text-danger fw-semibold mx-auto">
									총 상품금액 
									<span class="float-end fs-3" id="totalPrice"> <f:formatNumber value="0" pattern="#,###" />원</span>
								</span>
							</div>

							<!-- 버튼 선택 -->
							<div class="card-text border-top border-2 border-danger pt-4 row mx-auto w-100 input-group">
								<button
									class="col-md-2 btn btn-outline-warning btn-lg mb-2 me-md-2 text-warning"
									id="btnZzim" type="button" style="background: none; border-radius: 8px;">찜</button>
								<button
									class="col-md-3 btn btn-outline-warning btn-lg mb-2 me-md-2 text-warning"
									id="btnCart "type="button" style="background: none; border-radius: 8px;">장바구니</button>
								<button
									class="col-md-6 btn btn-warning btn-lg mb-2 text-white fw-semibold shadow-sm form-control"
									id="btnBuy" type="button" style="border-radius: 8px;">바로구매</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</main>

	<!-- Footer-->
	<footer class="py-5 bg-dark">
		<div class="container">
			<p class="m-0 text-center text-white">Copyright &copy; ohdogcat
				2023</p>
		</div>
	</footer>


	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
		crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
	<script src="../js/product-details.js"></script>
</body>
</html>