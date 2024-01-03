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
    <div class="container-fluid">
        <!-- header -->
        <%@ include file="../fragments/header.jspf"%>
        
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
                        <textarea class="form-control" id="content" readonly>${post.content}</textarea>
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
                
                <div class="card-footer">
                        <c:url var="postModifyPage" value="/community/modify">
                            <c:param name="post_pk" value="${post.post_pk}" />
                        </c:url>
                        <a href="${postModifyPage}" class="btn btn-primary">수정하기</a>
                </div>
                
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
                                <textarea class="form-control"
                                    id="ctext" placeholder="댓글 입력"></textarea>
                                <!-- 댓글 작성자 아이디 -  로그인 사용자 아이디로 설정 -->
                                <input class="" id="member_fk" />
                            </div>
                            <div class="col-2">
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
            <div id="commentModal" class="modal" tabindex="-1">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title">댓글 수정</h5>
                            <button type="button" class="btn-close"
                                data-bs-dismiss="modal"
                                aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <!-- 수정할 댓글 아이디 -->
                            <input class="d-none" id="modalCommentMember_fk" />
                            <!-- 수정댓글 입력 -->
                            <textarea class="form-controll" id="modalCommentText"></textarea>
                        </div>
                        <div class="modal-footer">
                            <button type="button"
                                class="btn btn-secondary"
                                data-bs-dismiss="modal">취소</button>
                            <button id="btnUpdateComment" type="button"
                                class="btn btn-warning">변경내용 저장</button>
                        </div>
                    </div>
                </div>
            </div> <!-- end Modal -->

        </main>
        
         
    
    </div>
    
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" 
        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" 
        crossorigin="anonymous"></script>
        
    <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>   
    
    <script>
          const signedMember = '${signedMember}';
    </script>
    
    <script src="../js/community/post-comment.js"></script>
    

    </body>
</html>