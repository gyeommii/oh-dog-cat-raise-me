/**
 * wishlist.jsp에 포함되는 js
 */

document.addEventListener('click', function (event) {
    if (event.target.classList.contains('btnWish')) {
        const productFk = event.target.getAttribute('data-id');
        onClickWish(productFk);
    }
});
    
    async function onClickWish(productFk){
        console.log("onClickWish()");    
        const btnWish = document.querySelector(`button#btnWish_${productFk}`);
        console.log("btnWish",btnWish);
		const uri = `../product/wish/${productFk}`;
		// 찜 선택 상태
		if(btnWish.classList.contains("bi-suit-heart-fill")){ 
			try{	
				await axios.delete(uri);
				btnWish.classList.remove("bi-suit-heart-fill");
				btnWish.classList.add("bi-suit-heart");
                location.reload(true);
			}catch(error){
				console.log(error);
			}
		}else{
			try{
				const {data:result} = await axios.post(uri);
				if(!result){alert("로그인 후 이용 가능합니다!"); return;}
				btnWish.classList.remove("bi-suit-heart");
				btnWish.classList.add("bi-suit-heart-fill");
                location.reload(true);
			}catch(error){
				console.log(error);
			}
		}
    }


