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
                    <h3 class="text-center mb-4">리뷰 쓰기</h3>        
                </div>
                <div class="card">
                    <div class="card-body">
                        <form class="row g-3" action="" method="POST" enctype="multipart/form-data" multiple>
                            <div>
                                <span>product_name + option_name 넣을 예정</span>
                            </div>
                            <div>
                                <label for="score" class="form-label">평점</label>
                                <input type="text" class="form-control" id="score" placeholder="평점을 입력하세요">
                            </div>                    
                             <div class="col-12 text-center mb-3">
                            <label for="rating">평점</label><br>
                            <span class="rating">
                                <input type="radio" id="star5" name="rating" value="5"><label for="star5" title="5 stars"><i class="fas fa-star"></i></label>
                                <input type="radio" id="star4" name="rating" value="4"><label for="star4" title="4 stars"><i class="fas fa-star"></i></label>
                                <input type="radio" id="star3" name="rating" value="3"><label for="star3" title="3 stars"><i class="fas fa-star"></i></label>
                                <input type="radio" id="star2" name="rating" value="2"><label for="star2" title="2 stars"><i class="fas fa-star"></i></label>
                                <input type="radio" id="star1" name="rating" value="1"><label for="star1" title="1 star"><i class="fas fa-star"></i></label>
                            </span>
                        </div>  
                            <div>
                                <label></label>
                                <input type="file" id="img_file" value="리뷰 이미지" >
                            </div>
                            <div>                        
                                <label></label>
                                <img alt="img_preview" src="#">
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

	</body>
</html>