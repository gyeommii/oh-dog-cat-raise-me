const distinguishCode = `imp24187885`;
const REST_API_KEY = `6075557175583251`;
const REST_API_SECRET = `CktVTCKBSlu8kS2gVkwLGrmMriWOGz71p3JDqQoy7uxq0viw5Izm2tFufBvuNIBiTL1VnoZ32KhtLPdS`;
let merchant_uid = `ORD20180131-${crypto.randomUUID()}`;

IMP.init(distinguishCode);

function requestPay() {
  merchant_uid = `ORD20180131-${crypto.randomUUID()}`;

  IMP.request_pay({
    pg: "kakaopay",
    pay_method: "card",
    merchant_uid,   // 주문번호
    name: document.getElementById("orderName").value,
    amount: getParsedNumber(priceToPay.innerHTML),                         // 숫자 타입
    buyer_name: document.getElementById("member_id").innerHTML.trim(),
    buyer_email: document.getElementById("member_email").innerHTML.trim(),
    buyer_tel: document.getElementById("member_phone").innerHTML.trim(),
  }, function (rsp) {
    if (rsp.success == true) {
      pointInput.readOnly = true;
      applyPointBtn.disabled = true;
      document.getElementById("success-complete-card").classList.remove(
          "d-none");
    }
  });

  document.getElementById("account-window").classList.add("d-none");
}