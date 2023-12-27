const pointToUse = document.getElementById("pointToUse"); // floating 박스 적립금 사용
const pointInput = document.getElementById("point-to-use-input"); // 입력 박스
const priceToPay = document.getElementById("priceToPay"); // priceToPay 플롯팅 박스 최종 결제할 금액
const totalPrice = document.getElementById("totalPrice"); // 합산 가격
const reservedPoint = document.getElementById("reservedPoint"); // 잔여 포인트 div
const pointCanUse = document.getElementById("pointCanUse"); // 잔여 포인트 div
const applyPointBtn = document.getElementById("applyPointBtn");

const NUM_REG = /^[0-9]*$/;

pointInput.addEventListener("keyup", () => {
  if (!NUM_REG.test(pointInput.value)) {
    pointInput.value = pointInput.value.slice(0, pointInput.value.length - 1);
  }

  console.log("pointInput.value={}", pointCanUse);

  if (getParsedNumber(pointCanUse.innerHTML) < parseInt(pointInput.value)) {
    console.log("parseInt(pointCanUse)" + getParsedNumber(pointCanUse.innerHTML))
    alert(`사용하실 수 있는 최대 포인트는 최대 "` + pointCanUse.innerHTML + `원" 입니다.`);
    pointInput.value = getParsedNumber(pointCanUse.innerHTML);
    applyPoint();
  }

  if (parseInt(pointInput.value) > getParsedNumber(totalPrice.innerHTML)) {
    pointInput.value = getParsedNumber(totalPrice.innerHTML);
    alert("포인트 사용 금액은 주문 금액보다 작아야 합니다.");
    applyPoint();
  }

});

function getParsedNumber(str) {
  return parseInt(str.split(",").join(""));
}

applyPointBtn.addEventListener("click", applyPoint)

function applyPoint() {
  pointToUse.innerHTML = parseInt(pointInput.value).toLocaleString("ko-KR");

  priceToPay.innerHTML = (getParsedNumber(totalPrice.innerHTML) - parseInt(
      pointInput.value)).toLocaleString("ko-KR");
  reservedPoint.innerHTML = (getParsedNumber(pointCanUse.innerHTML) - parseInt(
      pointInput.value)).toLocaleString("ko-KR");
}

document.getElementById("mutongjangBtn").addEventListener("click", () => {
  const accountWindow = document.getElementById("account-window");
  accountWindow.classList.remove("d-none");

  const accountWindowRect = accountWindow.getBoundingClientRect();
  window.scrollTo(accountWindowRect.left, accountWindowRect.top);
})
