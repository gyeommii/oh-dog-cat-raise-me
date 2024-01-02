const purchaseListItems = document.querySelectorAll(".purchase-list-item");
let page = 1;

for (let purchaseList of purchaseListItems) {
  const deleteBtn = purchaseList.querySelector("#delete-btn");
  const purchasePk = purchaseList.getAttribute("data-id");
  deleteBtn.addEventListener("click", () => cancelPurchase(purchasePk))
}

async function cancelPurchase(purchasePk) {

  const confirmResult = confirm(
      "해당 주문을 취소하겠습니까? <span class='text-warning'>취소 요청은 되돌릴 수 없습니다.</span>")
  console.log(confirmResult + "confirm");
  if (!confirmResult) {
    return;
  }

  console.log("purchasePk=" + purchasePk);

  const {data: result} = await axios.delete(`../order/${purchasePk}`);

  console.log("result=" + result);

  if (result === "canceled") {
    alert("삭제가 완료되었습니다.");
  } else if (result === "cancel_requested") {
    alert("삭제 요청이 완료되었습니다.");
  } else if (result === "already_canceled_or_cancel_requested") {
    alert("이미 삭제 요청이 완료된 ");
  }

}

async function getPageCount () {
  const {data} = axios.get('./count');


}

