<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
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
	</head>
	<body>
		<!-- Header-->
		<%@ include file="../fragments/header.jspf"%>
		
		 <!-- 강아지 장바구니 컨테이너 / 카드 -->
        <div class="container card my-5 border-0" 
             style="border-radius: 24px; box-shadow: 0px 0px 10px 0px rgb(230, 230, 230);">
            <div class="card-header fw-semibold fs-1 text-center" 
                 style="background-color: white; border-bottom: 3px solid black;"> 🐶❣️🧺 </div>
            <div class="card-body pt-2 pb-0">
                <!-- 장바구니 목록-->
                <ul class="list-group list-group-flush">
                    <li class="list-group-item pb-3">
                        <div class="text-center">
                            <div class="row fw-semibold" style="font-size: 18px;">
                                <div class="col-1 form-check">
                                    <input class="form-check-input" type="checkbox" value="" id="flexCheckChecked" checked>
                                </div>
                                <div class="col-7">   
                                    상품정보
                                </div>
                                <div class="col-2">수량</div>
                                <div class="col-2">상품금액</div>
                            </div>
                        </div>        
                    </li>

                    <!-- 장바구니 목록 아이템 정보 -->
                    <li class="list-group-item mt-2 pb-3">
                        <div class="text-center">
                            <div class="row fw-semibold">
                                <!-- 1. 상품 영역  -->
                                <div class="col-8">
                                    <div class="row">
                                        <!-- 상품 사진 -->
                                        <div class="col-1 form-check align-self-center">
                                            <input class="form-check-input" type="checkbox" value="" id="flexCheckChecked" checked></div>
                                        <div class="col-2">
                                            <img src="https://images.pet-friends.co.kr/storage/pet_friends/product/id/7/0/9/7/9/2/0/7097920d327288787513aa4bb019b6e3/10000/e411ac21909e62c4b174a8e313842b45.jpeg" class="img-fluid rounded" alt="product Img">
                                        </div>
                                        <!-- 상품 이름-->
                                        <div class="col-9 align-self-center fw-normal" style="text-align: left;">
                                            <div class="row">
                                                <div class="col">
                                                    <div class="fw-semibold" style="font-size: 16px;">리카리카 똑딱 1초 하네스</div>
                                                    <div>39,000원</div>
                                                    <div class="fs-6 text-black-50">옵션: [ 블랙 ] 39,000</div>
                                                </div>
                                                <!-- Add other product details as needed -->
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <!-- 2. 수량 영역 -->
                                <div class="col-2 align-self-center">
                                    <div class="input-group">
                                        <div class="input-group-prepend">
                                            <button class="btn btn-warning text-white fw-bold btn-sm" type="button" id="btnMinus" style="border-radius: 0; height: 30px;">-</button>
                                        </div>
                                        <input type="text" class="text-center" value="1" id="count" readonly style="border: 0; width: 40px; font-size: 0.8em; height: 30px;">
                                        <div class="input-group-append">
                                            <button class="btn btn-warning text-white fw-bold btn-sm" type="button" id="btnPlus" style="border-radius: 0; height: 30px;">+</button>
                                        </div>
                                    </div>
                                </div>

                                <!-- 3. 주문 금액 영역 -->
                                <div class="col-2 align-self-center">
                                    <div style="font-size: 18px;">39,000원</div>
                                    <div class="fs-6 fw-normal text-black-50">무료배송</div>
                                </div>
                            </div>
                        </div>        
                    </li>
                </ul>
             </div>
        </div>
		
		
		<!-- Footer-->
		<footer class="py-5 bg-dark">
			<div class="container">
				<p class="m-0 text-center text-white">Copyright &copy; ohdogcat
					2023</p>
			</div>
		</footer>
		
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
		    		integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
					crossorigin="anonymous"></script>
	</body>
</html>