const distinguishCode = `imp24187885`;
const REST_API_KEY = `6075557175583251`;
const REST_API_SECRET = `CktVTCKBSlu8kS2gVkwLGrmMriWOGz71p3JDqQoy7uxq0viw5Izm2tFufBvuNIBiTL1VnoZ32KhtLPdS`;

IMP.init(distinguishCode);

function requestPay() {
  IMP.request_pay({
    pg: "kakaopay",
    pay_method: "card",
    merchant_uid: `ORD20180131-${crypto.randomUUID}`,   // 주문번호
    name: "노르웨이 회전 의자",
    amount: 64900,                         // 숫자 타입
    buyer_email: "gildong@gmail.com",
    buyer_name: "홍길동",
    buyer_tel: "010-4242-4242",
    buyer_addr: "서울특별시 강남구 신사동",
    buyer_postcode: "01181"
  }, function (rsp) {
    console.log(rsp);
  });
}