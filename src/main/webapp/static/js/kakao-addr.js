const addressFinder = document.getElementById('address-finder');

function foldDaumPostcode() {
  addressFinder.style.display = 'none';
}

function findAddressThrougDaumPost() {
  let currentScroll = Math.max(document.body.scrollTop,
      document.documentElement.scrollTop);
  new daum.Postcode({
    oncomplete: function (data) {
      let addr = "";

      if (data.userSelectedType === "R") {
        addr = data.roadAddress;
      } else {
        addr = data.jibunAddress;
      }

      if (data.userSelectedType === 'R') {
        let extraAddr = "";
        if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
          extraAddr += data.bname;
        }
        if (data.buildingName !== '' && data.apartment === 'Y') {
          extraAddr += (extraAddr !== '' ? ', ' + data.buildingName
              : data.buildingName);
        }
        if (extraAddr !== '') {
          extraAddr = ' (' + extraAddr + ')';
        }
        addr += extraAddr;
      }

      document.getElementById('postcode').value = data.zonecode;
      document.getElementById("address").value = addr;
      document.getElementById("detailAddress").focus();

      addressFinder.style.display = 'none';

      document.body.scrollTop = currentScroll;
    },
    onresize: function (size) {
      addressFinder.style.height = size.height + 'px';
    },
    width: '100%',
    height: '100%'
  }).embed(addressFinder);

  addressFinder.style.display = "block";
  const clientRect = addressFinder.getBoundingClientRect();
  window.scrollTo(clientRect.left, clientRect.top + (clientRect.height / 2));
}
