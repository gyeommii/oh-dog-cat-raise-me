/**
 * best.jsp / new.jsp 에 포함되는 js
 * RestController를 통해 받아온 데이터로 다시 상품목록을 만듦
 */

    document.addEventListener("DOMContentLoaded", function() {
    // 고양이 버튼 클릭 이벤트 리스너
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
    
    function showProductsCards(products) {
    var productsContainer = document.getElementById('productsContainer');
    productsContainer.innerHTML = ''; // 상품목록 초기화
                console.log(products);

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

    // 멍멍이 버튼 클릭 이벤트 리스너
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
});
    