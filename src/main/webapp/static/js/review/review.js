/*
 * review.js
 */

// 예를 들어, 리뷰를 불러오는 함수
function loadReviews() {
  axios.get('/api/review/details/123') // 여기에 실제 제품 ID를 넣어주세요
    .then(response => {
      // 서버로부터 받은 리뷰 데이터 처리
      const reviews = response.data;
      // 리뷰 데이터를 화면에 표시하는 등의 작업 수행
      displayReviews(reviews);
    })
    .catch(error => {
      console.error('Error fetching reviews:', error);
    });
}

// 리뷰를 화면에 표시하는 함수 (예시)
function displayReviews(reviews) {
  const reviewContainer = document.getElementById('reviewContainer');
  // 받은 리뷰 데이터를 화면에 추가하는 등의 작업을 수행
  reviews.forEach(review => {
    const reviewElement = document.createElement('div');
    reviewElement.textContent = `Reviewer: ${review.reviewer}, Rating: ${review.rating}, Comment: ${review.comment}`;
    reviewContainer.appendChild(reviewElement);
  });
}

// 버튼 클릭 시 리뷰 불러오기
document.addEventListener('DOMContentLoaded', function() {
  const reviewButton = document.getElementById('reviewButton');
  reviewButton.addEventListener('click', function() {
    loadReviews();
  });
});