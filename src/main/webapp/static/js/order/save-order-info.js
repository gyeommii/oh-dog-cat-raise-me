const optionDivList = document.querySelectorAll(".option-info-to-order");
const optionList = [];
const PAYMENT_METHOD = Object.freeze(
    {KAKAOPAY: "kakopay", BANK_TRANSFER: "bank_transfer"});

const PAYMENT_SUCCESS = Object.freeze({PENDING: "pending", SUCCESS: "success", BEFORE: "before"});

let merchantUid = `ORD20180131-${crypto.randomUUID()}`;



optionDivList.forEach(el => {
  const option = {
    option_fk: parseInt(el.getAttribute("data-optionfk")),
    count: parseInt(el.getAttribute("data-count"))
  }

  optionList.push(option);
})

const orderInfoToSubmit = {
  totalPrice: getParsedNumber(totalPrice.innerHTML), 
  pointUsed: getParsedNumber(pointToUse.innerHTML), 
  optionList, 
  addressFk: null,
  orderName: document.getElementById("orderName").value,
  paidPrice: 0, 
  payMethod: undefined,
  paymentSuccess: PAYMENT_SUCCESS.BEFORE,
  merchantUid,
  orderType: "c"
};

function getParsedNumber(str) {
  return parseInt(str.split(",").join(""));
}

const addrSelectComp = document.getElementById("addr-selected");
addrSelectComp.addEventListener("change", changeLangSelect);

function changeLangSelect() {
  const addrSelectComp = document.getElementById("addr-selected");
  // select element에서 선택된 option의 value가 저장된다.
  const selectedAddr = addrSelectComp.options[addrSelectComp.selectedIndex].value;
  orderInfoToSubmit.addressFk = getParsedNumber(selectedAddr);
  console.log("selectedAddr : ", selectedAddr)
  console.log("orderInfoToSubmit : ", orderInfoToSubmit)

  return getParsedNumber(selectedAddr);
}

orderInfoToSubmit.addressFk = changeLangSelect();

function checkOrderCanCreate() {
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
  console.log("오잉")
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

