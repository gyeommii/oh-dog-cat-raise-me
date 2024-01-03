/**
 * 모든view .jsp에 포함되는 js
 * 내비게이션 장바구니 수량 표시용
 */
document.addEventListener('DOMContentLoaded', () => {
    updateCartQuantity(); 
})

async function updateCartQuantity() {
    console.log("updateCartQuantity()");
    const navCartCount = document.getElementById("navCartCount");

    if (navCartCount) {
        try {
            const count = await getCartCount();
            navCartCount.innerText = count;
            navCartCount.classList.remove("d-none");
        } catch (error) {
            console.error(error);
        }
    }
}


async function getCartCount(){
    try {
        const uri = `/ohdogcat/cart/list/all/items`;
        const {data: items}= await axios.get(uri);
        return items.length;
    } catch (error) {
        console.error(error);
    }
}
