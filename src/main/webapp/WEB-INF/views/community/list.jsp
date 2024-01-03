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
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
        <link href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" rel="stylesheet" />
        <link href="../css/font.css" rel="stylesheet" >
        <link href="../css/nav.css" rel="stylesheet" >
              
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
        flex-flow: row nowrap;
        justify-content: center; /* 가로 중앙 정렬 */
        width: 80%; /* 너비를 80%로 설정 */
        margin: 0 auto; /* 컨테이너를 중앙에 배치 */
    }
     .search-container {
        flex-flow: row nowrap;
        justify-content: space-around; /* 가로 중앙 정렬 */
        width: 80%; /* 너비를 80%로 설정 */
        margin: 0 auto; /* 컨테이너를 중앙에 배치 */
    }
    
    .card {
        max-width: 800px; /* 카드의 최대 가로 길이 설정 */
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
    
    .margin-class {
        margin-left: 170px;  /* 왼쪽 마진 */
        margin-right: 170px; /* 오른쪽 마진 */
    }
    .btn-custom{
        color: white; /* 글자색을 흰색으로 설정 */
        background-color: #FFB914;
    }
    
    .btn-group{
        justify-content: center;
    }
    
    .btn-custom-width {
        width: 180px; /* 버튼의 가로 길이를 160px로 설정 */
    }
    /* 검색 버튼 클릭 시 스타일 */
    .btn-custom:hover {
        background-color: #FFCD28; /* 클릭 시 배경 색상 변경 */
        color: white; /* 클릭 시 텍스트 색상*/
    }
    
    .card-text-postMember{
         margin-left: 10px;
    }
        
    
    
   
    
    
        
    
    </style>

	</head>
	<body>
        <!--top nav -->
        <%@ include file="../fragments/top-nav.jspf"%>
        <!-- Header-->
        <%@ include file ="../fragments/header.jspf" %>
        <!-- bottom nav-->
        <%@ include file="../fragments/bottom-nav.jspf"%>
        
        <main>
            <div class="container mt-4">
                <div class="margin-class" style="width:80;" >
                    <div>
                        <div class="search-container">
                            <c:url var="postSearchPage" value="/community/search" />
                            <form action="${postSearchPage}" method="get" id="searchForm">
                                <div class="row">
                                    <div class="col-3">
                                        <select class="form-control" name="searchCategory">
                                            <option value="t">제목</option>
                                            <option value="c">내용</option>
                                            <option value="tc">제목+내용</option>
                                            <option value="a">작성자</option>
                                        </select>
                                    </div>
                                    <div class="col-6">
                                        <input class="form-control" type="text" 
                                            name="keyword" placeholder="검색어" required autofocus />
                                    </div>
                                    <div class="col-3">
                                        <button type="button" class="form-control btn btn-custom" onclick="submitSearch()">검색</button>
    
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                    <h1></h1>
                    <!-- 카테고리, 새 글쓰기, 정렬 버튼 -->
                    <div class="write-button-container">
                        <div class="row" >
                            <div class="col-3">
                                <c:set var="currentCategory" value="${param.category != null ? param.category : 'all'}" />
                                <select class="form-control" id="categoryFilter" name="categoryFilter" onchange="filterByCategory()">
                                    <option value="0" ${currentCategory == '0' ? 'selected' : ''}>전체</option>
                                    <option value="1" ${currentCategory == '1' ? 'selected' : ''}>내새꾸 자랑</option>
                                    <option value="2" ${currentCategory == '2' ? 'selected' : ''}>[입양]키워주개</option>
                                    <option value="3" ${currentCategory == '3' ? 'selected' : ''}>[입양]키워주냥</option>
                                    <option value="4" ${currentCategory == '4' ? 'selected' : ''}>[실종/제보]길잃은멍</option>
                                    <option value="5" ${currentCategory == '5' ? 'selected' : ''}>[실종/제보]길잃은냥</option>
                                </select>
                            </div>
                            <!-- 정렬 버튼 -->
                            <div class="btn-group col-3"  role="group" aria-label="Basic example">
                                <button onclick="sortPosts('desc')" class="btn">최신글</button>
                                <button onclick="sortPosts('asc')" class="btn">오래된글</button>
                            </div> 
                            <div class="col-3">
                            
                            </div>


                            <div class="col-3">
                                <a href="../community/createPost"> 
                                    <button class="btn btn-custom btn-custom-width" >글쓰기</button>
                                </a>
                            </div>
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
                                <p class="card-text card-text-postMember" >${post.member_fk}</p>
                                <p class="card-text" style="text-align: right;">
                                    <small class="text-muted">${post.formattedModifiedTime}</small>
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
                
    <script>
        function filterByCategory() {
            var selectedCategory = document.getElementById('categoryFilter').value;
            window.location.href = '/ohdogcat/community/list?category=' + selectedCategory;
        }
        
        function getQueryParams() {
            var queryParams = {};
            var queryStrings = window.location.search.substring(1).split('&');
            for (var i = 0; i < queryStrings.length; i++) {
                var pair = queryStrings[i].split('=');
                if (pair.length == 2) {
                    queryParams[decodeURIComponent(pair[0])] = decodeURIComponent(pair[1]);
                }
            }
            return queryParams;
        }

        function sortPosts(order) {
            var queryParams = getQueryParams();
            queryParams['sort'] = order;
            var newQuery = Object.keys(queryParams).map(key => key + '=' + queryParams[key]).join('&');
            window.location.href = '/ohdogcat/community/list?' + newQuery;
        }
        
        function submitSearch() {
            document.getElementById("searchForm").submit();
        }

        
        
        


    </script>
    
    <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
    
    <script src="../js/navcart-count.js"></script>
    
    <script src="../js/cart-list.js"></script>
    
               
    <!-- Footer-->
    <%@ include file="../fragments/footer.jspf"%>
    
	</body>
</html>