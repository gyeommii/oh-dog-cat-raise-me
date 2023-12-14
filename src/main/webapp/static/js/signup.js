document.addEventListener("DOMContentLoaded", () => {
  const checkDuplicationId = document.getElementById("check-duplicated-id-btn");
  const submitBtn = document.getElementById("submitBtn");
  submitBtn.disabled = true;

  let isUserIdValid = false;
  let isUserIdUnique = false;

  let isPasswordValid = false;
  let isPasswordChecked = false;
  let isEmailValid = false;

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
      pwHelp.innerHTML = "조건에 알맞은 비밀번호입니다. 비밀번호 확인을 완료해주세요.";
      pwHelp.classList.remove("text-danger");
      pwHelp.classList.add("text-success");
    } else {
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
      pwCheckHelp.classList.add("text-text");
      isPasswordChecked = true;
    }

    isBtnActivatable();
  }

  function isBtnActivatable() {
    submitBtn.disabled = !(isUserIdValid && isUserIdUnique && isEmailValid
        && isPasswordValid && isPasswordChecked);
  }

//   submit 버튼 눌렀을 때 전송 요청 함수
  function onClickSubmitBtn () {

  }

});