document.addEventListener("DOMContentLoaded", () => {
  const checkDuplicationId = document.getElementById("check-duplicated-id-btn");
  const submitBtn = document.getElementById("submitBtn");
  submitBtn.disabled = true;

  let isUserIdValid = false;
  let isPasswordValid = false;
  let isEmailValid = false;

  checkDuplicationId.addEventListener("click", async (e) => {
    const inputUserId = document.getElementById("inputUserId");
    const userIdDesc = document.getElementById("userIdDesc");

    const userId = inputUserId.value;

    const {data} = await axios.get(`./checkId?userId=${userId}`);

    if (data) {
      userIdDesc.innerHTML = "<span class='text-danger'>중복된 아이디입니다. 다른 아이디를 입력해주세요!</span>";
    } else {
      userIdDesc.innerHTML = "<span class='text-success'>사용가능한 아이디입니다. 회원가입을 계속 진행해주세요.</span>";
    }
  });



  function onKeyUpAtUserId () {

  }
  function onKeyUpAtEmail () {

  }
  function onKeyUpAtPassword () {

  }
  function isBtnActivatable() {
    submitBtn.disabled = !(isUserIdValid && isEmailValid && isPasswordValid);
  }
});