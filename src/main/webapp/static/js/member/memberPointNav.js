const pointDiv = document.getElementById("pointDiv");

getMemberPoint();

async function getMemberPoint() {
  const {data: point} = await axios.get("../user/point");
  pointDiv.innerHTML = point;
}