// 비밀번호 유효성 검사에 필요한 변수들
const passwordInput = document.getElementById("input-pw");
const passwordCheckInput = document.getElementById("input-pw-check");
const pwBtn = document.getElementById("pwResetBtn");
console.dir(pwBtn)
let isPasswordValid = false;
let isPasswordChecked = false;

pwBtn.disabled = !isPasswordValid;

passwordInput.addEventListener("keyup", keyupEventPassword);
passwordCheckInput.addEventListener("keyup", keyupEventPasswordCheck);

function keyupEventPassword(e) {
  // TODO : 비밀 번호 조건
  /* 1. 영문 숫자 혼합
  *  2. 공백 제외 6글자 이상
  */
  const password = passwordInput.value;
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

  isBtnActivatable();
}

function isBtnActivatable() {
  pwBtn.disabled = !(isPasswordValid && isPasswordChecked);
}

function keyupEventPasswordCheck() {
  const password = passwordInput.value;
  const passwordCheck = passwordCheckInput.value;

  const pwCheckHelp = document.getElementById("pwCheckHelp");

  if (password !== passwordCheck) {
    pwCheckHelp.innerHTML = "비밀번호가 일치하지 않습니다. 다시 입력해주세요.";
    pwCheckHelp.classList.remove("text-success");
    pwCheckHelp.classList.add("text-danger");
    isPasswordChecked = false;
  } else {
    pwCheckHelp.innerHTML = "비밀번호가 일치합니다.";
    pwCheckHelp.classList.remove("text-danger");
    pwCheckHelp.classList.add("text-success");
    isPasswordChecked = true;
  }

  isBtnActivatable();
}

pwBtn.addEventListener("click", () => {
})