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
	</head>
	<body>
        <main>
            <table>
                <thead>
                    <tr>
                        <th>번호</th>
                        <th>제목</th>
                        <th>작성자</th>
                        <th>조회수</th>
                        <th>작성일자</th>
                    </tr>
                </thead>
    
                <tbody>
                    <c:forEach items="" var="">                         
                        
                    </c:forEach>
                </tbody>
            </table>
    
            <div>
                <a href="${contextPath }/board/write"> 
                    <input type="button" value="글쓰기" class="btn btn-xs pull-right" style="font-size: 17px;">
                </a>
            </div>
    
        </main>



    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" 
				integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" 
				crossorigin="anonymous"></script>
	</body>
</html>