document.addEventListener('DOMContentLoaded', () => {
    const bsCollapse = new bootstrap.Collapse('div#collapseComments', { toggle: false });
    const btnToggleComment = document.querySelector('button#btnToggleComment');
    const btnRegisterComment = document.querySelector('button#btnRegisterComment');
    const modal = new bootstrap.Modal('div#commentModal', { backdrop: true });
    const btnUpdateComment = document.querySelector('button#btnUpdateComment');
    
    // 댓글 창
    btnToggleComment.addEventListener('click', () => {
        bsCollapse.toggle();
        if (btnToggleComment.innerHTML === '댓글 보기') {
            btnToggleComment.innerHTML = '댓글 감추기';
            getAllComments();
        } else {
            btnToggleComment.innerHTML = '댓글 보기';
        }
    });

    btnRegisterComment.addEventListener('click', registerComment);

    btnUpdateComment.addEventListener('click', () => {
        const commentId = document.querySelector('input#modalCommentMember_fk').value;
        const ctext = document.querySelector('textarea#modalCommentText').value;
        updateComment(commentId, ctext);
    });
    
    // 댓글 등록
    function registerComment(event) {
        const postId = document.querySelector('input#post_pk').value;
        const ctext = document.querySelector('textarea#ctext').value;
        const member_fk = document.querySelector('input#member_fk').value;

        if (ctext.trim() === '') {
            alert('댓글 내용을 입력하세요.');
            return;
        }

        const data = { post_fk: postId, ctext, member_fk };
        axios.post('../post/comment', data)
            .then(response => {
                if (response.data === 1) {
                    alert('댓글 등록 완료!');
                    document.querySelector('textarea#ctext').value = '';
                    getAllComments();
                }
            })
            .catch(error => {
                console.error('댓글 등록 중 오류 발생', error);
            });
    }
    
    // 댓글목록 조회
    function getAllComments() {
        const postId = document.querySelector('input#post_pk').value;
        axios.get(`../post/comment/all/${postId}`)
            .then(response => {
                makeCommentElements(response.data);
            })
            .catch(error => {
                console.error('댓글 조회 중 오류 발생', error);
            });
    }
    
    // 댓글 카드 생성
    function makeCommentElements(data) {
        const divComments = document.querySelector('div#comments');
        let htmlStr = '';

        data.forEach(comment => {
            const time = new Date(comment.modifiedTime).toLocaleString();
            htmlStr += `
                <div class="card card-body my-1">
                    <div>
                        <span class="d-none">${comment.comments_pk}</span>
                        <span class="fw-bold memberId-text">${comment.memberId}</span>
                        <span class="text-secondary">${time}</span>
                    </div>
                    <div>${comment.ctext}</div>`;
            console.log(typeof comment.member_fk, typeof signedMember);
            if (comment.member_fk == signedMember) {
                htmlStr += `
                    <div class="mt-2">
                        <button class="btnCommentModify btn btn-outline-warning btn-sm" 
                                data-id="${comment.comments_pk}" data-text="${comment.ctext}">수정</button>
                        <button class="btnCommentDelete btn btn-outline-secondary btn-sm" 
                                data-id="${comment.comments_pk}">삭제</button>
                    </div>`;
            } 
            
            htmlStr += '</div>';
        });

        divComments.innerHTML = htmlStr;
        addEventListenersToButtons();
    }
    
    // 댓글 수정
    function updateComment(commentId, ctext) {
        if (ctext.trim() === '') {
            alert('댓글 내용을 입력하세요.');
            return;
        }
    
        axios.put(`../post/comment/${commentId}`, { ctext })
            .then(response => {
                if (response.data === 1) {
                    alert('댓글이 수정되었습니다.');
                    getAllComments();
                    modal.hide(); // 모달 창 숨기기
                }
            })
            .catch(error => {
                console.error('댓글 수정 중 오류 발생', error);
            });
    }

    
    // 댓글 삭제
    function deleteComment(commentId) {
        if (!confirm('댓글을 삭제하시겠습니까?')) return;
    
        axios.delete(`../post/comment/${commentId}`)
            .then(response => {
                if (response.data === 1) {
                    alert('댓글이 삭제되었습니다.');
                    getAllComments();
                    modal.hide(); // 모달 창 숨기기
                }
            })
            .catch(error => {
                console.error('댓글 삭제 중 오류 발생', error);
            });
    }
    
    // 댓글 수정 모달
    function showEditCommentModal(commentId, currentText) {
        document.querySelector('input#modalCommentMember_fk').value = commentId;
        document.querySelector('textarea#modalCommentText').value = currentText;
        modal.show();
    }
    
    function showDeleteConfirmation(commentId) {
            deleteComment(commentId);
        
    }

    function addEventListenersToButtons() {
        document.querySelectorAll('button.btnCommentDelete').forEach(button => {
            button.addEventListener('click', function() {
                const commentId = this.getAttribute('data-id');
                showDeleteConfirmation(commentId);
            });
        });

        document.querySelectorAll('button.btnCommentModify').forEach(button => {
            button.addEventListener('click', function() {
                const commentId = this.getAttribute('data-id');
                const currentText = this.getAttribute('data-text');
                showEditCommentModal(commentId, currentText);
            });
        });
    }
});
