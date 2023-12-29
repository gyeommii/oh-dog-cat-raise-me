<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>New Post | ohdogcat</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
          crossorigin="anonymous">
</head>
<body>
    <%@ include file="../fragments/header.jspf"%>
    <main class="container mt-5">
            
        
        <div class="" style="margin: auto; width:60%">
            <div class="card">
                <div class="card-body">
                    <div class="mb-3">
                        <label for="category" class="form-label"></label> 
                        <select class="form-select" id="category" name="category">
                            <option selected>게시판 선택</option>
                            <option value="myPetPride">내새꾸 자랑</option>
                            <option value="adoptionDog">[입양]키워주개</option>
                            <option value="adoptionCat">[입양]키워주냥</option>
                            <option value="missingDog">[실종/제보]길잃은멍</option>
                            <option value="missingCat">[실종/제보]길잃은냥</option>
                        </select>
                    </div>
                    <div>
                        <form action="postServlet" method="post">
                            <div class="mb-2">
                                <label for="title" class="form-label"></label>
                                <input type="text" class="form-control" id="title" name="title" placeholder="제목을 입력하세요." required>
                            </div>
                            <div class="mb-2">
                                <label for="author" class="form-label"></label>
                                <input type="text" class="form-control d-none" id="author" name="author" required>
                            </div>
                            <div class="mb-2">
                                <label for="content" class="form-label"></label>
                                <textarea class="form-control" id="content" name="content" rows="5" required></textarea>
                            </div>
                            <button type="submit" class="btn btn-primary">등록</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </main>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
            crossorigin="anonymous"></script>
</body>
</html>
