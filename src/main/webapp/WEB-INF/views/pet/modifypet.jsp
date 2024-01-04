<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>펫 수정하기</title>
<link
    href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
    rel="stylesheet"
    integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
    crossorigin="anonymous">
<link
    href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css"
    rel="stylesheet" />
<link
    href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200"
    rel="stylesheet" />
<link href="../css/font.css" rel="stylesheet">
<link href="../css/nav.css" rel="stylesheet">
<link href="../css/mypage.css" rel="stylesheet">

</head>
<body>
    <!--top nav -->
    <%@ include file="../fragments/top-nav.jspf"%>
    <!-- Header-->
    <%@ include file="../fragments/header.jspf"%>
    <!-- bottom nav-->
    <%@ include file="../fragments/bottom-nav.jspf"%>



    <main class="outer-container row">
        <div class="col-2">
            <%@include file="../fragments/MyPageNav.jsp"%>
        </div>
        <div class=" my-page-container container col-8">
            <h2 class="display-6 text-center my-4">😸반려미야옹과 반려멍
                정보수정🐶</h2>
            <div class="card">
                <c:url var="modifyPet" value="/mypage/modifypet" />
                <form class="card-body" action="${modifyPet}"
                    method="POST" enctype="multipart/form-data">
                    <label class="form-label" for="img_file">펫
                        사진</label>
                    <div class="my-2 input-group">
                        <input class="form-control mb-2" id="img_file"
                            type="file" value="이미지" name="img_file" />
                        <button id="clearButton"
                            class="btn btn-danger d-none mb-2"
                            onclick="clearFileInput(event);">파일
                            선택 제거</button>
                    </div>
                    <div class="text-center">
                        <img id="imagePreview" class="d-none" src="#"
                            alt="Image Preview"
                            style="height: 200px; width: 200px" />
                    </div>
                    <div class="my-2">
                        <label class="d-none" for="pet_pk">펫 고유키</label>
                        <input class="d-none" id="pet_pk" name="pet_pk"
                            type="text" value="${petList.pet_pk}"
                            readonly />
                    </div>
                    <div class="my-2">
                        <label class="form-label" for="pet_name">이름</label>
                        <input class="form-control" id="pet_name"
                            name="pet_name" type="text"
                            value="${petList.pet_name}" autofocus
                            required />
                    </div>
                    <div class="my-2">
                        <label class="form-label" for="pet_type">🐶🐱</label>
                        <select class="form-select" name="pet_type"
                            id="pet_type">
                            <option value="강아지">강아지</option>
                            <option value="고양이">고양이</option>
                        </select>
                    </div>
                    <div class="my-2">
                        <label class="form-label" for="age">나이</label> <input
                            class="form-control" id="age" type="text"
                            name="age" placeholder="나이"
                            value="${petList.age}" required />
                    </div>
                    <div class="my-2">
                        <label class="form-label" for="gender">성별</label>
                        <select class="form-select" name="gender"
                            id="gender">
                            <option value="female">암컷</option>
                            <option value="male">수컷</option>
                        </select>
                    </div>
                    <div class="my-2">
                        <label class="form-label" for="chehyeong">체형</label>
                        <select class="form-select" name="chehyeong"
                            id="chehyeong">
                            <option value="소형">소형</option>
                            <option value="중형">중형</option>
                            <option value="대형">대형</option>
                        </select>
                    </div>
                    <input class="form-control btn btn-success my-3"
                        onclick="return confirm('수정하시겠습니까?')"
                        type="submit" value="수정완료" />
                </form>
            </div>

        </div>
    </main>

    <!-- Footer-->
    <%@ include file="../fragments/footer.jspf"%>

    <script
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
    <script src="../js/pet/modifypet.js"></script>
    <script src="../js/navcart-count.js"></script>
    <script src="../js/cart-list.js"></script>
</body>
</html>