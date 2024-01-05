const optionDivList = document.querySelectorAll(".option-info-to-order");
const optionList = [];
const PAYMENT_METHOD = Object.freeze(
    {KAKAOPAY: "카카오페이", BANK_TRANSFER: "무통장입금"});

const PAYMENT_SUCCESS = Object.freeze(
    {PENDING: "pending", SUCCESS: "success", BEFORE: "before"});
const today = new Date();

optionDivList.forEach(el => {
  const option = {
    option_fk: parseInt(el.getAttribute("data-optionfk")),
    count: parseInt(el.getAttribute("data-count"))
  }

  optionList.push(option);
})

const addrSelectComp = document.getElementById("addr-selected");
addrSelectComp.addEventListener("change", changeAddressSelect);

console.log("addrSelectComp.selectedIndex :" + addrSelectComp.options[addrSelectComp.selectedIndex]);

const orderInfoToSubmit = {
  totalPrice: getParsedNumber(totalPrice.innerHTML),
  pointUsed: getParsedNumber(pointToUse.innerHTML),
  optionList,
  addressFk: addrSelectComp.selectedIndex >= 0
      ? addrSelectComp.options[addrSelectComp.selectedIndex].value : null,
  orderName: document.getElementById("orderName").value,
  paidPrice: 0,
  payMethod: undefined,
  paymentSuccess: PAYMENT_SUCCESS.BEFORE,
  merchantUid: `ORD${today.getFullYear()}${today.getMonth()}${today.getDate()}0131-${Date.now()}`,
  orderType: document.getElementById("orderType").value
};

function getParsedNumber(str) {
  return parseInt(str.split(",").join(""));
}

function changeAddressSelect() {
  // select element에서 선택된 option의 value가 저장된다.
  const selectedAddr = addrSelectComp.options[addrSelectComp.selectedIndex].value;
  orderInfoToSubmit.addressFk = getParsedNumber(selectedAddr);

  checkOrderCanCreate();
  return getParsedNumber(selectedAddr);
}

function checkOrderCanCreate() {

  if (!orderInfoToSubmit.addressFk) {
    alert("배송받을 주소를 추가해주세요!");
  }

  if (!orderInfoToSubmit.orderName
      || !orderInfoToSubmit.payMethod
      || !orderInfoToSubmit.addressFk
      || !orderInfoToSubmit.optionList.length
      || (orderInfoToSubmit.paidPrice !== 0 && !orderInfoToSubmit.paidPrice)
      || !orderInfoToSubmit.paymentSuccess
      || (orderInfoToSubmit.pointUsed !== 0 && !orderInfoToSubmit.pointUsed)
      || (orderInfoToSubmit.totalPrice !== 0
          && !orderInfoToSubmit.totalPrice)) {
    return;
  }

  if (orderInfoToSubmit.payMethod === PAYMENT_METHOD.KAKAOPAY) {
    document.getElementById("order-submit-btn").disabled = false;
    return;
  }

  if (orderInfoToSubmit.payMethod === PAYMENT_METHOD.BANK_TRANSFER) {
    document.getElementById("order-submit-btn").disabled = false;
    return;
  }

  document.getElementById("order-submit-btn").disabled = true;
}


