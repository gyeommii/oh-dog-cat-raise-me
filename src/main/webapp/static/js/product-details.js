/**
 * product/details.jsp에 포함되는 js
 * 상품PK 별 옵션 리스트 처리
 */

 document.addEventListener('DOMContentLoaded',()=>{
	 
	 const btnOption = document.querySelector("button#btnOption");
	 const optionAddArea = document.querySelector("div#optionAddArea");
	 
	 btnOption.addEventListener("click", getOptionList);
	 
	 async function getOptionList(){
		 const productPk = document.querySelector("input#productPk").value;
		 const uri = `optionlist/${productPk}`;
		 const response = await axios.get(uri);
			 
		makeOptionListElements(response.data);
	 }
	 
	 function makeOptionListElements(data){
		 // 옵션 리스트 추가 할 영역
		 const optionList = document.querySelector("ul#optionList");
		 
		 let htmlStr = "";
		 
		 for(let option of data){
			 htmlStr += `
			 	<li><a class="dropdown-item" style ="cursor: pointer">
			 	${option.option_Name}    ${option.price} / 재고: ${option.stock}</a></li>` 
		 }
		 optionList.innerHTML = htmlStr;
	 }
	 
	 
 }); // end document.addEventListener