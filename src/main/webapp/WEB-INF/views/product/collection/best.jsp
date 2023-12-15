<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>키워주개냥 Shop</title>

<link
    href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
    rel="stylesheet"
    integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
    crossorigin="anonymous">

<style>
body {
    padding-top: 56px;
}

.horizontal-list {
    list-style-type: none;
    padding: 0;
    margin: 0;
    display: flex;
    justify-content: flex-end;
}

.horizontal-list li {
    margin-left: 10px;
}
</style>
</head>
<body>
    <div>
        <!-- Header with Banner/Navigation //////////-->
        <header>
            <nav
                class="navbar navbar-expand-lg navbar-light bg-light fixed-top">
                <div class="container-fluid">
                    <!-- Your banner goes here -->
                    <a class="navbar-brand" href="#">키워주개냥</a>

                    <!-- Navigation links -->
                    <div class="collapse navbar-collapse" id="navbarNav">
                        <ul class="navbar-nav">
                            <li class="nav-item active"><a
                                class="nav-link" href="#">Home</a></li>
                            <li class="nav-item"><a
                                class="nav-link" href="#">Categories</a></li>
                            <li class="nav-item"><a
                                class="nav-link" href="#">Cart</a></li>
                        </ul>
                    </div>
                </div>
            </nav>
        </header>
    </div>
    <main>
        <h2 class="text-center">베스트상품 ></h2>
        <div class="container mt-5">
            <div>
                <div>
                    <div>
                        <ul class="horizontal-list">
                            <div>총 [???]건</div>
                            <li><a href="">신상품순</a></li>
                            <li><a href="">판매량순</a></li>
                            <li><a href="">낮은 가격순</a></li>
                            <li><a href="">높은 가격순</a></li>
                            <li><a href="">리뷰 평점순</a></li>
                        </ul>
                    </div>
                </div>
            </div>


            <!-- Your product list display logic goes here -->
            <div class="row">
                <c:forEach var="product" items="${products}">
                    <div class="col-3">
                        <div class="card mb-4">
                            <img src="${product.imgUrl}"
                                class="card-img-top" alt="Product Image">
                            <div class="card-body">
                                <h5 class="card-title">${product.productName}</h5>
                                <p class="card-text">${product.minPrice}원</p>
                                <a href="#" class="btn btn-primary">찜</a>
                                <a href="#" class="btn btn-primary">장바구니</a>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </main>

    

    <script
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>
</body>
</html>
