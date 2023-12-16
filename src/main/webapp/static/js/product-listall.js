/**
 * list.jsp에 포함되는 js
 * 강아지/고양이 버튼 선택에 따라 list페이지의 전체목록 아이템 다르게 보여줌(신상품기준)
 */

 document.addEventListener("DOMContentLoaded",()=>{
     
     /* 신상품, 베스트 타이틀 이름 */
     const newTitle = document.querySelector("h3#newTitle");
     
     /* 강아지 아이템 리스트 */
     const dogNewList = document.querySelector("div#dogNewList");
     
     /* 고양이 아이템 리스트 */
     const catNewList = document.querySelector("div#catNewList");
     
     const btnDog = document.querySelector("input#btnradio1");
     const btnCat = document.querySelector("input#btnradio2");
     
     
     btnCat.addEventListener("click", ChangeMainListToCat);
     btnDog.addEventListener("click", ChangeMainListToDog);
     
     /* 고양이 아이템들로 불러오기! */
     function ChangeMainListToCat(){
        newTitle.innerText ="🐱냥 상품";
        
        dogNewList.classList.add("d-none");
        
        catNewList.classList.remove("d-none");
     };
     
     /* 강아지 아이템들로 불러오기! */
     function ChangeMainListToDog(){
        newTitle.innerText ="🐶멍 상품";
        
        catNewList.classList.add("d-none");
        
        dogNewList.classList.remove("d-none");
     };
     
 })