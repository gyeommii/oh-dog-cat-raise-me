document.addEventListener("DOMContentLoaded", () => {
  if (location.href.includes("result=none")){
    alert("해당하는 아이디를 찾지 못했습니다. 다시 시도해주세요.");
  }
  const btnradio2 = document.getElementById("btnradio2");
  const searchDiv = document.getElementById("searchDiv");
  const resultDiv = document.getElementById("resultDiv");

  btnradio2.addEventListener("click", () => {
    location.href = "./password";
  });

  const a = searchDiv.getBoundingClientRect();
  window.scrollTo(a.x, a.y);
})