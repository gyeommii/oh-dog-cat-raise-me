document.getElementById("cancelPurchaseBtn").addEventListener("click", cancelPurchaseBtn);
document.getElementById("toListBtn").addEventListener("click", toPurchaseListPage);

function cancelPurchaseBtn() {
  const purchasePk = parseInt(document.getElementById("purchasePk").value);
//   취소 flow
}

function toPurchaseListPage() {
  location.href = "./list";
}