/**
 * product/details.jsp에 포함되는 js
 * 상품PK 별 옵션 목록 확인
 * 옵션 및 수량 선택 로직 처리
 * 장바구니 버튼 기능
 * 
 */

document.addEventListener('DOMContentLoaded', () => {
	
	// 옵션 버튼
	const btnOption = document.querySelector("button#btnOption");	
	// 옵션 선택 시 추가 될 영역
	const optionAddArea = document.querySelector("div#optionAddArea");

	// 바로구매
	const btnBuyNow = document.querySelector("button#btnBuyNow");

	// 장바구니 버튼
	const btnCart = document.getElementById("btnCart");
	// 장바구니 모달
	const cartModal = new bootstrap.Modal('div#toCartModal',{backdrop: true}); 
	// 로그인 모달.
	const loginModal = new bootstrap.Modal('div#toLoginModal',{backdrop: true}); 
	
	// 옵션 버튼 클릭 시 실행
	btnOption.addEventListener("click", getOptionList);

	// 장바구니 버튼 클릭 시 실행 
	btnCart.addEventListener("click", addToCart);

	// 바로구매
	btnBuyNow.addEventListener("click", buyNow);

 	/*----------   ★ 옵션/장바구니 기능들 ★   ---------- */
 	
	// 바로구매
	async function buyNow(){
		const cartItems = addedCartItemList();
		console.log("buyNow()=",cartItems);	
		if(cartItems.length === 0){
			alert("상품을 선택해주세요!");
			return;
		}
		try{
		const {data : uri} = await axios.post("../order/checkout",cartItems);
		location.href = uri;
		} catch(error){
			console.log(error);
		}
	}	
	// 장바구니 담기
	async function addToCart(){	
		const cartItems = addedCartItemList();
		console.log("addToCart()=",cartItems);

		if(cartItems.length === 0){
			alert("상품을 선택해주세요!");
			return;
		}
			
		try {
			const response = await axios.post("../cart/add",cartItems);
			const result = response.data;
			
			switch(result){
				case "overStock":
					alert("재고보다 많은 수량을 담으실 수 없습니다!");
					break;
				case "overCount":
					alert("장바구니에 있는 상품이며 \n옵션별 최대 10개까지 구매할 수 있습니다!");
					break;
				case "notLogin":
					loginModal.show();
					break;
				case "add":
					cartModal.show();
					break;
			}

		} catch(error){
			console.log(error);
		}
		
	}
		
	// 추가 된 상품 리스트
	function addedCartItemList(){
		console.log("addedCartItemList()");
		let cartItems =[];
		const addedOptions = optionAddArea.querySelectorAll("div.option-card");

		for(let added of addedOptions){
			let optionFk = added.getAttribute("data-id");
			let count = added.querySelector("input#count").value;

			cartItems.push({"option_fk": parseInt(optionFk), "count": parseInt(count)});
			
		}		
		return cartItems;
	}

	// 상품별 옵션 리스트
	async function getOptionList() {
		const productPk = document.querySelector("input#productPk").value;
		const uri = `option/all/${productPk}`;
		const response = await axios.get(uri);

		makeOptionListElements(response.data);
	}
	function makeOptionListElements(data) {
		const optionList = document.querySelector("ul#optionList");

		let htmlStr = "";

		for (let option of data) {
			htmlStr += `
			 	<li class = "optionItem">
			 		<div class="dropdown-item" data-id="${option.option_Pk}" style ="cursor: pointer">
			 			 ${option.option_Name} ${option.price} - 재고: ${option.stock}
		 			</div>
	 			</li>`
		}
		optionList.innerHTML = htmlStr;

		const optionItems = document.querySelectorAll("div.dropdown-item");

		for (let item of optionItems) {
			item.addEventListener("click", clickOption);
		}
	} 	
	
	// 옵션 상품 선택 시 실행
 	async function clickOption(e) {
        const optionPk = e.target.getAttribute("data-id");
        const uri = `option/${optionPk}`;
        const response = await axios.get(uri);
        const option = response.data;

		if(option.stock <= 0){
			alert("현재 해당 옵션은 재고가 부족합니다!")
			return;
		}	
	
		paintAddOption(option);

    }

	// 옵션 상품 선택 시 옵션 카드 추가
    function paintAddOption(option) {
        
        // 이미 추가 된 옵션이 있다면 수량 증가
		if(optionAddArea.querySelector("div.option-card") != null){
			const addedOptions = optionAddArea.querySelectorAll("div.option-card");
			for(let added of addedOptions){	
				const addedOptionPk = added.getAttribute("data-id");
				if(addedOptionPk == option.optionPk){	
					pushPlusBtn(added, option);
					return;
				}
			}	
		}

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
		updateTotalPrice(option.price);
        
        const btnClose = optionCard.querySelector("button.btn-close");
        btnClose.addEventListener("click", () => deleteAddOption(optionCard, option));
        
        const btnMinus = optionCard.querySelector("#btnMinus");
		btnMinus.addEventListener("click",() => pushMinusBtn(optionCard, option));

   		const btnPlus = optionCard.querySelector("#btnPlus");
		btnPlus.addEventListener("click",() => pushPlusBtn(optionCard, option));
        
    } 
	
	// 추가된 옵션 삭제
    function deleteAddOption(optionCard, option) {
		let count = optionCard.querySelector("input#count").value;
        optionCard.remove();
		updateTotalPrice(-option.price * count);
    }
	
	// 수량 - 버튼
	function pushMinusBtn(optionCard, option){
		let count = optionCard.querySelector("input#count");
		let currentCount = parseInt(count.value);
		if(currentCount == 1) {
			alert("1개 이상부터 구매할 수 있습니다!")
			return;
		}
		currentCount += -1;
		count.setAttribute("value", currentCount);

		updateTotalPrice(-option.price);
	}
	
	// 수량 + 버튼
	function pushPlusBtn(optionCard, option){
		let count = optionCard.querySelector("input#count");
		let currentCount = parseInt(count.value);

		if(currentCount >= option.stock && option.stock <= 10) {
			alert(`현재 구매 가능한 수량은 ${option.stock}개 입니다! `)
			return;
		} else if(currentCount >= 10 ) {
			alert(`옵션별 최대 10개까지 구매할 수 있습니다!`)
			return;			
		} 
		currentCount += 1;
		count.setAttribute("value", currentCount);
		
		updateTotalPrice(option.price);
	}
	
	// 총 상품 금액
	function updateTotalPrice(price) {
		let totalPrice = document.querySelector("span#totalPrice");
		let currentTotal = parseInt(totalPrice.innerText.replace(/[^\d.-]/g, '')) || 0;
		currentTotal += price;
		totalPrice.innerText = currentTotal.toLocaleString('ko-KR') + "원";
	}	

}); // end document.addEventListener