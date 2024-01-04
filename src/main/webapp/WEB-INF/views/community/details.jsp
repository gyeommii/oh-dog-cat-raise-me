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
    <!-- Custom Modal Styles -->
        /* 모달 컨텐츠 스타일링 */
        .custom-modal .modal-content {
            background-color: #f8f9fa; /* 밝은 회색 배경 */
            border-radius: 8px; /* 모서리 둥글게 */
            border: 1px solid #dee2e6; /* 테두리 색상 */
        }
    
        /* 모달 헤더 스타일링 */
        .custom-modal .modal-header {
            background-color: #e9ecef; /* 헤더 배경색 */
            border-bottom: 1px solid #dee2e6; /* 헤더 아래 테두리 */
            padding: 15px; /* 패딩 */
        }
    
        .custom-modal .modal-header .modal-title {
            font-weight: bold; /* 제목 굵게 */
        }
    
        /* 모달 바디 스타일링 */
        .custom-modal .modal-body {
            padding: 20px; /* 내부 패딩 */
        }
    
        /* 모달 푸터 스타일링 */
        .custom-modal .modal-footer {
            padding: 15px; /* 패딩 */
            background-color: #e9ecef; /* 푸터 배경색 */
            border-top: 1px solid #dee2e6; /* 푸터 위 테두리 */
        }
    
        /* 모달 버튼 스타일링 */
        .custom-modal .modal-footer .btn {
            margin: 0 5px; /* 버튼 간격 */
        }
        
        /* 카드 커스텀 스타일 */
         .card {
            background-color: #f8f9fa;
            border-radius: 8px;
            border: 1px solid #dee2e6;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            max-width: 800px; /* 최대 너비 설정 */
            margin: auto; /* 중앙 정렬 */
        }
    
        .card-header {
            background-color: #e9ecef;
            border-bottom: 1px solid #dee2e6;
            font-weight: bold;
        }
    
        .card-body {
            padding: 20px;
        }
       .btn-modify {
            background-color: #FFB914; /* 배경 색상 */
            color: white; /* 글자 색상 */
            border: none; /* 테두리 제거 */
            border-radius: 5px; /* 모서리 둥글게 */
            text-decoration: none; /* 밑줄 제거 */
        }
    
        .btn-modify:hover {
            background-color: #e6a810; /* 호버 상태에서의 배경 색상 */
        } 
        
        .form-label {
            color: #828282;
        }
         .fixed-size-textarea {
            width: 100%; /* 너비를 컨테이너의 100%로 설정 */
            height: 300px; /* 높이 설정 */
            resize: none; /* 크기 조절 비활성화 */
        }
        .fixed-textarea-comment{
            width: 100%; /* 너비를 컨테이너의 100%로 설정 */
            height: 100px; /* 높이 설정 */
            resize: none; /* 크기 조절 비활성화 */
        }
        .btn-register-comment{
            margin-top: 30px;
        }
            
            
    </style>
    
    </head>
    <body>
    <div class="container-fluid p-0">
        <!--top nav -->
        <%@ include file="../fragments/top-nav.jspf"%>
        <!-- Header-->
        <%@ include file ="../fragments/header.jspf" %>
        <!-- bottom nav-->
        <%@ include file="../fragments/bottom-nav.jspf"%>
        
        <main class="my-2">
            <div class="card">
                <form class="card-body">
                    <div class="my-2 d-none">
                        <label class="form-label " for="post_pk">번호</label>
                        <input class="form-control" id="post_pk" 
                            type="text" value="${post.post_pk}" readonly />
                    </div>
                    <div class="my-2">
                        <label class="form-label" for="title">제목</label>
                        <input class="form-control" id="title" 
                            type="text" value="${post.title}" readonly />
                    </div>
                    <div class="my-2">
                        <label class="form-label" for="content">내용</label>
                        <textarea class="form-control fixed-size-textarea" id="content" readonly>${post.content}</textarea>
                    </div>
                    <div class="my-2 d-none">
                        <label class="form-label" for="member_fk">작성자</label>
                        <input class="form-control" id="member_fk" 
                            type="text" value="${post.member_fk}" readonly />
                    </div>
                    <div class="my-2">
                        <label class="form-label" for="createdTime">작성시간</label>
                        <input class="form-control" id="createdTime" 
                            type="text" value="${post.formattedCreatedTime}" readonly />
                    </div>
                    <div class="my-2">
                        <label class="form-label" for="modifiedTime">수정시간</label>
                        <input class="form-control" id="modifiedTime" 
                            type="text" value="${post.formattedModifiedTime}" readonly />
                    </div>
                </form>
                
                <c:if test="${post.member_fk eq signedMember.member_pk}" >
                    <div class="card-footer">
                        <c:url var="postModifyPage" value="/community/modify">
                            <c:param name="post_pk" value="${post.post_pk}" />
                        </c:url>
                        <a href="${postModifyPage}" class="btn btn-modify">수정하기</a>
                    </div>
                </c:if>
                
            </div>
            
            <div class="my-2 card">
                <div class="card-header d-inline-flex gap-1">
                    <!-- collapse(접기/펼치기) 기능 버튼 -->
                    <button class="btn btn-secondary" id="btnToggleComment">댓글 보기</button>
                </div>
                <!-- 댓글 토글 버튼에 의해서 접기/펼치기를 할 영역 -->
                <div class="card-body collapse" id="collapseComments">
                    <div class="card card-body">
                        <!-- 내 댓글 등록 -->
                        <div class="row my-2">
                            <div class="col-10">
                                <!-- 댓글 입력 창 -->
                                <textarea class="form-control fixed-textarea-comment"
                                    id="ctext" placeholder="댓글 입력"></textarea>
                                <!-- 댓글 작성자 아이디 -  로그인 사용자 아이디로 설정 -->
                                <input class="d-none" id="member_fk" value="${signedMember}"/>
                            </div>
                            <div class="col-2 btn-register-comment">
                                <button class="btn btn-outline-warning" 
                                    id="btnRegisterComment">등록</button>
                            </div>
                        </div>
                        
                        <!-- 포스트에 달려 있는 댓글 목록을 보여줄 영역 -->
                        <div class="my-2" id="comments"></div>
                    </div>
                </div>
            </div>
            
            <!-- 댓글 업데이트 모달 -->
            <!-- 모달 HTML -->
            <div id="commentModal" class="modal custom-modal" tabindex="-1">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title">댓글 수정</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <input class="d-none" id="modalCommentMember_fk" />
                            <textarea class="form-control" id="modalCommentText"></textarea>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
                            <button id="btnUpdateComment" type="button" class="btn btn-warning">변경완료</button>
                        </div>
                    </div>
                </div>
            </div>

        </main>
        
         
    
    </div>
    
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" 
        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" 
        crossorigin="anonymous"></script>
        
    <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>   
    
    <script>
          const signedMember = '${signedMember.member_pk}';
          console.log(signedMember);
    </script>
    
    <script src="../js/community/post-comment.js"></script>
    
    <script src="../js/navcart-count.js"></script>
    <script src="../js/cart-list.js"></script>
    

    </body>
</html>