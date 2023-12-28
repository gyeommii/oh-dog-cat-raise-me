/**
 * cart/list.jsp에 포함되는 js
 */

document.addEventListener("DOMContentLoaded",() => {
	// 펫타입별 장바구니 영역 및 상품이 있는 경우 펫타입별 리스트로 들어갈 자리
    const dogCart = document.querySelector("div#dogCart");
	const dogItemList = document.querySelector("ul#dogItemList");
    const catCart = document.querySelector("div#catCart");
	const catItemList = document.querySelector("ul#catItemList");
    const emptyCart = document.querySelector("div#emptyCart");

	// 장바구니에 상품이 있는 경우에만 보여줄 영역
	const checkedDeleteArea = document.querySelector("div#checkedDeleteArea");
	const amountArea = document.querySelector("div#amountArea");
	const orderArea = document.querySelector("div#orderArea");

	// 펫타입별 전체선택 체크박스
	const allDogCheckBox = document.querySelector("input#allDogItemCheck");
	const allCatCheckBox = document.querySelector("input#allCatItemCheck");

	// 선택상품 삭제 버튼
	const btnCheckedDelete = document.querySelector("button#btnCheckedDelete");
	
	// 결제 금액 부분
	const amountValue = document.querySelector("span#amountValue");
	const paymentValue = document.querySelector("span#paymentValue");
	const totalProductCount = document.querySelector("span#totalProductCount");

	// 옵션 변경 
	const optionChangeModal = new bootstrap.Modal("div#optionChangeModal",{backdrop: true});
	const btnApplyOption = document.querySelector("a#btnApplyOption");
	let beforeCount = 0;
	let beforeOptionFk = 0;
	

	getAllCartList();
	allDogCheckBox.addEventListener("click", () => toggleAllItems(allDogCheckBox, dogItemList));
	allCatCheckBox.addEventListener("click", () => toggleAllItems(allCatCheckBox, catItemList));
	btnCheckedDelete.addEventListener("click", deleteCheckedItems);
	


	/* -------------------------------------------------- 기능 영역 -------------------------------------------------- */

	function updatePaymentArea(){
		let currentProductCount = 0; // 총 상품 갯수
		let totalOptionCount = 0; // 총 옵션 갯수
		let currentAmountValue = 0; // 옵션 * 상품 가격
		const checkboxes = document.querySelectorAll("input#itemChecked");
		checkboxes.forEach( checkbox => {
			if (checkbox.checked && checkbox.value.trim() !== "") {
				const priceAttribute = checkbox.getAttribute("data-price");
				const countAttribute  = checkbox.getAttribute("data-count");
				currentProductCount += 1;
				totalOptionCount += parseInt(countAttribute);
				currentAmountValue += parseInt(priceAttribute) * parseInt(countAttribute);
			}
		});
		totalProductCount.innerText = currentProductCount;
		amountValue.innerText = currentAmountValue.toLocaleString('ko-KR');
		paymentValue.innerText = currentAmountValue.toLocaleString('ko-KR');
	}

	async function getAllCartList() {
		try {
			const uri = `list/all/items`;
			const response = await axios.get(uri);

			dogCart.classList.add('d-none');
			catCart.classList.add('d-none');
			checkedDeleteArea.classList.add("d-none");
			amountArea.classList.add("d-none");			
			orderArea.classList.add("d-none");
			dogItemList.innerHTML = "";
			catItemList.innerHTML = "";

			paintCartList(response.data);
		} catch (error) {
			console.error("장바구니 목록을 불러오는 중 오류가 발생했습니다:", error);
		}
    }

    function paintCartList(cartList) {
        console.log("카트 상품 갯수 = ",cartList.length);

        if (cartList.length === 0) {
            emptyCart.classList.remove("d-none");
			return;
        }
		checkedDeleteArea.classList.remove("d-none");
		amountArea.classList.remove("d-none");			
		orderArea.classList.remove("d-none");

		for(let cartItem of cartList){
			if(cartItem.pet_type === 1){
				makeCartListElements(cartItem, dogItemList);
				dogCart.classList.remove("d-none");			
			} else{
				makeCartListElements(cartItem, catItemList);
				catCart.classList.remove("d-none");
			}
		}	
		updatePaymentArea();
    }

	function makeCartListElements(cartItem, parentList){
		const itemList = document.createElement("li");
		const totalItemPrice = cartItem.price * cartItem.count;
		const detailsPage = `/ohdogcat/product/details?productPk=${cartItem.product_pk}`;
		
		itemList.classList.add("list-group-item", "mt-3", "pb-4", "text-center");
		itemList.innerHTML = `
				<div class="row fw-semibold">
					<!-- 1. 상품 영역  -->
					<div class="col-8">
						<div class="row">
							<div class="col-1 form-check fs-5 align-self-center" style="width: auto;">
								<input class="form-check-input" type="checkbox" value="${cartItem.option_fk}"
								data-price="${cartItem.price}" data-count="${cartItem.count}" id="itemChecked" checked></div>
							<!-- 상품 사진 -->
							<div class="col-2">
                        		<a href="${detailsPage}"><img src="${cartItem.img_url}" class="img-fluid rounded d-block" alt="product Img" style="width: 100%;"></a>
							</div>
							<!-- 상품 이름-->
							<div class="col-9 align-self-center fw-normal" style="text-align: left;">
								<div class="row">
									<div class="col">
										<a href="${detailsPage}"><div class="fw-semibold" style="font-size: 17px;">${cartItem.product_name}</div></a>
										<div>${cartItem.min_price.toLocaleString('ko-KR')}원</div>
										<div class="fs-6 pt-1 text-black-50">옵션 : [${cartItem.option_name}] ${cartItem.price.toLocaleString('ko-KR')}원</div>
										<!-- 옵션 변경 -->
										<button class="btn btn-warning mt-2 btn-sm text-white" data-productpk="${cartItem.product_pk}"
										id="btnChangeOption" type="button" style="border-radius: 8px; background-color: #ffc107 !important;">옵션변경</button>                                       
									</div>
								    <div class="col-1 align-self-center">      
        	                            <i class="btn bi bi-x-square" id="btnDelete" style="font-size: 24px; color: rgb(207, 207, 207);" ></i>
    	                            </div>
								</div>
							</div>
						</div>
					</div>
					<!-- 2. 수량 영역 -->
					<div class="col-2 align-self-center">
						<div class="input-group justify-content-center">
							<div class="input-group-prepend">
								<button class="btn btn-warning text-white fw-bold btn-sm" type="button" id="btnMinus" style="border-radius: 0; height: 30px;">-</button>
							</div>
							<input type="text" class="text-center" value="${cartItem.count}" id="count" readonly style="border: 0; width: 40px; font-size: 0.8em; height: 30px;">
							<div class="input-group-append">
								<button class="btn btn-warning text-white fw-bold btn-sm" type="button" id="btnPlus" style="border-radius: 0; height: 30px;">+</button>
							</div>
						</div>
					</div>

					<!-- 3. 주문 금액 영역 -->
					<div class="col-2 align-self-center">
						<div id = "totalItemPrice" style="font-size: 18px;">${totalItemPrice.toLocaleString('ko-KR')}원</div>
						<div class="fs-6 fw-normal text-black-50">무료배송</div>
					</div>
				</div>      
		`;
		parentList.appendChild(itemList);

		const btnDelete = itemList.querySelector("i#btnDelete");
		btnDelete.addEventListener("click", () => deleteCartItem(cartItem.option_fk, itemList));

		const btnMinus = itemList.querySelector("button#btnMinus");
		btnMinus.addEventListener("click",() => pushMinusBtn(itemList, cartItem));

   		const btnPlus = itemList.querySelector("button#btnPlus");
		btnPlus.addEventListener("click",() => pushPlusBtn(itemList, cartItem));

		const btnChangeOption = itemList.querySelector("button#btnChangeOption");
		btnChangeOption.addEventListener("click",() => changeOption(itemList,cartItem));    
		
		const inputChkBox = itemList.querySelector('input#itemChecked');
		inputChkBox.addEventListener("click", () => updatePaymentArea());

	}

	async function pushMinusBtn(itemList, item){
		let count = itemList.querySelector("input#count");
		const inputChkBox = itemList.querySelector('input#itemChecked');

		let currentCount = parseInt(count.value);
		if(currentCount == 1) {
			alert("1개 이상부터 구매할 수 있습니다!")
			return;
		}
		currentCount += -1;
		count.setAttribute("value", currentCount);
		inputChkBox.setAttribute('data-count', currentCount);

		updateTotalItemPrice(itemList,-item.price);
		updatePaymentArea();

		const update = {count: currentCount, option_fk: item.option_fk};
		console.log(update);
		try{
			const uri = `list/update/${item.option_fk}`;
			const response = await axios.put(uri,update);
		} catch(error){
			console.log("장바구니 업데이트 실패",error);
		}

	}

	async function pushPlusBtn(itemList, item){
		let count = itemList.querySelector("input#count");
		const inputChkBox = itemList.querySelector('input#itemChecked');

		let currentCount = parseInt(count.value);

		if(currentCount >= item.stock && item.stock <= 10) {
			alert(`현재 구매 가능한 수량은 ${item.stock}개 입니다! `)
			return;
		} else if(currentCount >= 10 ) {
			alert(`옵션별 최대 10개까지 구매할 수 있습니다!`)
			return;			
		} 
		currentCount += 1;
		count.setAttribute("value", currentCount);
		inputChkBox.setAttribute('data-count', currentCount);

		updateTotalItemPrice(itemList, item.price);
		updatePaymentArea();

		const update = {count: currentCount, option_fk: item.option_fk};
		console.log(update);
		try{
			const uri = `list/update/${item.option_fk}`;
			const response = await axios.put(uri,update);
			const result = response.data;
			console.log("result",result);
			// 아직 쓸모 없는 코드..
			// switch(result){
			// 	case "overStock":
			// 		alert("재고보다 많은 수량을 담으실 수 없습니다!");
			// 		break;
			// 	case "overCount":
			// 		alert("옵션별 최대 10개까지 구매할 수 있습니다!");
			// 		break;
			// }

		} catch(error){
			console.log("장바구니 업데이트 실패",error);
		}
	}

	function updateTotalItemPrice(itemList, price) {
		let totalItemPrice = itemList.querySelector("div#totalItemPrice");
		let currentTotal = parseInt(totalItemPrice.innerText.replace(/[^\d.-]/g, '')) || 0;
		currentTotal += price;
		totalItemPrice.innerText = currentTotal.toLocaleString('ko-KR') + "원";
	}

	async function deleteCartItem(option_fk){
		const uri = `list/delete/${option_fk}`;
		try{
			const response = await axios.delete(uri);
			if (response.data === 1) {
				getAllCartList();
			}
		} catch(error){
			console.log(error);
		}
	}

	async function deleteCheckedItems(){
		const checkboxes = document.querySelectorAll("input#itemChecked");
		const checkedItems = [];
		checkboxes.forEach( checkbox => {
			if (checkbox.checked && checkbox.value.trim() !== "") {
				checkedItems.push(checkbox.value);
			}
		});
		checkedItems.forEach( item => { 
			deleteCartItem(item);
		});
	}

	function changeOption(itemList, cartItem){
		beforeCount = itemList.querySelector("input#count").value;
		beforeOptionFk = cartItem.option_fk;
		const btnOption = document.querySelector("button#btnOption");
		btnOption.innerText = "상품 옵션을 선택하세요";
		btnOption.addEventListener("click",() => getOptionList(cartItem));
		optionChangeModal.show();
	}
	
	async function getOptionList(cartItem){
		const uri = `../product/option/all/${cartItem.product_pk}`;
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
	
	async function clickOption(e) {
        const optionPk = e.target.getAttribute("data-id");
        const uri = `../product/option/${optionPk}`;
        const response = await axios.get(uri);
        const option = response.data;

		if(option.stock <= 0){
			alert("현재 해당 옵션은 재고가 부족합니다!")
			return;
		}
		btnOption.innerText = `변경 옵션 : [${option.optionName}] ${option.price.toLocaleString('ko-KR')}원`;	

		btnApplyOption.addEventListener("click",changeCartItem(option.optionPk));
    }

	async function changeCartItem (afterOptionFk){
		console.log("changeCartItem()", `beforeOptionFk=${beforeOptionFk}, beforeCount=${beforeCount}`);
		const uri = `list/update/${beforeOptionFk}/${afterOptionFk}`;
        const response = await axios.put(uri);
		console.log(response.data);
	}


	function toggleAllItems(checkbox, itemList) {
		const list = itemList.querySelectorAll("li");
		for (let li of list) {
			li.querySelector("input#itemChecked").checked = checkbox.checked;
		}
		updatePaymentArea()
	}



}); // end document.addEventListener
