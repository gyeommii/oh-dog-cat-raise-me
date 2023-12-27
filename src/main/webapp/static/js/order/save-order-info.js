const optionDivList = document.querySelectorAll(".option-info-to-order");
const optionList = [];

optionDivList.forEach(el => {
  const option = {
    option_fk: parseInt(el.getAttribute("data-optionfk")),
    count: parseInt(el.getAttribute("data-count"))
  }

  optionList.push(option);
})

console.log(optionList)