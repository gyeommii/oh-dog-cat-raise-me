/**
 * best.jsp / new.jsp 에 포함되는 js
 * RestController를 통해 받아온 데이터로 다시 상품목록을 만듦
 */

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


