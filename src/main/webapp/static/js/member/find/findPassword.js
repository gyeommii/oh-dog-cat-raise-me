document.addEventListener("DOMContentLoaded", () => {
  const btnradio1 = document.getElementById("btnradio1");

  btnradio1.addEventListener("click", () => {
    location.href = "./memberid";
  });

  const loginBtn = document.getElementById('loginBtn');
  const toastLiveExample = document.getElementById('liveToast');

  if (loginBtn) {
    loginBtn.addEventListener('click', (e) => {
      e.preventDefault();
      const toast = new bootstrap.Toast(toastLiveExample);

      const memberIdResulted = document.getElementById("memberIdResulted");
      memberIdResulted.innerHTML = "심채원";
      toast.show()
    })
  }

})