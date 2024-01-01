<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>


<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>커뮤니티 | ohdogcat</title>
		
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" 
			  rel="stylesheet" 
			  integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" 
			  crossorigin="anonymous">
              
    <style>
    
    .ellipsis {
        white-space: nowrap;
        overflow: hidden;    
        text-overflow: ellipsis; 
        max-width: 150px;
    }
    
    /* 일반 링크 스타일 */
    a {
        color: #696969; /* 링크 색상 */
        text-decoration: none; /* 밑줄 없앰 */
        font-weight: bold; /* 글씨 굵게 */
    }

    /* 링크에 마우스를 올렸을 때의 스타일 */
    a:hover {
        color: #ffd700; /* 마우스 오버시 색상 변경 */
        text-decoration: underline; /* 밑줄 추가 */
    }

    /* 링크가 방문된 상태일 때의 스타일 */
    a:visited {
        color: #a9a9a9; /* 방문한 링크 색상 */
    }
    
     .write-button-container {
        display: flex;
        justify-content: center; /* 가로 중앙 정렬 */
        
        margin: 0 auto; /* 컨테이너 중앙 정렬 */
    }
    
    .card {
        max-width: 500px; /* 카드의 최대 가로 길이 설정 */
        margin: 0 auto; /* 카드를 중앙에 정렬 */
    }
    
    .card-title {
        white-space: nowrap; /* 텍스트를 한 줄로 설정 */
        overflow: hidden; /* 내용이 넘칠 경우 숨김 처리 */
        text-overflow: ellipsis; /* 넘친 텍스트를 말줄임표로 표시 */
        max-width: 100%; /* 최대 너비 설정 */
    }
    
    .category-label {
        font-weight: bold;
        color: #FF9B00; /* 예시 색상 */
        padding: 2px 6px; /* 텍스트 주변 여백 */
        margin-right: 5px; /* 오른쪽 마진 */
    }
    
    
        
    
    </style>

	</head>
	<body>
        <!-- header -->
        <%@ include file="../fragments/header.jspf"%>
        <main>
            <div class="container mt-4">
                <div>
                    <!-- 카테고리, 새 글쓰기, 정렬 버튼 -->
                    <div class="write-button-container">
                        <div class="" >
                            <label for="post_category_fk" class="form-label"></label> 
                            <select class="form-select " id="category" name="post_category_fk">
                                <option selected>게시판 선택</option>
                                <option value="1">내새꾸 자랑</option>
                                <option value="2">[입양]키워주개</option>
                                <option value="3">[입양]키워주냥</option>
                                <option value="4">[실종/제보]길잃은멍</option>
                                <option value="5">[실종/제보]길잃은냥</option>
                            </select>
                            <!-- 정렬 버튼 -->
                            <div class="btn-group" role="group" aria-label="Basic example">
                                <button type="button" id="newest" class="btn">최신글</button>
                                <button type="button" id="oldest" class="btn">오래된글</button>
                            </div>
                            <a href="../community/createPost"> 
                                <button class="btn btn-warning" style="font-size: 17px;">글쓰기</button>
                            </a>
                        </div>
                        
                    </div>
                </div>

                <!-- 글 목록 -->
                <div class="mt-4">
                    <c:forEach items="${posts}" var="post">
                        <c:url var="postDetailsPage" value="/community/details">
                            <c:param name="post_pk" value="${post.post_pk}" />
                        </c:url>
                        <div class="card mb-3">
                            <div class="card-body">
                                <h5 class="card-title ellipsis">
                                    <a href="${postDetailsPage}">
                                        <span class="category-label">
                                            <c:choose>
                                                <c:when test="${post.post_category_fk == 1}">내새꾸 자랑</c:when>
                                                <c:when test="${post.post_category_fk == 2}">[입양]키워주개</c:when>
                                                <c:when test="${post.post_category_fk == 3}">[입양]키워주냥</c:when>
                                                <c:when test="${post.post_category_fk == 4}">[실종/제보]길잃은멍</c:when>
                                                <c:when test="${post.post_category_fk == 5}">[실종/제보]길잃은냥</c:when>
                                            </c:choose>
                                        </span>
                                        ${post.title}
                                    </a>
                                </h5>
                                <p class="card-text" >${post.member_fk}</p>
                                <p class="card-text" style="text-align: right;">
                                    <small class="text-muted">${post.modified_time}</small>
                                </p>
                            </div>
                        </div>
                </c:forEach>

                </div>
            </div>

    </main>
        
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" 
				integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" 
				crossorigin="anonymous"></script>
                
               
    <!-- Footer-->
    <%@ include file="../fragments/footer.jspf"%>
    
	</body>
</html>