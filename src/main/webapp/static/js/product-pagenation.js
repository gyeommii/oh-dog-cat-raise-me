/**
 * list.jsp / best.jsp / new.jsp 에 포함되는 js
 * pagenation 기능
 */

document.addEventListener("DOMContentLoaded", function(){
    const productsPerPage = 20; // 페이지당 상품 수
    
    const getTotalPageCount = () => {
        return Math.ceil(data.length / productsPerPage);
    }
    
    const numberButtonWrapper = document.querySelector('.number-button-wrapper');
    
    const setPageButton = () => {
        numberButtonWrapper.innerHTML = ''; 
        
        for(let i = 1; i <= getTotalPageCount(); i++){
            numberButtonWrapper.innerHTML += `<button class="number-button" id="pageNumberBtn">${i}</button>`;
        }
    };
    
    
    
}); // end