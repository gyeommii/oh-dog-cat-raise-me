const purchasePk = document.getElementById("purchasePk");
const cancelPurchaseBtn = document.getElementById("cancelPurchaseBtn");


cancelPurchaseBtn.addEventListener("click", async () => {
  const confirmResult = confirm(
      "해당 주문을 취소하겠습니까? <span class='text-warning'>취소 요청은 되돌릴 수 없습니다.</span>")
  console.log(confirmResult + "confirm");
  if (!confirmResult) {
    return;
  }

  console.log("purchasePk=" + purchasePk);

  const {data: result} = await axios.delete(`./${purchasePk}`);

  console.log("result=" + result);

  if (result === "canceled") {
    alert("주문 취소가 완료되었습니다.");
  } else if (result === "cancel_requested") {
    alert("취소 요청이 완료되었습니다.\n배송 중이거나 배송완료된 상품은 상담원과의 상담 후 가능합니다.");
  } else if (result === "already_canceled_or_cancel_requested") {
    alert("이미 삭제 요청이 완료된 ");
  }

  location.reload();
})
