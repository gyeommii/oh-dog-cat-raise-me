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

const pwReg = /^(?=.*[A-Za-z])(?=.*\d)(?!.*\s).{8,}$/;
const phoneReg = /^(02|010)-?\d{3,4}-?\d{4}$/;
let inputPwValid
let inputNewPwValid = !(inputNewPw.value.trim().length < 5) && pwReg.test(
    inputNewPw.value) && (inputPw.value !== inputNewPw.value);
let inputNewPwCheckValid = !(inputNewPwCheck.value.trim().length < 5)
    && pwReg.test(inputNewPwCheck.value);
let inputPhoneValid = !(inputPhone.value.trim().length === 0) && phoneReg.test(
    inputPhone.value);
inputPw.addEventListener("keyup", () => {
  const pwDesc = document.getElementById("pwDesc");

  inputPwValid = !(inputPw.value.trim().length < 5) && pwReg.test(
      inputPw.value);

  if (inputPw.value === inputNewPw.value) {
    pwDesc.innerHTML = "현재 비밀번호와 새 비밀번호가 일치합니다. 다른 비밀번호를 사용해주세요."
  }

  if (inputPwValid) {
    pwDesc.innerHTML = "";
  }
  canChangeUserInfo();
});
inputNewPw.addEventListener("keyup", () => {
  const newPwDesc = document.getElementById("newPwDesc");

  inputNewPwValid = !(inputNewPw.value.length < 5) && pwReg.test(
      inputNewPw.value) && (inputPw.value !== inputNewPw.value);

  if (inputPw.value === inputNewPw.value) {
    newPwDesc.innerHTML = "현재 비밀번호와 새 비밀번호가 일치합니다. 다른 비밀번호를 사용해주세요."
    newPwDesc.classList.add("text-danger");
  }

  if (inputNewPwValid) {
    newPwDesc.innerHTML = "변경 가능한 비밀번호입니다."
    newPwDesc.classList.remove("text-danger");
    newPwDesc.classList.add("text-success");
  }

  canChangeUserInfo();
});
inputNewPwCheck.addEventListener("keyup", () => {
  const newPwCheckDesc = document.getElementById("newPwCheckDesc");
  inputNewPwCheckValid = (inputNewPwValid)
      && !(inputNewPwCheck.value.trim().length < 5)
      && pwReg.test(inputNewPwCheck.value);

  if (inputNewPwCheckValid) {
    newPwCheckDesc.innerHTML = "비밀번호가 일치합니다!."
    newPwCheckDesc.classList.remove("text-danger");
    newPwCheckDesc.classList.add("text-success");
  } else {
    newPwCheckDesc.innerHTML = "비밀번호 일치여부를 확인해주세요."
    newPwCheckDesc.classList.remove("text-success");
    newPwCheckDesc.classList.add("text-danger");
  }
  canChangeUserInfo();
});
inputPhone.addEventListener("keyup", () => {
  if (!phoneReg.test(inputPhone.value)) {
    document.getElementById("phoneHelp").classList.add("text-danger");
  } else {
    document.getElementById("phoneHelp").classList.remove("text-danger");
  }
  canChangeUserInfo();
});

function canChangeUserInfo() {

  console.log("hihihi");
  const isNewPwValuesSame = inputNewPw.value
      === inputNewPwCheck.value;

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

  console.log("data==", data)
  const url = "./address";

  const {data: result} = await axios.patch(url, data);
  console.log("result : " + result);

})

infoChangeBtn.addEventListener("click", () => {
  const password = inputPw.value;
  const newPassword = inputNewPw.value;
  const phone = inputPhone.value;

  const data = {
    password,
    newPassword,
    phone,
  };

  axios.patch("./member", data).then(res => {
    if (res.data === "updated") {
      alert("업데이트가 완료되었습니다.");
      location.reload();
    } else {
      alert("정보 수정에 실패하였습니다. 다시 시도해주세요.")
    }
    console.log("result=", res);
  }).catch(e => {
    e.status = 400;
    alert("정보 수정에 실패하였습니다. 다시 시도해주세요.")
  })

})