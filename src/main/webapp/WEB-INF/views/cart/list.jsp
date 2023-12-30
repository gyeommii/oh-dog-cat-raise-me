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
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css"
	rel="stylesheet" />

<style>
a {
	text-decoration: none;
	color: inherit;
}

.bi-plus-circle-fill {
	display: block;
	position: absolute;
	left: -24px;
	top: 0%;
}

.bi-arrow-right-circle-fill {
	display: block;
	position: absolute;
	left: -24px;
	top: 0%;
}

</style>
</head>
<body>
	<!-- Header-->
	<%@ include file="../fragments/header.jspf"%>
	<main>
		<div class="container fs-1 fw-bold pt-5">01 SHOPPING BAG</div>

		<!-- 강아지 장바구니 -->
		<div class="container card mt-5 border-0 d-none" id="dogCart"
			style="border-radius: 24px; box-shadow: 0px 0px 10px 0px rgb(230, 230, 230);">
			<div class="card-header fw-semibold fs-1 text-center"
				style="background-color: white; border-bottom: 3px solid black;">
				🐶❣️🧺</div>
			<div class="card-body pt-2 pb-0">
				<!-- 장바구니 목록-->
				<ul class="list-group list-group-flush text-center">
					<li class="list-group-item pb-3"
						style="border-bottom: 1px solid rgb(218, 218, 218);">
						<div class="row fw-semibold" style="font-size: 16px;">
							<!-- 전체 선택-->
							<div class="col-1 form-check fs-5">
								<input class="form-check-input " type="checkbox" value=""
									id="allDogItemCheck" checked>
							</div>
							<div class="col-7">상품정보</div>
							<div class="col-2">수량</div>
							<div class="col-2">상품금액</div>
						</div>
					</li>
				</ul>

				<ul class="list-group list-group-flush" id="dogItemList">
					<!-- <li> 장바구니 아이템 JS 처리 -->
				</ul>
			</div>
		</div>

		<!-- 고양이 장바구니 -->
		<div class="container card mt-5 border-0 d-none" id="catCart"
			style="border-radius: 24px; box-shadow: 0px 0px 10px 0px rgb(230, 230, 230);">
			<div class="card-header fw-semibold fs-1 text-center"
				style="background-color: white; border-bottom: 3px solid black;">
				🐱❣️🧺</div>
			<div class="card-body pt-2 pb-0">
				<!-- 장바구니 목록-->
				<ul class="list-group list-group-flush text-center">
					<li class="list-group-item pb-3"
						style="border-bottom: 1px solid rgb(218, 218, 218);">
						<div class="row fw-semibold" style="font-size: 16px;">
							<!-- 전체 선택-->
							<div class="col-1 form-check fs-5">
								<input class="form-check-input " type="checkbox" value=""
									id="allCatItemCheck" checked>
							</div>
							<div class="col-7">상품정보</div>
							<div class="col-2">수량</div>
							<div class="col-2">상품금액</div>
						</div>
					</li>
				</ul>

				<ul class="list-group list-group-flush" id="catItemList">
					<!-- <li> 장바구니 아이템 JS 처리 -->
				</ul>
			</div>
		</div>

		<!-- 빈 장바구니-->
		<div class="container card my-5 border-0 d-none" id="emptyCart"
			style="border-radius: 24px; box-shadow: 0px 0px 10px 0px rgb(230, 230, 230);">
			<div class="card-body py-5">
				<div class="row fw-semibold text-center" style="font-size: 20px;">
					<div class="col my-5">
						<p style="font-size: 50px;">🧺</p>
						장바구니에 담은 상품이 없습니다.
					</div>
				</div>
			</div>
		</div>

		<!--  선택 상품 삭제 -->
		<div class="container mt-4 pb-5 d-none" id="checkedDeleteArea">
			<button type="button" class="btn btn-outline-secondary btn-sm"
				id="btnCheckedDelete"
				style="width: 130px; height: 40px; border-color: rgb(218, 218, 218); background: none; border-radius: 10px; color: black; font-size: 15px;">
				선택상품 삭제</button>
		</div>

		<!-- 총 결제금액 -->
		<div class="container py-5 border-0 text-center d-none"
			id="amountArea">
			<div class="row fw-semibold py-3"
				style="font-size: 16px; border-top: 3px solid black; border-bottom: 1px solid rgb(218, 218, 218);">
				<div class="col-4">총 주문금액</div>
				<div class="col-4">총 배송비</div>
				<div class="col-4">총 결제금액</div>
			</div>
			<div class="row fw-bolder pt-5 pb-4"
				style="font-size: 28px; border-bottom: 1px solid rgb(218, 218, 218);">
				<div class="col-4">
					<span id="amountValue">0</span> <span class="fw-normal fs-6">원</span>
					<div class="fw-normal" style="font-size: 14px;">
						총 <span class="fw-bold" id="totalProductCount">0</span>개
					</div>
				</div>
				<div class="col-4" style="position: relative;">
					<span>0</span> <span class="fw-normal fs-6">원</span> <i
						class="bi bi-plus-circle-fill"
						style="font-size: 28px; color: black;"></i>
				</div>
				<div class="col-4" style="position: relative;">
					<span id="paymentValue">0</span> <span class="fw-normal fs-6">원</span>
					<i class="bi bi-arrow-right-circle-fill"
						style="font-size: 28px; color: black;"></i>
				</div>
			</div>
		</div>

		<!-- 주문 버튼  -->
		<div class="container pb-5 text-center d-none" id="orderArea">
			<button type="button"
				class="btn fw-bold btn-outline-warning text-warning btn-lg me-2"
				id="btnSelectedOrders" style="background: none; width: 180px; height: 52px;">선택주문</button>
			<button type="button"
				class="btn fw-bold btn-warning text-white btn-lg"
				id="btnAllOrders" style="width: 180px; height: 52px;">전체주문</button>
		</div>


		<!--  옵션 변경 모달-->
		<div id="optionChangeModal" class="modal" tabindex="-1">
			<div class="modal-dialog modal-dialog-centered">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title fw-semibold">옵션 변경</h5>
						<button type="button" class="btn-close" data-bs-dismiss="modal"
							aria-label="Close"></button>
					</div>
					<div class="modal-body my-2">
						<div class="fw-semibold text-black-50 text-center mt-3"
							style="font-size: 18px;">
							<div class="dropdown pb-3">
								<button class="btn btn-outline text-black dropdown-toggle w-100"
									id="btnOption"
									style="border-color: lightgray; background: none;"
									type="button" data-bs-toggle="dropdown" aria-expanded="false">
									</button>
								<!--상품별 옵션 리스트 추가 될 영역 -->
								<ul class="dropdown-menu w-100" id="optionList">
								</ul>
							</div>
						</div>
					</div>
					<div
						class="modal-footer text-center d-flex justify-content-center mb-2"
						style="border-top: 1px solid rgb(218, 218, 218);">
						<button type="button"
							class="btn btn-outline-warning text-warning btn-lg"
							style="background: none; border-radius: 8px; height: 44px; font-size: 16px;"
							data-bs-dismiss="modal">취소</button>
						<c:url var="cartListPage" value="/cart/list" />
						<a type="button"
							class="btn btn-warning text-white fw-semibold shadow-sm btn-lg"
							style="border-radius: 8px; height: 44px; font-size: 16px;"
							id="btnApplyOption">적용</a>
					</div>
				</div>
			</div>
		</div>
		<!-- end 모달 -->


	</main>

	<!-- Footer-->
	<%@ include file="../fragments/footer.jspf"%>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
		crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>

	<script src="../js/cart-list.js"></script>
</body>
</html>