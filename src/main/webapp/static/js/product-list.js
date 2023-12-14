/**
 * home.jsp에 포함되는 js
 * 강아지/고양이 버튼 선택에 따라 메인 아이템 다르게 보여줌
 */

 document.addEventListener('DOMContentLoaded',()=>{
	 const newTitle = document.querySelector("h3#newTitle");
	 const bestTitle = document.querySelector("h3#bestTitle");
	 
	 const dogNewList = document.querySelector("div#dogNewList");
	 const catNewList = document.querySelector("div#catNewList");
	 
	 const btnDog = document.querySelector('input#btnradio1');
	 const btnCat = document.querySelector('input#btnradio2');
	 const btnDiv = document.querySelector('div.btn-group')
	 
	 console.dir(btnDiv);

	 
	 btnCat.addEventListener("click", ChangeMainListToCat)
	 
	 function ChangeMainListToCat(){
		newTitle.innerText ="🐱냥 신상품";
		bestTitle.innerText ="🐱냥 베스트";
		dogNewList.classList.add('d-none');
	 };
	 
 })