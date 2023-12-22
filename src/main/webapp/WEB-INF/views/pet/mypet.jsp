<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이 펫</title>
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
                <div class="col-md-2 ">
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
                                <h2>😸마이 펫🐶</h2>
                                    <c:forEach var="pet" items="${petList}">
                                        <c:url var="addPet" value="/mypage/addpet">
                                            <c:param name="member_fk" value="${pet.member_fk}"/>
                                        </c:url>
                                    </c:forEach>
                                        <div class="card-body d-flex justify-content-end">                             
                                        <a class="btn btn-primary " role="button" href="${addPet}">추가하기</a>
                                </div> 
                        </div>
                        <div class="bg-secondary bg-opacity-10">
                            <hr class="border border-dark border-3 opacity-75 ">
                        </div>
                        <table class="table table-hover align-middle" >
                            <thead>
                                <tr class="text-center">
                                    <th>
                                        사진
                                    </th>
                                    <th>
                                        정보
                                    </th>
                                    <th>
                                        수정
                                    </th>
                                </tr>
                            </thead>
                            <tbody class="table table-borderless">
                                <c:forEach var="pet" items="${petList}">
                                    <tr class="text-center">
                                        <td>
                                        <div class="d-none">
                                            ${pet.pet_pk}
                                        </div>
                                            <div class="card-body d-flex justify-content-center">
                                                <div class="text-center">
                                                <c:url var="imgGetUrl" value="/img">
                                                    <c:param name="imgUrl" value="${pet.img_url}" />
                                                </c:url>
                                                    <img src="${imgGetUrl}" alt="펫 이미지">
                                                </div>
                                            </div>
                                            </td>
                                            <td>
                                                <div class="card-body d-flex justify-content-center">
                                                <div class="card-body">
                                                    <span>                                  
                                                    <strong class="d-block mb-3">
                                                        이름
                                                    </strong>
                                                        ${pet.pet_name}
                                                    </span>
                                                </div>
                                                <div class="card-body">
                                                    <span>
                                                    <strong class="d-block mb-3">
                                                        🐶🐱
                                                    </strong>
                                                        ${pet.pet_type}
                                                    </span>
                                                </div>
                                                <div class="card-body">                       
                                                    <span>
                                                    <strong class="d-block mb-3">
                                                        나이
                                                    </strong>
                                                        ${pet.age}
                                                    </span>
                                                </div>
                                                <div class="card-body">                                 
                                                    <span>
                                                    <strong class="d-block mb-3">
                                                        성별
                                                    </strong>
                                                        ${pet.gender}
                                                    </span>
                                                </div>
                                                <div class="card-body">                                      
                                                    <span>
                                                    <strong class="d-block mb-3">
                                                        체형
                                                    </strong>
                                                        ${pet.chehyeong}
                                                    </span>
                                                </div>
                                            </div>
                                        </td>
                                        <td>                                                                             
                                            <c:url var="modifyPet" value="/mypage/modifypet">
                                                <c:param name="pet_pk" value="${pet.pet_pk}" />
                                            </c:url>
                                            <div class="d-flex justify-content-center">
                                                <a class="btn btn-secondary" role="button" href="${modifyPet}">수정하기</a>
                                            </div>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>                        
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