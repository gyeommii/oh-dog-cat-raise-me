/**
 * best.jsp / new.jsp 에 포함되는 js
 * RestController를 통해 받아온 데이터로 다시 상품목록을 만듦
 */

    document.addEventListener("DOMContentLoaded", function() {
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
    
    // 낮은 가격순 TODO
    document.querySelector("button#lowest").addEventListener("click", function() {
        // 현재 펫 타입(멍멍이/야옹이) // 멍멍이버튼 선택되어있으면 true -> 1 고양이버튼 선택되어있으면 faulse -> 2
        let petType = document.querySelector("input#btnradio1").checked ? 1 : 2; 
        let orderBy = 'lowest'; // 낮은 가격순 // 이건 mapper에서 order by 하나 더 추가해야댐.. 지금 "orderBy == 'price'"로 되어있음. 최저최고가 비교불가

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

    // 높은 가격순 TODO
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
    