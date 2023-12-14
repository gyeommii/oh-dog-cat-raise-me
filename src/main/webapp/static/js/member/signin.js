document.addEventListener("DOMContentLoaded", () => {
  const inputId = document.getElementById("inputUserId");
  const inputPw = document.getElementById("inputPw");

  const loginBtn = document.getElementById("loginBtn");
  loginBtn.addEventListener("click", loginBtnClickHandler);

  async function loginBtnClickHandler() {
    const member_id = inputId.value;
    const password = inputPw.value;

    const data = {
      member_id,
      password
    };

    console.log("data={}", data)

    const {data: result} = await axios.post("./signin", data);

    console.log("result=", result);
  }

})