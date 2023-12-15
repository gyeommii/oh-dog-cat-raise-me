/**
 * home.jsp에 포함되는 js
 * 강아지/고양이 버튼 선택에 따라 메인 아이템 다르게 보여줌
 */

 document.addEventListener("DOMContentLoaded",()=>{
	 
	 /* 신상품, 베스트 타이틀 이름 */
	 const newTitle = document.querySelector("h3#newTitle");
	 const bestTitle = document.querySelector("h3#bestTitle");
	 
	 /* 강아지 아이템 리스트 */
	 const dogNewList = document.querySelector("div#dogNewList");
	 const dogBestList = document.querySelector("div#dogBestList");
	 
	 /* 고양이 아이템 리스트 */
	 const catNewList = document.querySelector("div#catNewList");
	 const catBestList = document.querySelector("div#catBestList");
	 
	 const btnDog = document.querySelector("input#btnradio1");
	 const btnCat = document.querySelector("input#btnradio2");
	 
	 
	 btnCat.addEventListener("click", ChangeMainListToCat);
	 btnDog.addEventListener("click", ChangeMainListToDog);
	 
	 /* 고양이 아이템들로 불러오기! */
	 function ChangeMainListToCat(){
		newTitle.innerText ="🐱냥 신상품";
		bestTitle.innerText ="🐱냥 베스트";
		
		dogNewList.classList.add("d-none");
		dogBestList.classList.add("d-none");
		
		catNewList.classList.remove("d-none");
		catBestList.classList.remove("d-none");
	 };
	 
 	 /* 강아지 아이템들로 불러오기! */
	 function ChangeMainListToDog(){
		newTitle.innerText ="🐶멍 신상품";
		bestTitle.innerText ="🐶멍 베스트";
		
		catNewList.classList.add("d-none");
		catBestList.classList.add("d-none");
		
		dogNewList.classList.remove("d-none");
		dogBestList.classList.remove("d-none");
	 };
	 
 })