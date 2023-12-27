/**
 * best.jsp / new.jsp 에 포함되는 js
 * RestController를 통해 받아온 데이터로 다시 상품목록을 만듦
 */

document.addEventListener("DOMContentLoaded", function() {
    // 펫타입. (best/new). 정렬조건. 필터조건
    
    // *현재 페이지 확인(best/new)*
    const currentPage = window.location.pathname;
    
    // *정렬조건*
    let newPageDefaultOrderBy;
    if (currentPage.includes('/ohdogcat/product/collection/best')) {
        newPageDefaultOrderBy = 'sold'; // 베스트셀러 페이지 -> 기본값'sold'
    } else if (currentPage.includes('/ohdogcat/product/collection/new')) {
        newPageDefaultOrderBy = 'createDate'; // 신상품 페이지 -> 기본값 'createDate'
    }
    
    // *펫타입*
    function getCurrentPetType() {
        return document.querySelector("input#btnradio1").checked ? 1 : 2;
    }
    
    // 펫 타입 변경
    function changePetType(newPetType) {
        currentPetType = newPetType;
        fetchProducts(1); // 펫 타입 변경 후 첫 페이지로 초기화
    }
    
    // 필터 상태 저장
    let filterState = {
        minPrice: 0,
        maxPrice: 9999000,
        keyword: '',
        inStock: true
    };
    
    // 필터 옵션 업데이트
    function updateFilterState() {
        filterState.minPrice = parseInt(document.querySelector("input#minPrice").value) || 0;
        filterState.maxPrice = parseInt(document.querySelector("input#maxPrice").value) || 9999000;
        filterState.keyword = document.querySelector("input#keyword").value || '';
        filterState.inStock = document.querySelector("input#soldOutChecked").checked;
        
         console.log("필터 상태:", filterState);
    }
    
    
    // 멍멍이 버튼 클릭 이벤트
    document.querySelector("input#btnradio1").addEventListener("click", function() {
        changePetType(1); // 멍멍이(1)로 설정
        
    });
    
    // 야옹이 버튼 클릭 이벤트
    document.querySelector("input#btnradio2").addEventListener("click", function() {
        changePetType(2); // 야옹이(2)로 설정
        
    });


    
    // *정렬조건*
    //let currentOrderBy = newPageDefaultOrderBy;
    document.querySelectorAll(".btn-group button").forEach(button => {
        button.addEventListener("click", function() {
            console.log("정렬 버튼:", this.id);
            currentOrderBy = this.id; // 현재 클릭된 버튼의 id로 업데이트
            updateFiltersAndFetchProducts(); // 정렬 변경 후 상품 데이터 불러오기
        });
    });
    
    function getCurrentOrderBy() {
        return currentOrderBy;
    }

    // *품절여부 체크박스*
    let inStockCheckbox = document.querySelector("input#soldOutChecked"); // 변경된 체크박스 선택
    let inStock = inStockCheckbox.checked; // 체크박스 상태에 따라 boolean 값 설정

    // 고양이 버튼 클릭 
    document.querySelector("input#btnradio2").addEventListener("click", function() {
        let petType = 2; // 고양이
        let orderBy = newPageDefaultOrderBy;
        let newTitle = document.getElementById("newTitle");
    
        if (newTitle.innerHTML === "🐶멍 베스트") {
            newTitle.innerHTML = "🐱냥 베스트"; // 멍멍이 베스트에서 냥냥이 베스트로 변경
        } else if (newTitle.innerHTML === "🐶멍 신상품") {
            newTitle.innerHTML = "🐱냥 신상품"; // 멍멍이 신상품에서 냥냥이 신상품으로 변경
        }
        
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
        let orderBy = newPageDefaultOrderBy;
        let newTitle = document.getElementById("newTitle");

        if (newTitle.innerHTML === "🐱냥 베스트") {
            newTitle.innerHTML = "🐶멍 베스트"; // 멍멍이 베스트에서 냥냥이 베스트로 변경
        } else if (newTitle.innerHTML === "🐱냥 신상품") {
            newTitle.innerHTML = "🐶멍 신상품"; // 멍멍이 신상품에서 냥냥이 신상품으로 변경
        }

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
    document.querySelector("button#sold").addEventListener("click", function() {
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
            console.log("필터 버튼");
            let petType = getCurrentPetType(); // 현재 선택된 petType 가져오기
            let orderBy = getCurrentOrderBy(); // 현재 선택된 orderBy 가져오기
            console.log(orderBy);

            // 입력 값 업데이트
            let minPriceInput = document.querySelector("input#minPrice") ? document.querySelector("input#minPrice").value : '';
            let maxPriceInput = document.querySelector("input#maxPrice") ? document.querySelector("input#maxPrice").value : '';
            let minPrice = minPriceInput ? parseInt(minPriceInput) : 0; // 입력값이 없으면 기본값 설정
            let maxPrice = maxPriceInput ? parseInt(maxPriceInput) : 9999000; // 입력값이 없으면 기본값 설정
            let keywordInput = document.querySelector("input#keyword") ? document.querySelector("input#keyword").value : '';
            let inStock = document.querySelector("input#soldOutChecked").checked; // 체크박스 상태
            const params= {
                                petType: petType,
                                keyword: keywordInput,
                                minPrice: minPrice,
                                maxPrice: maxPrice,
                                inStock: inStock,
                                orderBy: orderBy
                            };
                            

                  console.log(params);
                
            // 필터 옵션 적용
            axios.get("/ohdogcat/aaa/bbb/filter", {
             params   
            })
            .then(function(response) {
                showProductsCards(response.data);
            })
            .catch(function(error) {
                console.error('Error:', error);
                alert('제품 목록을 로드하는 데 실패했습니다.');
            });
        });
        
        console.dir(submitFilterButton)
    }

    // 상품목록 생성
    function showProductsCards(products) {
        if (!Array.isArray(products)) {
            console.error('products는 배열이 아님:', products);
            return; // 배열 아니면 종료
        }
        
        let productsContainer = document.getElementById('productsContainer');
        productsContainer.innerHTML = ''; // 상품목록 초기화

        if (!Array.isArray(products)) {
            console.error('products는 배열이 아님:', products);
            return; // 배열 아니면 종료
        }

        products.forEach(function (product) {
            let cardHtml = `
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
    
    
    // 현재 페이지 번호와 총 페이지 수
    let currentPageNum = 1;
    let totalPages;
 
    let currentPetType = 1; // 기본값은 1로 설정
    let currentOrderBy = newPageDefaultOrderBy; // 기본값은 ''로 설정
    
  
    
    // 필터 변경시 호출되는 함수
    function updateFiltersAndFetchProducts() {
        currentPetType = document.querySelector("input#btnradio1").checked ? 1 : 2;
        currentOrderBy = getCurrentOrderBy(); // 현재 정렬 조건 가져오기
        
        updateFilterState();
        fetchProducts(1); // 필터 변경시 첫 페이지로 초기화
    }

        
        
    
    
    // 페이지네이션 버튼을 생성하는 함수
    function createPagination(totalPages, currentPageNum) {
        const paginationContainer = document.querySelector('.pagination-container');
        paginationContainer.innerHTML = ''; // 초기화

        // 이전 페이지 버튼
        const prevButton = document.createElement('button');
        prevButton.innerText = '◀';
        prevButton.disabled = currentPageNum === 1;
        prevButton.addEventListener('click', () => fetchProducts(currentPageNum - 1));
        paginationContainer.appendChild(prevButton);

        // 페이지 번호 버튼
        for (let i = 1; i <= totalPages; i++) {
            const pageButton = document.createElement('button');
            pageButton.innerText = i;
            pageButton.className = 'page-number-button';
            pageButton.disabled = i === currentPageNum;
            pageButton.addEventListener('click', () => {
                console.log("페이지 번호 버튼 클릭됨:", i);
                fetchProducts(i);
            });
            paginationContainer.appendChild(pageButton);
        }


        // 다음 페이지 버튼
        const nextButton = document.createElement('button');
        nextButton.innerText = '▶';
        nextButton.disabled = currentPageNum === totalPages;
        nextButton.addEventListener('click', () => fetchProducts(currentPageNum + 1));
        paginationContainer.appendChild(nextButton);
    }

    // 페이지 번호에 따라 상품 데이터를 가져오는 함수
    function fetchProducts(pageNumber) {
        currentPageNum = pageNumber;
        
        const params = {
            petType: currentPetType,
            orderBy: currentOrderBy,
            minPrice: filterState.minPrice,
            maxPrice: filterState.maxPrice,
            inStock: filterState.inStock,
            keyword: filterState.keyword,
            page: pageNumber,
            size: 20
        };
    
        //console.log("API요청 매개변수:", params);
    
        axios.get('/ohdogcat/aaa/bbb/collection/best', { params })
        .then(function(response) {
            showProductsCards(response.data.products);
            totalPages = response.data.totalPages;
            createPagination(totalPages, currentPageNum);
        })
        .catch(function(error) {
            console.error('Error:', error);
        });
    }

    // 필터 버튼 클릭 이벤트
    //document.querySelector("input#submitFilter").addEventListener("click", function() {
       // updateFiltersAndFetchProducts(); // 필터 변경 후 상품 데이터 불러오기
   // });
    
    // 초기 페이지 로드 시 생성
    fetchProducts(1);
        
}); //end
