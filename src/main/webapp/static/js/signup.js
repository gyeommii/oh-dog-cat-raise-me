document.addEventListener("DOMContentLoaded", () => {
  if(location.href.includes("result=fail")) {
    alert("로그인에 실패하였습니다. 다시 시도해주세요!");
  }

  const checkDuplicationId = document.getElementById("check-duplicated-id-btn");
  const submitBtn = document.getElementById("submitBtn");
  submitBtn.disabled = true;

  let isUserIdValid = false;
  let isUserIdUnique = false;

  let isPasswordValid = false;
  let isPasswordChecked = false;
  let isEmailValid = false;
  let isPhoneValid = false;

  let isAddressValid = true;

  checkDuplicationId.addEventListener("click", async (e) => {
    const inputUserId = document.getElementById("inputUserId");
    const userIdDesc = document.getElementById("userIdDesc");

    const userId = inputUserId.value;

    const {data} = await axios.get(`./checkId?userId=${userId}`);

    console.dir(data);
    console.log(typeof data);
    if (!data) {
      userIdDesc.innerHTML = "<span class='text-danger'>중복된 아이디입니다. 다른 아이디를 입력해주세요!</span>";
      isUserIdUnique = false;
    } else {
      userIdDesc.innerHTML = "<span class='text-success'>사용가능한 아이디입니다. 회원가입을 계속 진행해주세요.</span>";
      isUserIdUnique = true;
    }
  });

  const inputUserId = document.getElementById("inputUserId");
  inputUserId.addEventListener("keyup", onKeyUpAtUserId);

  const inputPw = document.getElementById("inputPw");
  inputPw.addEventListener("keyup", onKeyUpAtPassword);

  const inputPwCheck = document.getElementById("inputPwCheck");
  inputPwCheck.addEventListener("keyup", onKeyUpAtPasswordCheck);

  const inputEmail = document.getElementById("inputEmail");
  inputEmail.addEventListener("change", onChangeAtEmail);

  const inputPhone = document.getElementById("inputPhone");
  inputPhone.addEventListener("change", onChangeAtPhone);

  function onKeyUpAtUserId() {
    //   DONE: userId 조건
    /**
     * 1. userId는 6자 이상이어야 한다.
     * 2. userId는 영어와 숫자의 조합이어야 한다.
     * 3. 중복확인을 마무리한 이후여야 한다.
     *  3-1. 중복확인을 한 후, 아이디를 변경 시 중복 확인을 다시 하여야 한다.
     */

    const userId = inputUserId.value;
    const userIdDesc = document.getElementById("userIdDesc");

    if (isUserIdUnique) {
      isUserIdUnique = false;
      userIdDesc.innerHTML = "<span class='text-danger'>중복확인을 다시 해주세요.</span>";
    }

    const engNum = /^[a-zA-Z](?=.*[a-zA-Z])(?=.*[0-9]).{4,12}$/g;

    if (userId.length < 6 || !engNum.test(userId)) {
      userIdDesc.innerHTML = "아이디는 6자 이상의 영어와 숫자의 조합입니다.";
      userIdDesc.classList.remove("text-success")
      userIdDesc.classList.add("text-danger");
      isUserIdValid = false;
    } else {
      userIdDesc.innerHTML = "멋진 아이디입니다! 중복 확인을 완료해주세요.";
      userIdDesc.classList.remove("text-danger")
      userIdDesc.classList.add("text-success");
      isUserIdValid = true;
    }
    isBtnActivatable();
  }

  async function onChangeAtEmail() {
    // Done : Email 조건
    /**
     * 1. email 형태를 갖춰야 한다.
     * 2. DB 내 해당 email로 가입된 중복 이메일이 없어야 한다.
     */
    const email = inputEmail.value;
    const emailDesc = document.getElementById("emailDesc");
    const {data} = await axios.get(`./checkEmail?email=${email}`);

    const emailReg = /^[a-zA-Z0-9+-_.]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$/;

    if (emailReg.test(email) && data) {
      emailDesc.innerHTML = "가입 가능한 이메일입니다.";
      emailDesc.classList.remove("text-danger");
      emailDesc.classList.add("text-success");
      isEmailValid = true;
    } else {
      if (!emailReg.test(email)) {
        emailDesc.innerHTML = "올바른 이메일을 입력해주세요.";
        emailDesc.classList.remove("text-success");
        emailDesc.classList.add("text-danger");
      } else {
        emailDesc.innerHTML = "이미 가입된 이메일입니다.";
        emailDesc.classList.remove("text-success");
        emailDesc.classList.add("text-danger");
      }
      isEmailValid = false;
    }

    isBtnActivatable();
  }

  function onKeyUpAtPassword() {
    // TODO : 비밀 번호 조건
    /* 1. 영문 숫자 혼합
    *  2. 공백 제외 6글자 이상
    */
    const password = inputPw.value;
    const pwHelp = document.getElementById("pwHelp");

    const pwReg = /^(?=.*[A-Za-z])(?=.*\d)(?!.*\s).{8,}$/;

    if (pwReg.test(password)) {
      isPasswordValid = true;
      pwHelp.innerHTML = "조건에 알맞은 비밀번호입니다. 비밀번호 확인을 완료해주세요.";
      pwHelp.classList.remove("text-danger");
      pwHelp.classList.add("text-success");
    } else {
      isPasswordValid = false;
      pwHelp.innerHTML = "비밀번호 조건을 다시 확인해주세요! 조건) 6글자 이상의 영어와 숫자 조합";
      pwHelp.classList.remove("text-success");
      pwHelp.classList.add("text-danger");
    }
  }

  function onKeyUpAtPasswordCheck() {
    const password = inputPw.value;
    const passwordCheck = inputPwCheck.value;

    const pwCheckHelp = document.getElementById("pwCheckHelp");

    if (password !== passwordCheck) {
      pwCheckHelp.innerHTML = "비밀번호가 일치하지 않습니다. 다시 입력해주세요.";
      pwCheckHelp.classList.remove("text-success");
      pwCheckHelp.classList.add("text-danger");
      isPasswordChecked = false;
    } else {
      pwCheckHelp.innerHTML = "비밀번호가 일치합니다! 회원가입을 마무리해주세요^^.";
      pwCheckHelp.classList.remove("text-danger");
      pwCheckHelp.classList.add("text-success");
      isPasswordChecked = true;
    }

    isBtnActivatable();
  }

  function onChangeAtPhone() {
    // DB 중복 체크는 따로 하지 않고, 형태 유효성 검사만 할 것
    const phone = inputPhone.value;
    const phoneReg = /^(02|010)-?\d{3,4}-?\d{4}$/;
    isPhoneValid = phoneReg.test(phone);

    const phoneHelp = document.getElementById("phoneHelp");

    if (isPhoneValid) {
      phoneHelp.innerHTML = "유효한 핸드폰 번호입니다. ";
      phoneHelp.classList.remove("text-danger");
      phoneHelp.classList.add("text-success");
    } else {
      phoneHelp.innerHTML = "유효하지 않은 핸드폰 번호입니다. 다시 확인해주세요.";
      phoneHelp.classList.remove("text-success");
      phoneHelp.classList.add("text-danger");
    }
    isBtnActivatable();
  }

  function isBtnActivatable() {
    submitBtn.disabled = !(isUserIdValid && isPhoneValid && isUserIdUnique
        && isEmailValid
        && isPasswordValid && isPasswordChecked && isAddressValid);
  }

// TODO : 배송지 입력란 eventHandler 등록
  document.getElementById("daumPostOpenBtn").addEventListener("click",
      findAddressThrougDaumPost);
  document.getElementById("btnFoldWrap").addEventListener("click",
      foldDaumPostcode);

  const zone_codeInput = document.getElementById('zonecode');
  const addressInput = document.getElementById("address");
  const detail_addrInput = document.getElementById("detailAddress");
  const recipientInput = document.getElementById("recipient");

  detail_addrInput.addEventListener("change", detailAddrOnChangeEvent);
  recipientInput.addEventListener("change", detailAddrOnChangeEvent);

  const addressResetBtn = document.getElementById("address-reset-btn");
  addressResetBtn.addEventListener("click", resetAddress);

  function resetAddress() {
    zone_codeInput.value = "";
    addressInput.value = "";
    detail_addrInput.value = "";
    recipientInput.value = "";

    isAddressValid = true;
    isBtnActivatable();
  }

  function detailAddrOnChangeEvent() {

    const addressHelp = document.getElementById("addressHelp");

    const zone_code = zone_codeInput.value;
    const address = addressInput.value;
    const detail_addr = detail_addrInput.value;
    const recipient = recipientInput.value;

    if (zone_code.trim().length > 0
        && address.trim().length > 0
        && detail_addr.trim().length > 0
        && recipient.trim().length > 0
    ) {
      addressHelp.innerHTML = "* 배송지 입력시 수취인과 상세 주소는 필수입니다.";
      addressHelp.classList.remove("text-danger");
      addressHelp.classList.add("text-success");
      isAddressValid = true;
    } else if ((zone_code.trim().length === 0
        && address.trim().length === 0
        && detail_addr.trim().length === 0
        && recipient.trim().length === 0)) {
      addressHelp.innerHTML = "* 배송지 입력시 수취인과 상세 주소는 필수입니다.";
      addressHelp.classList.remove("text-danger");
      addressHelp.classList.add("text-success");
      isAddressValid = true;
    } else {
      addressHelp.innerHTML = "* 배송지 입력을 완료하십시오.";
      addressHelp.classList.remove("text-success");
      addressHelp.classList.add("text-danger");
      isAddressValid = false;
    }

    isBtnActivatable();
  }

//   submit 버튼 눌렀을 때 전송 요청 함수
  submitBtn.addEventListener("click", onClickSubmitBtn);

  async function onClickSubmitBtn() {
    const member_id = inputUserId.value;
    const password = inputPw.value;
    const email = inputEmail.value;
    const phone = inputPhone.value;

    const address = addressInput.value;
    const detail_addr = detail_addrInput.value;
    const zonecode = zone_codeInput.value;
    const recipient = recipientInput.value;

    const data = {
      member_id,
      password,
      email,
      phone,
      address,
      detail_addr,
      zonecode,
      recipient
    };

    const {data: result} = await axios.post("./signup", data);

    console.log("result= ", result);
    if (result) {
      location.href = "./signin?result=success";
    } else {
      location.href = `./signup?result=fail`;
    }
  }

});