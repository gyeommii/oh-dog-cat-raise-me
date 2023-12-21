<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>펫 추가하기</title>
<link
    href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
    rel="stylesheet"
    integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
    crossorigin="anonymous">
</head>
<body>

    <%@include file="../fragments/header.jspf"%>

    <main>
        <div class="container">
            <div class="row">
                <div class="col-md-2">
                    <nav class="bg-secondary bg-opacity-10">
                        <ul class="nav flex-column">
                            <c:url var="myPetPage" value="/mypage/pet" />                    
                            <li class="nav-item border border-secondary">
                            <c:url var="homePage" value="/"/>
                                <h4><a class="nav-link active text-center my-3 text-black" href="${homePage}">😸홈페이지🐶</a></h4>
                            </li>
                            <li class="nav-item border border-secondary">
                                <h4><a class="nav-link active text-center my-3 text-black" href="${myPetPage}">😸마이 펫🐶</a></h4>
                            </li>
                        </ul>
                    </nav>
                </div>
                <div class="col-md-10">
                    <div class="card">
                        <div class="card-body bg-secondary bg-opacity-10">
                            <div>
                                <h2>😾펫 추가하기🐶</h2>
                            </div>
                        </div>
                        <div class="bg-secondary bg-opacity-10">
                            <hr class="border border-dark border-3 opacity-75">
                        </div>
                        <div class="card">
                            
                            <c:url var="addPet" value="/mypage/addpet"/>
                            <form class="card-body" action="${addPet}" method="POST" enctype="multipart/form-data">                                
                                <div class="my-2">
                                    <label class="form-label" for="img">이미지</label>
                                    <input class="form-control" id="img" type="file" value="이미지" name="img" />
                                </div>                                
                                <div class="my-2">
                                    <label class="form-label" for="pet_name">펫 이름</label>
                                    <input class="form-control" id="pet_name" type="text" name="pet_name" placeholder="펫 이름" autofocus required />
                                </div>
                                <div class="my-2">
                                    <label class="form-label" for="pet_type">펫 유형</label> 
                                    <select class="form-select" name="pet_type">
                                        <option value="dog">강아지</option>
                                        <option value="cat">고양이</option>
                                    </select>
                                </div>
                                <div class="my-2">
                                    <label class="form-label" for="age">나이</label>
                                    <input class="form-control" id="age" type="text" name="age" placeholder="나이" required />
                                </div>
                                <div class="my-2">
                                    <label class="form-label" for="gender">성별</label> 
                                    <select class="form-select" name="gender">
                                        <option value="male">남자</option>
                                        <option value="female">여자</option>
                                    </select>
                                </div>
                                <div class="my-2">
                                    <label class="form-label" for="chehyeong">체형</label> 
                                    <select class="form-select" name="chehyeong">
                                        <option value="small">소형</option>
                                        <option value="middle">중형</option>
                                        <option value="big">대형</option>
                                    </select>
                                </div>
                                <div>
                                    <input class="form-control btn btn-success" type="submit" value="작성완료" />
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </main>


    <script
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>

</body>
</html>