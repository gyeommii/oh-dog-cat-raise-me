/**
 * best.jsp / new.jsp 에 포함되는 js
 * RestController를 통해 받아온 데이터로 다시 상품목록을 만듦
 */

document.addEventListener("DOMContentLoaded", function() {
    // *펫타입*
    function getCurrentPetType() {
        return document.querySelector("input#btnradio1").checked ? 1 : 2;
    }
    // *정렬조건*
    let currentOrderBy = 'new'; // 기본값으로 'new'를 설정
    document.querySelectorAll(".btn-group button").forEach(button => {
        button.addEventListener("click", function() {
            currentOrderBy = this.id; // 현재 클릭된 버튼의 id로 업데이트
        });
    });
    
    function getCurrentOrderBy() {
        return currentOrderBy;
    }

    // *체크박스*
    let inStockCheckbox = document.querySelector("input#soldOutChecked"); // 변경된 체크박스 선택
    let inStock = inStockCheckbox.checked; // 체크박스 상태에 따라 boolean 값 설정

    // 고양이 버튼 클릭 
    document.querySelector("input#btnradio2").addEventListener("click", function() {
        let petType = 2; // 고양이
        let orderBy = 'sold';
        let newTitle = document.getElementById("newTitle");
    
        newTitle.innerHTML = "🐱냥 베스트"; // 타이틀 텍스트 변경
        
        const uri = `/ohdogcat/aaa/bbb/best`; 

        axios.get(uri, {
            params: {
                petType: petType,
                orderBy: orderBy
            }
        })
        .then(function (response) {
            showProductsCards(response.data); // 상품목록 업데이트
        })
        .catch(function (error) {
            console.error('Error:', error);
            alert('제품 목록을 로드하는 데 실패했습니다.');
        });
    });

    // 강아지 버튼 클릭 
    document.querySelector("input#btnradio1").addEventListener("click", function() {
        let petType = 1; // 멍멍이
        let orderBy = 'sold';
        let newTitle = document.getElementById("newTitle");

        newTitle.innerHTML = "🐶멍 베스트"; 

        const uri = `/ohdogcat/aaa/bbb/best`;

        axios.get(uri, {
            params: {
                petType: petType,
                orderBy: orderBy
            }
        })
        .then(function (response) {
            showProductsCards(response.data); // 상품목록 업데이트
        })
        .catch(function (error) {
            console.error('Error:', error);
            alert('제품 목록을 로드하는 데 실패했습니다.');
        });
    });

    // 신상품순 버튼 클릭 
    document.querySelector("button#new").addEventListener("click", function() {
        // 현재 펫 타입(멍멍이/야옹이) // 멍멍이버튼 선택되어있으면 true -> 1 고양이버튼 선택되어있으면 faulse -> 2
        let petType = document.querySelector("input#btnradio1").checked ? 1 : 2; 
        let orderBy = 'new'; // 신상품순

        const uri = `/ohdogcat/aaa/bbb/best`; 

        axios.get(uri, {
            params: {
                petType: petType,
                orderBy: orderBy
            }
        })
        .then(function (response) {
            showProductsCards(response.data);
        })
        .catch(function (error) {
            console.error('Error:', error);
            alert('제품 목록을 로드하는 데 실패했습니다.');
        });
    });

    // 판매량순 
    document.querySelector("button#best").addEventListener("click", function() {
        // 현재 펫 타입(멍멍이/야옹이) // 멍멍이버튼 선택되어있으면 true -> 1 고양이버튼 선택되어있으면 faulse -> 2
        let petType = document.querySelector("input#btnradio1").checked ? 1 : 2; 
        let orderBy = 'sold'; // 판매량순

        const uri = `/ohdogcat/aaa/bbb/best`; 

        axios.get(uri, {
            params: {
                petType: petType,
                orderBy: orderBy
            }
        })
        .then(function (response) {
            showProductsCards(response.data);
        })
        .catch(function (error) {
            console.error('Error:', error);
            alert('제품 목록을 로드하는 데 실패했습니다.');
        });
    });
    
    // 낮은 가격순
    document.querySelector("button#lowest").addEventListener("click", function() {
        // 현재 펫 타입(멍멍이/야옹이) // 멍멍이버튼 선택되어있으면 true -> 1 고양이버튼 선택되어있으면 faulse -> 2
        let petType = document.querySelector("input#btnradio1").checked ? 1 : 2; 
        let orderBy = 'lowest'; // 낮은 가격순 

        const uri = `/ohdogcat/aaa/bbb/best`; 

        axios.get(uri, {
            params: {
                petType: petType,
                orderBy: orderBy
            }
        })
        .then(function (response) {
            showProductsCards(response.data);
        })
        .catch(function (error) {
            console.error('Error:', error);
            alert('제품 목록을 로드하는 데 실패했습니다.');
        });
    });

    // 높은 가격순
    document.querySelector("button#highest").addEventListener("click", function() {
        // 현재 펫 타입(멍멍이/야옹이) // 멍멍이버튼 선택되어있으면 true -> 1 고양이버튼 선택되어있으면 faulse -> 2
        let petType = document.querySelector("input#btnradio1").checked ? 1 : 2; 
        let orderBy = 'highest'; // 높은 가격순

        const uri = `/ohdogcat/aaa/bbb/best`; 

        axios.get(uri, {
            params: {
                petType: petType,
                orderBy: orderBy
            }
        })
        .then(function (response) {
            showProductsCards(response.data);
        })
        .catch(function (error) {
            console.error('Error:', error);
            alert('제품 목록을 로드하는 데 실패했습니다.');
        });
    });
    
    // 리뷰 평점순(높은순)
    document.querySelector("button#topRated").addEventListener("click", function() {
        // 현재 펫 타입(멍멍이/야옹이) // 멍멍이버튼 선택되어있으면 true -> 1 고양이버튼 선택되어있으면 faulse -> 2
        let petType = document.querySelector("input#btnradio1").checked ? 1 : 2; 
        let orderBy = 'reviewScore'; // 리뷰 평점 높은순

        const uri = `/ohdogcat/aaa/bbb/best`; 

        axios.get(uri, {
            params: {
                petType: petType,
                orderBy: orderBy
            }
        })
        .then(function (response) {
            showProductsCards(response.data);
        })
        .catch(function (error) {
            console.error('Error:', error);
            alert('제품 목록을 로드하는 데 실패했습니다.');
        });
    });
    
    // 필터옵션 버튼
    let submitFilterButton = document.querySelector("input#submitFilter");
    if (submitFilterButton) {
        submitFilterButton.addEventListener("click", function() {
            let petType = getCurrentPetType(); // 현재 선택된 petType 가져오기
            let orderBy = getCurrentOrderBy(); // 현재 선택된 orderBy 가져오기

            // 입력 값 업데이트
            let minPriceInput = document.querySelector("input#minPrice") ? document.querySelector("input#minPrice").value : '';
            let maxPriceInput = document.querySelector("input#maxPrice") ? document.querySelector("input#maxPrice").value : '';
            let minPrice = minPriceInput ? parseInt(minPriceInput) : 0; // 입력값이 없으면 기본값 설정
            let maxPrice = maxPriceInput ? parseInt(maxPriceInput) : 9999000; // 입력값이 없으면 기본값 설정
            let keywordInput = document.querySelector("input#keyword") ? document.querySelector("input#keyword").value : '';
            let inStock = document.querySelector("input#soldOutChecked").checked; // 체크박스 상태

            // 필터 옵션 적용
            axios.get("/ohdogcat/aaa/bbb/filter", {
                params: {
                    petType: petType,
                    keyword: keywordInput,
                    minPrice: minPrice,
                    maxPrice: maxPrice,
                    inStock: inStock,
                    orderBy: orderBy
                }
            })
            .then(function(response) {
                showProductsCards(response.data);
            })
            .catch(function(error) {
                console.error('Error:', error);
                alert('제품 목록을 로드하는 데 실패했습니다.');
            });
        });
    }

    // ProductRestController를 통해 받아온 데이터로 다시 상품목록을 만듦
    function showProductsCards(products) {
        var productsContainer = document.getElementById('productsContainer');
        productsContainer.innerHTML = ''; // 상품목록 초기화

        if (!Array.isArray(products)) {
            console.error('products는 배열이 아님:', products);
            return; // 배열 아니면 종료
        }

        products.forEach(function (product) {
            var cardHtml = `
                <div class="col-3">
                <div class="card mb-5">
                    <img class="card-img-top" src="${product.imgUrl}" alt="Product image">
                    <div class="card-body">
                        <h5 class="card-title">${product.productName}</h5>
                        <p class="card-text">${product.minPrice}</p>
                    </div>
                </div>
            </div>
            `;
            productsContainer.innerHTML += cardHtml;
        });
    }
});
