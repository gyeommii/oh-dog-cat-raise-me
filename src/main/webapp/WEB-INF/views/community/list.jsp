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
        
    
    </style>

	</head>
	<body>
        <!-- header -->
        <%@ include file="../fragments/header.jspf"%>
        <main>
        
        
        <div>
            <div class="container mt-4">
                <c:forEach items="${posts}" var="post">
                    <c:url var="postDetailsPage" value="/post/details">
                        <c:param name="id" value="${post.post_pk}" />
                    </c:url>
                    <div class="card mb-3">
                        <div class="card-body">
                            <h5 class="card-title">
                                <a href="${postDetailsPage}">${post.title}</a>
                            </h5>
                            <p class="card-text">${post.author}</p>
                            <p class="card-text">
                                <small class="text-muted">${post.modified_time}</small>
                            </p>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
        
            <div>
                <a href=""> 
                    <input type="button" value="글쓰기" class="btn btn-xs pull-right" style="font-size: 17px;">
                </a>
            </div>
    
        </main>
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