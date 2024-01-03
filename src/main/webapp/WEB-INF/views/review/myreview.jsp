<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>${signedMember.member_id}의 리뷰 페이지</title>
		<link
    href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
    rel="stylesheet"
    integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
    crossorigin="anonymous">
	</head>
	<body>
    
    <!-- Header-->
    <%@ include file ="../fragments/header.jspf" %>
    <div class="container">
        <div class="row">
            <div class="col-md-3">
            </div>
            <div class="col-md-6">
                <div class="container mt-5">
                    <h3 class="text-center mb-4">내가 작성한 리뷰 목록</h3>
                </div>
                <table class="card-body table table-hover text-center">
                    <thead>
                        <tr>
                            <th>번호</th>
                            <th>옵션</th>
                            <th>펫</th>
                            <th>내용</th>
                            <th>이미지</th>
                            <th>평점</th>
                            <th>수정시간</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="myReview" items="${myReview}">
                            <tr>
                                <td>${myReview.review_pk}</td>
                                <td>${myReview.option_fk}</td>
                                <td>${myReview.pet_fk}</td>
                                <td>${myReview.content}</td>
                                <td><c:url var="imgGetUrl" value="/image">
                                        <c:param name="imgUrl" value="${myReview.img}" />
                                    </c:url>
                                        <c:choose>
                                            <c:when test="${empty myReview.img}">
                                                <img class="img-fluid card-img" src="" style="height: 200px; width: 200px" alt="이미지 X">
                                            </c:when>
                                            <c:otherwise>
                                                <img class="img-fluid card-img" src="${imgGetUrl}" style="height: 200px; width: 200px" alt="리뷰 이미지">
                                            </c:otherwise>
                                        </c:choose>
                                </td>
                                <td>${myReview.score}</td>
                                <td>${myReview.modified_time}</td>                    
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    <!-- Footer-->
    <%@ include file="../fragments/footer.jspf"%>
	

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" 
	    integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" 
	    crossorigin="anonymous"></script>

	</body>
</html>