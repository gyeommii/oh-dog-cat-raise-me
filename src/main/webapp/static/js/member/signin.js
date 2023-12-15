document.addEventListener("DOMContentLoaded", () => {
  const resultDesc = document.getElementById("login-result-desc");

  if (location.href.includes("result=success")) {
    resultDesc.innerHTML = "회원가입에 성공하셨습니다. 로그인을 해볼까요?";
    resultDesc.classList.remove("d-none");
    resultDesc.classList.remove("text-danger");
    resultDesc.classList.add("text-success");
  }

  if (location.href.includes("result=f")) {
    resultDesc.classList.remove("d-none");
  } else {
    resultDesc.classList.add("d-none");
  }

})