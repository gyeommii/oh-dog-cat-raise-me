document.addEventListener('DOMContentLoaded', () => {
    const bsCollapse = new bootstrap.Collapse('div#collapseComments', {toggle: false});
    
    const btnToggleComment = document.querySelector('button#btnToggleComment');
    btnToggleComment.addEventListener('click', () => {
        bsCollapse.toggle();
        
        if (btnToggleComment.innerHTML === '댓글 보기') {
            btnToggleComment.innerHTML = '댓글 감추기';
            getAllComments();
        } else {
            btnToggleComment.innerHTML = '댓글 보기';
        }
    });
    
    const btnRegisterComment = document.querySelector('button#btnRegisterComment');
    btnRegisterComment.addEventListener('click', registerComment);
    
    const modal = new bootstrap.Modal('div#commentModal', {backdrop: true});
    const btnUpdateComment = document.querySelector('button#btnUpdateComment');
    btnUpdateComment.addEventListener('click', () => {
        const commentId = document.querySelector('input#modalCommentId').value;
        const ctext = document.querySelector('textarea#modalCommentText').value;
        updateComment(commentId, ctext);
    });

    function registerComment(event) {
        const postId = document.querySelector('input#post_pk').value;
        const ctext = document.querySelector('textarea#ctext').value;
        const member_fk = document.querySelector('input#member_fk').value; 
        //signedMember; // signedMember는 현재 로그인된 사용자 ID
        
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
    
    function makeCommentElements(data) {
        const divComments = document.querySelector('div#comments');
        let htmlStr = '';
    
        data.forEach(comment => {
            const time = new Date(comment.modifiedTime).toLocaleString();
            htmlStr += `
                <div class="card card-body my-1">
                    <div>
                        <span class="d-none">${comment.comments_pk}</span>
                        <span class="fw-bold">${comment.member_fk}</span>
                        <span class="text-secondary">${time}</span>
                    </div>
                    <div>${comment.ctext}</div>`;
    
            // 수정 및 삭제 버튼은 현재 로그인한 사용자와 댓글 작성자가 같을 때만 표시
            // signedMember 현재 로그인한 사용자의 정보
            if (comment.member_fk === signedMember) {
                htmlStr += `
                    <div>
                        <button class="btnCommentDelete btn btn-outline-danger" 
                            data-id="${comment.comments_pk}">삭제</button>
                        <button class="btnCommentModify btn btn-outline-success" 
                            data-id="${comment.comments_pk}" data-text="${comment.ctext}">수정</button>
                    </div>`;
            }
            
            htmlStr += '</div>'; // card 종료 태그
    });

    divComments.innerHTML = htmlStr;

    // 삭제 및 수정 버튼에 대한 이벤트 리스너를 추가합니다.
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
                }
            })
            .catch(error => {
                console.error('댓글 수정 중 오류 발생', error);
            });
    }

    function deleteComment(commentId) {
        if (!confirm('댓글을 삭제하시겠습니까?')) return;

        axios.delete(`../post/comment/${commentId}`)
            .then(response => {
                if (response.data === 1) {
                    alert('댓글이 삭제되었습니다.');
                    getAllComments();
                }
            })
            .catch(error => {
                console.error('댓글 삭제 중 오류 발생', error);
            });
    }

  
    // 댓글 수정 모달을 보여주는 함수
function showEditCommentModal(commentId, currentText) {
    // 모달에 현재 댓글 내용을 채워넣음
    document.querySelector('input#modalCommentId').value = commentId;
    document.querySelector('textarea#modalCommentText').value = currentText;

    // 모달을 보여줌
    modal.show();
}

// 댓글 삭제 확인을 위한 함수
function showDeleteConfirmation(commentId) {
    if(confirm('댓글을 삭제하시겠습니까?')) {
        deleteComment(commentId);
    }
}

// 댓글 목록 HTML을 작성하고, div#comments 영역에 추가하는 함수.
function makeCommentElements(data) {
    const divComments = document.querySelector('div#comments');
    let htmlStr = '';

    data.forEach(comment => {
        const time = new Date(comment.modifiedTime).toLocaleString();
        htmlStr += `
            <div class="card card-body my-1">
                <div>
                    <span class="d-none">${comment.comments_pk}</span>
                    <span class="fw-bold">${comment.member_fk}</span>
                    <span class="text-secondary">${time}</span>
                </div>
                <div>${comment.ctext}</div>
                `;

        if (comment.member_fk === signedMember) {
            htmlStr += `
                <div>
                    <button class="btnCommentDelete btn btn-outline-danger" 
                        data-id="${comment.comments_pk}">삭제</button>
                    <button class="btnCommentModify btn btn-outline-success" 
                        data-id="${comment.comments_pk}" data-text="${comment.ctext}">수정</button>
                </div>`;
        }
        
        htmlStr += '</div>'; // card 종료 태그
    });

    divComments.innerHTML = htmlStr;

    // 삭제 및 수정 버튼에 이벤트 리스너 등록
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
