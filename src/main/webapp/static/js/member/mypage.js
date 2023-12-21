const daumPostOpenBtn = document.getElementById("daumPostOpenBtn");
const btnFoldWrap = document.getElementById("btnFoldWrap");

daumPostOpenBtn.addEventListener("click", findAddressThrougDaumPost);
btnFoldWrap.addEventListener("click", foldDaumPostcode);

const zoneCodeInput = document.getElementById("zonecode");
const addressInput = document.getElementById("address");
const detailAddressInput = document.getElementById("detailAddress");
const recipientInput = document.getElementById("recipient");

const userInfo = {
  zonecode: zoneCodeInput.value,
  address: addressInput.value,
  detailAddress: detailAddressInput.value,
  recipient: recipientInput.value
};

const memberInfoWrapper = document.getElementById(
    "member-info-viewer-wrapper");
// 정보 뷰어
const modifyFormWrapper = document.getElementById("modify-form-wrapper");
// 수정 Div
const toModifyBtn = document.getElementById("to-modify-btn");
// 수정 버튼
const toViewInfoBtn = document.getElementById("to-view-info-btn");
// 마이페이지로 돌아가기
const addrressResetBtn = document.getElementById("address-reset-btn");
const addressUpdateBtn = document.getElementById("address-update-btn");

// 주소 리셋 버튼 => 유저의 본래 정보로 변경
addrressResetBtn.addEventListener("click", () => {
  document.getElementById("zonecode").value = userInfo.zonecode;
  document.getElementById("address").value = userInfo.address;
  document.getElementById("detailAddress").value = userInfo.detailAddress;
  document.getElementById("recipient").value = userInfo.recipient;
})

const toFocusAtViewer = document.getElementById(
    "toFocusAtViewer").getBoundingClientRect();
window.scrollTo(toFocusAtViewer.left, toFocusAtViewer.top);

const infoChangeBtn = document.getElementById("infoChangeBtn");
const inputPw = document.getElementById("inputPw");
const inputNewPw = document.getElementById("inputNewPw");
const inputNewPwCheck = document.getElementById("inputNewPwCheck");
const inputPhone = document.getElementById("inputPhone");

inputPw.addEventListener("keyup", canChangeUserInfo);
inputNewPw.addEventListener("keyup", canChangeUserInfo);
inputNewPwCheck.addEventListener("keyup", canChangeUserInfo);
inputPhone.addEventListener("keyup", canChangeUserInfo);

function canChangeUserInfo() {
  const pwReg = /^(?=.*[A-Za-z])(?=.*\d)(?!.*\s).{8,}$/;
  const phoneReg = /^(02|010)-?\d{3,4}-?\d{4}$/;

  const inputPwValid = !(inputPw.value.trim().length < 5) && pwReg.test(
      inputPw.value);
  const inputNewPwValid = !(inputNewPw.value.trim().length < 5)
      && pwReg.test(inputNewPw.value);
  const inputNewPwCheckValid = !(inputNewPwCheck.value.trim().length < 5)
      && pwReg.test(inputNewPwCheck.value);
  const inputPhoneValid = !(inputPhone.value.trim().length === 0)
      && phoneReg.test(inputPhone.value);
  const isNewPwValuesSame = inputNewPw.value.trim()
      === inputNewPwCheck.value.trim();

  infoChangeBtn.disabled = !(inputPwValid && inputPhoneValid) || !(inputPwValid
      && inputNewPwValid && inputNewPwCheckValid && isNewPwValuesSame);
}

// 수정하기 컴포넌트로 이동하는 이벤트 추가
toModifyBtn.addEventListener("click", () => {
  modifyFormWrapper.classList.remove("d-none");
  memberInfoWrapper.classList.add("d-none");

  const inputPwRect = inputPw.getBoundingClientRect();
  window.scrollTo(inputPwRect.left, inputPwRect.top);

  inputPw.focus();
})

// 뷰어 컴포넌트로 이동하는 이벤트 추가
toViewInfoBtn.addEventListener("click", () => {
  modifyFormWrapper.classList.add("d-none");
  memberInfoWrapper.classList.remove("d-none");
  window.scrollTo(toFocusAtViewer.left, toFocusAtViewer.top);

  foldDaumPostcode();
})

function checkCanSubmitAddress() {
  addressUpdateBtn.disabled = !(zoneCodeInput.value.length
      && addressInput.value.length
      && detailAddressInput.value.length && recipientInput.value.length)
}

detailAddressInput.addEventListener("keyup", checkCanSubmitAddress);
recipientInput.addEventListener("keyup", checkCanSubmitAddress);

// 배송지 업데이트
addressUpdateBtn.addEventListener("click", async () => {
  const address_pk = document.getElementById("address_pk").value;

  const data = {
    address_pk,
    zonecode: zoneCodeInput.value,
    address: addressInput.value,
    detailAddress: detailAddressInput.value,
    recipient: recipientInput.value
  };

  const url = "./member";

  const {data: result} = await axios.patch(url, data);
  console.log("result : " + result);

})