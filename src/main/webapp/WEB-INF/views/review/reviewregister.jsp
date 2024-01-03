<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>리뷰 작성 페이지</title>
		<link
    href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
    rel="stylesheet"
    integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
    crossorigin="anonymous">
    
    <style>
        .rating input {
            display: none;
        }
        .rating label {
            font-size: 4em;
            color: gray;
            cursor: pointer;
        }
        .rating input:checked ~ label,
        .rating label:hover,        
        .rating label:hover ~ label {
            color: orange; 
        }
        .rating-reversed {
            direction: rtl; 
            unicode-bidi: bidi-override; 
        }
    </style>

    
	</head>
	<body>
    
    <!-- Header-->
    <%@ include file ="../fragments/header.jspf" %>
    
    <main>
        <div class="row">
            <div class="col-md-3">
            </div>
            <div class="col-md-6">
                <div class="container mt-5">
                    <h3 class="text-center mb-4">리뷰 등록</h3>
                </div>
                <div class="card my-2">
                    <div id="deliveryStatus">
                        <p class="text-center"></p>
                    </div>                    
                    <div class="card-body">
                        <c:url var="reviewRegister" value="/review/reviewregister" />
                        <form class="row g-3" action="${reviewRegister}" method="POST" enctype="multipart/form-data">
                            <div class="my-2">
                                <span>상품명</span>
                                <a class="form-control">${forReviewer[0].product_name}</a>
                            </div>
                            <div class="my-2">
                                <span>상품 이미지</span>
                                <c:url var="imgGetUrl" value="/image">
                                    <c:param name="imgUrl" value="${forReviewer[0].img_url}" />
                                </c:url>
                                <img class="img-fluid card-img" src="${imgGetUrl}" style="height: 500px; width: 500px;" id="img_url" alt="상품 이미지">
                            </div>
                           <div class="my-2">
                                <label for="option_fk">옵션</label>
                                <a class="form-control">${forReviewer[0].option_name}</a>
                                <input class="d-none" type="text" id="option_fk" name="option_fk" value="" readonly>
                            </div>                                                                               
                            <div class="my-2">
                                <label for="pet_fk">펫 선택</label>
                                <select class="form-select" id="pet_fk" name="pet_fk">
                                    <c:forEach var="forReviewer" items="${forReviewer}">
                                        <option value="${forReviewer.pet_pk}">${forReviewer.pet_name}＊${forReviewer.pet_type}</option>
                                    </c:forEach>
                                </select>
                            </div>
                                <div class="col-12 text-center mb-3">
                                <label for="rating">평점</label><br>
                                    <div class="rating rating-reversed" id="rating">
                                        <input type="radio" id="star5" name="score" value="5">
                                        <label for="star5">&#9733;</label>
                                        <input type="radio" id="star4" name="score" value="4">
                                        <label for="star4">&#9733;</label>
                                        <input type="radio" id="star3" name="score" value="3">
                                        <label for="star3">&#9733;</label>
                                        <input type="radio" id="star2" name="score" value="2">
                                        <label for="star2">&#9733;</label>
                                        <input type="radio" id="star1" name="score" value="1">
                                        <label for="star1">&#9733;</label>
                                    </div>
                                </div>                          
                            <div class="my-2">
                                <label for="content" class="form-label">내용</label>
                                <textarea cols="20" rows="6" class="form-control" id="content" name="content" placeholder="내용을 입력해주세요."  maxlength="500" required></textarea>
                                <span id="charCount">0 / 500</span>
                            </div>                    
                            <div class="my-2">
                                <label for="img_file" class="form-label">사진</label>
                                <input class="form-control" type="file" id="img_file" name="img_file" value="리뷰 이미지" />
                            </div>
                            <div>                        
                                <img class="d-none" alt="imagePreview" id="imagePreview" style="height: 500px; width: 500px" src="#">
                            </div>
                            <div>
                                <button id="clearButton" class="btn btn-danger d-none" onclick="clearFileInput(event);">파일 선택 제거</button>
                            </div>
                            <div>
                                <input class="d-none" type="text" value="" id="option_pk" readonly>
                            </div>                            
                            <div class="card-footer">
                                <input class="btn btn-success form-control" type="submit" value="리뷰 등록">
                            </div>                            
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </main>    
	
    <!-- Footer-->
    <%@ include file="../fragments/footer.jspf"%>
    
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" 
	    integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" 
	    crossorigin="anonymous"></script>
    
    <script src="../js/review/reviewregister.js"></script>
    
	</body>
</html>