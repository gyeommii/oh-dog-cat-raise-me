document.addEventListener("DOMContentLoaded", () => {
  // 1.  회원가입으로 이동
  const btnradio1 = document.getElementById("btnradio1");

  // 2. 스크롤 위치 설정
  const searchDiv = document.getElementById("searchDiv");
  const a = searchDiv.getBoundingClientRect();
  window.scrollTo(a.x, a.y); // 2

  btnradio1.addEventListener("click", () => {
    location.href = "./memberid";
  }); // 1

})