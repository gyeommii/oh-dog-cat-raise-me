/**
 * product/details.jsp에 포함되는 js
 * 상품PK 별 옵션 목록 확인
 * 옵션 및 수량 선택 기능
 * 
 */

document.addEventListener('DOMContentLoaded', () => {
	
	// 옵션 선택 버튼
	const btnOption = document.querySelector("button#btnOption");
	btnOption.addEventListener("click", getOptionList);


	async function getOptionList() {
		const productPk = document.querySelector("input#productPk").value;
		const uri = `option/all/${productPk}`;
		const response = await axios.get(uri);

		makeOptionListElements(response.data);
	}

	function makeOptionListElements(data) {
		// 옵션 리스트 목록
		const optionList = document.querySelector("ul#optionList");

		let htmlStr = "";

		for (let option of data) {
			htmlStr += `
			 	<li class = "optionItem">
			 		<div class="dropdown-item" data-id="${option.option_Pk}" data-stock="${option.stock}" style ="cursor: pointer">
			 			 ${option.option_Name} ${option.price} - 재고: ${option.stock}
		 			</div>
	 			</li>`
		}
		optionList.innerHTML = htmlStr;

		// 옵션 리스트의 아이템들을 저장
		const optionItems = document.querySelectorAll("div.dropdown-item");

		for (let item of optionItems) {
			item.addEventListener("click", clickOption);
		}
	} // end makeOptionListElements()

	
 	async function clickOption(e) {
		const stock = e.target.getAttribute("data-stock");
		if(stock <= 0){
			alert("현재 해당 옵션은 재고가 부족합니다!")
			return;
		}

        const optionPk = e.target.getAttribute("data-id");
        const uri = `option/${optionPk}`;
        const response = await axios.get(uri);
        const option = response.data;
        paintAddOption(option);
    }

    function paintAddOption(option) {
        const optionAddArea = document.querySelector("div#optionAddArea");
        const optionCard = document.createElement('div');
        optionCard.classList.add("card", "card-body", "bg-light", "mb-2", "option-card");
        optionCard.setAttribute("data-id", option.optionPk);

        optionCard.innerHTML = `
			    <div row class="d-flex justify-content-between align-items-center">
			        <div class="col-8">
			            <p class="card-text fw-semibold" style="font-size: 1em;">${option.optionName}</p>
			            <div class="input-group">
			                <div class="input-group-prepend">
			                    <button class="btn btn-warning text-white fw-bold btn-sm" type="button" id="btnMinus" style="border-radius: 0; height: 30px;">-</button>
			                </div>
			                <input type="text" class="text-center" value="1" id="count" readonly style="border: 0; width: 60px; font-size: 0.8em; height: 30px;">
			                <div class="input-group-append">
			                    <button class="btn btn-warning text-white fw-bold btn-sm" type="button" id="btnPlus" style="border-radius: 0; height: 30px;">+</button>
			                </div>
			            </div>
			        </div>
			        <span class="align-text-bottom" style="font-size: 1.2em;">${option.price}</span>
			        <button type="button" class="btn-close" aria-label="Close"></button>
			    </div>
		`;

        optionAddArea.appendChild(optionCard);
        
        const btnClose = optionCard.querySelector("button.btn-close");
        btnClose.addEventListener("click", () => deleteAddOption(optionCard));
        
        updateTotalPrice(option.price);
        
        const btnMinus = optionCard.querySelector("#btnMinus");
   		const btnPlus = optionCard.querySelector("#btnPlus");
        
    } // end paintAddOption()


    function deleteAddOption(optionCard) {
        optionCard.remove();
    }
    function updateTotalPrice(price) {
	let totalPrice = document.querySelector("span#totalPrice");
    let currentTotal = parseInt(totalPrice.innerText.replace(/[^\d.-]/g, '')) || 0;
    currentTotal += price;
    totalPrice.innerText = currentTotal.toLocaleString('ko-KR') + "원";
/*    let totalPrice = document.querySelector("span#totalPrice");
    let currentTotal = parseInt(totalPrice.innerText);
    currentTotal += price;
    totalPrice.innerText = currentTotal;*/
}
    
    

}); // end document.addEventListener