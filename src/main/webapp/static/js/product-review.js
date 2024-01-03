const reviewContainer = document.getElementById("review-container");

const productPk = parseInt(document.getElementById("productPk").value.trim());
const imgScore = document.querySelector(".estimate-avg-point .img-score");
loadReview();

async function loadReview() {
  const {data} = await axios.get(`../review?productPk=${productPk}`);

  const {satisfaction, reviewList} = data;

  let html = '';
  const roundedNum = (Math.round((satisfaction.score ? satisfaction.score : 0) * 20));
  const backgroundPositionY = Math.floor(roundedNum/10)*10;

  imgScore.style.backgroundPosition = `0 ${backgroundPositionY}%`;
  document.getElementById("score-pane").innerHTML = Math.floor(satisfaction.score) ?? 0;

  // var avg = Math.round(sum/score.length); // 79
  // var result = Math.round(avg/10)*10; // 80

  if (reviewList.length === 0) {
    const emptyReview = `
        <div class="container card my-5 border-0" id="emptyCart"
          style="border-radius: 24px; box-shadow: 0px 0px 10px 0px rgb(230, 230, 230);">
            <div class="card-body py-5">
              <div class="row fw-semibold text-center" style="font-size: 20px;">
                <div class="col my-5">
                  <p style="font-size: 50px">📝</p>
                    작성된 리뷰가 없습니다.
                </div>
              </div>
            </div>
        </div>`;

    reviewContainer.innerHTML = emptyReview;
    return;
  }

  reviewList.map(el => {
    const petInfo = (!el.pet_type ? "" : `<div class="fw-semibold">
                ${el.pet_type === "고양이" ?
            '<span class="badge rounded-pill text-bg-primary" style="font-size: small">'
            + el.pet_type + '</span>'
            :
            '<span class="badge rounded-pill text-bg-success" style="font-size: small">'
            + el.pet_type + '</span>'
        }
        <span>${el.pet_name}
        </span> | <span>${el.chehyeong}</span> | ${el.age}살 |
   
   `) + (el.gender === "female" ?
            `<span class="material-symbols-outlined text-danger"> ${el.gender} </span>`
            : `<span class="material-symbols-outlined text-primary"> ${el.gender} </span>`)
        + '</div>';

    const imgHtml = el.image_url ? ` <div class="mb-5">
              <img
                style='height: 250px;'
                src='/ohdogcat/image?imgUrl=${el.image_url}'
              />
            </div>` : "";

    html += `
      <div class="review-item row mt-5 mb-3">
          <div class="col-2 text-center">
            <div class="fs-4">${el.member_id}</div>
            <div class="estimate-avg-point mt-2">
              <div class="fs-5">별점</div>
              <div>${getStar(el.score)}</div>
            </div>
          </div>
          <div class="col-10">
            <div class="small_header mb-3">
              <div class="pet-container text-black-50">
                ${petInfo}
                <div class="fw-lighter align-middle">[${el.option_name}]</div>
              </div>
              <div class="fw-bolder" style="margin-top: 2%">${getDateStr(
        el.created_time)}</div>
            </div>
            <p class="my-5">
              ${el.content}
            </p>
           ${imgHtml}
          </div>
        </div>
`;
    console.log(html)
    reviewContainer.innerHTML = html;
  })

}

function getDateStr(dataStr) {
  return `${dataStr[0]}/${dataStr[1]}/${dataStr[2]}`;
}

function getStar(score) {
  return '⭐️'.repeat(Math.round(score));
}

function getRoundedAtOne(num) {
  return Math.round((num * 1.0) / 10);
}