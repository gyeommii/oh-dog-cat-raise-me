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
                <div class="card">
                    <div class="card-body">
                        <c:url var="reviewRegister" value="/mypage/reviewregister" />
                        <form class="row g-3" action="${reviewRegister}" method="POST" enctype="multipart/form-data">
                            <div class="card">
                                <label class="form-label" for="product_name">상품</label>
                                <a class="form-control" id="product_name"></a>
                                <label class="form-label" for="option_name">옵션</label>
                                <a class="form-control" id="option_name">${forReviewer.option_name}</a>
                                <img class="img-fluid card-img" alt="product_img_url" id="product_img_url" src="${forReviewer.img_url}">
                            <div>
                                <select>
                                    <option>
                                </select>
                            </div>
                            </div>                             
                                <div class="col-12 text-center mb-3">
                                <label for="rating">평점</label><br>
                                    <div class="rating rating-reversed">
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
                            <div>
                                <label for="content" class="form-label">내용</label>
                                <textarea cols="20" rows="6" class="form-control" id="content" name="content" placeholder="내용을 입력해주세요."  maxlength="500"></textarea>
                                <span id="charCount">0 / 500</span>
                            </div>                    
                            <div>
                                <label for="img_file" class="form-label">사진</label>
                                <input class="form-control" type="file" id="img_file" name="img_file" value="리뷰 이미지" multiple>
                            </div>
                            <div>                        
                                <img class="img-fluid card-img" alt="img_preview" id="img_preview" src="#">
                            </div>
                            
                            <div>
                                <input class="d-none" type="text" value="" id="member_id" readonly>
                            </div>
                            <div>
                                <input class="d-none" type="text" value="${forReviewer.pet_pk}" id="pet_pk" readonly>
                            </div>
                            <div>
                                <input class="d-none" type="text" value="${forReviewer.product_pk}" id="product_pk" readonly>
                            </div>
                            <div>
                                <input class="d-none" type="text" value="${forReviewer.option_pk}" id="option_pk" readonly>
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
    <footer class="py-5 bg-dark">
        <div class="container">
            <p class="m-0 text-center text-white">Copyright &copy; ohdogcat 2023</p>
        </div>
    </footer>


    
    
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" 
	    integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" 
	    crossorigin="anonymous"></script>
    
    <script src="../js/review/reviewregister.js"></script>
    
	</body>
</html>