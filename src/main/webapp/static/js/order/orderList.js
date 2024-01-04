const purchaseListItems = document.querySelectorAll(".purchase-list-item");
let page = 1;

for (let purchaseList of purchaseListItems) {
  const deleteBtn = purchaseList.querySelector("#delete-btn");
  const confirmBtn = purchaseList.querySelector("#confirm-btn")

  const purchasePk = purchaseList.getAttribute("data-id");
  deleteBtn.addEventListener("click", () => cancelPurchase(purchasePk));
  confirmBtn.addEventListener("click", () => confirmPurchase(purchasePk));
}

async function cancelPurchase(purchasePk) {

  const confirmResult = confirm(
      "해당 주문을 취소하겠습니까? 취소 요청은 되돌릴 수 없습니다.")
  console.log(confirmResult + "confirm");
  if (!confirmResult) {
    return;
  }

  console.log("purchasePk=" + purchasePk);

  const {data: result} = await axios.delete(`../order/${purchasePk}`);

  console.log("result=" + result);

  if (result === "canceled") {
    alert("주문 취소가 완료되었습니다.");
  } else if (result === "cancel_requested") {
    alert("취소 요청이 완료되었습니다.\n배송 중이거나 배송완료된 상품은 상담원과의 상담 후 가능합니다.");
  } else if (result === "already_canceled_or_cancel_requested") {
    alert("이미 삭제 요청이 완료된 ");
  }

  location.reload();
}

async function confirmPurchase(purchasePk) {
  console.log("hihihi");

  const confirmResult = confirm("구매 확정을 하시겠습니까?");

  console.log(confirmResult + "confirm");

  if (!confirmResult) {
    return;
  }

  const {data: result} = await axios.get(`../order/confirm/${purchasePk}`);

  console.log("result", result)
  if (result === "not_owner") {
    alert("다른 집사의 구매를 확정할 수 없습니다.");

  } else if (result === "confirmed") {
    alert("구매확정되었습니다! 좋은 하루되세요!");

  } else {
    alert("구매확정에 실패하였습니다. 다시 시도해주세요");
  }

  location.reload();
}


