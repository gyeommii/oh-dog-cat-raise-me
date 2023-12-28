const distinguishCode = `imp24187885`;

IMP.init(distinguishCode);

document.getElementById("order-submit-btn").addEventListener("click", requestPay);

function requestPay() {

  if (orderInfoToSubmit.payMethod === PAYMENT_METHOD.BANK_TRANSFER) {
    // 무통장 입금 시 flow
    sumitOrder(orderInfoToSubmit);
    return;
  }

  orderInfoToSubmit.payMethod = PAYMENT_METHOD.KAKAOPAY;

  console.log()

  IMP.request_pay({
    pg: "kakaopay",
    pay_method: "card",
    merchantUid,   // 주문번호
    name: document.getElementById("orderName").value,
    amount: getParsedNumber(priceToPay.innerHTML),                         // 숫자 타입
    buyer_name: document.getElementById("member_id").innerHTML.trim(),
    buyer_email: document.getElementById("member_email").innerHTML.trim(),
    buyer_tel: document.getElementById("member_phone").innerHTML.trim(),
  }, function (rsp) {
    if (rsp.success === true) {
      document.getElementById("success-complete-card").classList.remove(
          "d-none");

      orderInfoToSubmit.paymentSuccess = PAYMENT_SUCCESS.SUCCESS;

      sumitOrder(orderInfoToSubmit);
    }
  });

  document.getElementById("account-window").classList.add("d-none");
}

function sumitOrder (data) {
  axios.post ("./", data).then (res=> {
    console.log(res.data)
    location.href = res.data;
  });
}