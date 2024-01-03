/**
 * review.js
 */

document.addEventListener('DOMContentLoaded', () => {

    const bsCollapse = new bootstrap.Collapse('div#collapseReviews', { toggle: false });

    const btnToggleReview = document.querySelector('#btnToggleReview');

    btnToggleReview.addEventListener('click', () => {
        bsCollapse.toggle();

        if (btnToggleReview.innerHTML === '리뷰열기') {
            btnToggleReview.innerHTML = '리뷰닫기'

            getAllReviews();
        } else {
            btnToggleReview.innerHTML = '리뷰열기'
        }

    });

    const modal = new bootstrap.Modal('div#ReviewModal', { backdrop: true });
    const btnUpdateModal = document.querySelector('button#btnUdateReview')

    btnUpdateModal.addEventListener('click', updateReview);

    async function updateReview(e) {
        const review_pk = document.querySelector('input#modalReview_pk').value;
        const comment = document.querySelector('#modalContent').value;
        const score = document.querySelector('#modalScore').value;

        const data = {
            review_pk,
            comment,
            score,
        }

        const uri = `../api/review/${review_pk}`;

        axios.put(uri, data)
            .then((response) => {
                console.log(response);
                if (response === 1) {
                    alert('수정이 완료되었습니다.');
                    modal.hide();
                    getAllReviews;
                }
            })
            .catch((error) => {
                console.log(error);
            });
    }
    
    function getAllReviews() {
        const product_pk = document.querySelector('#product_pk').value;

        const uri = `../api/review/all/${product_pk}`;

        axios.get(uri)
            .then((response) => {
                console.log(response);
                makeReviewElements(response.data);
            })
            .catch((error) => {
                console.log(error);
            });
    }

    function makeReviewElements(data) {
        const divReviews = document.querySelector('div#reviews')

        let htmlStr = '';

        for (let review of data) {
            const time = new Date(review.modified_time).toLocaleString();

            htmlStr += `
            <div class="card card-body my-2">
                <div>
                    <span class="d-none">${review.review_pk}</span>
                    <span class="fw-bold">${signedMember.member_id}</span>
                </div>
                <div>
                    <span class="text-secondary">${review.score}</span>
                    <span class="text-secondary">${review.content}</span>
                    <span class="text-secondary">${review.img}</span>
                    <span class="text-secondary">${time}</span>
                </div>
            `;
            if (signedMember.member_pk === review.member_fk) {
                htmlStr += `
                            <div>
                                <button class="btnReviewDelete btn btn-outline-danger btn-sm" data-review_pk=${review.review_pk}>삭제</button>
                                <button class="btnReviewModify btn btn-outline-success btn-sm data-review_pk=${review.review_pk}">수정</button>
                            </div>
                            `;

            }

        }
        htmlStr += `</div>`;

        divReviews.innerHTML = htmlStr;
    }

    const btnDeletes = document.querySelectorAll('.btnReviewDelete');
    for (let btn of btnDeletes) {
        btn.addEventListener('click', deleteReview);
    }

    const btnModifies = document.querySelectorAll('.btnCommentModify');
    for (let btn of btnModifies) {
        btn.addEventListener('click', showReviewModal);
    }

    function deleteReview(e) {
        console.log(e.target);

        const result = confirm('리뷰를 삭제하시겠습니까?')

        if (result) {
            return;
        }

        const review_pk = e.target.getAttribute('data-review_pk');

        const uri = `../api/comment/${review_pk}`
        axios.delete(uri)
            .then((response) => {
                console.log(response);
                if (response.data === 1) {
                    alert('리뷰를 삭제했습니다.')
                    getAllReviews();
                }
            })
            .catch((error) => {
                console.log(error);
            });
        async function showReviewModal(e) {
            console.log(e.target);

            const review_pk = e.target.getAttribute('data-review_pk');
            try {
                const response = await axios.get(`..api/review/${review_pk}`);
                console.log(response);
                const content = response.data.content;
                const score = response.data.score;
                document.querySelector('input#modalContent').value = content;
                document.querySelector('input#modalScore').value = score;

                modal.show();

            } catch (error) {
                console.log(error);
            }
        }

    }

});
