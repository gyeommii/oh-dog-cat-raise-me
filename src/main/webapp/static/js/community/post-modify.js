/**
 * post-modify.js
 * /community/modify.jsp에 포함
 * 포스트 삭제, 업데이트 기능.
 */

 document.addEventListener('DOMContentLoaded', () => {
     const form = document.querySelector('form#modifyForm');
     
     const inputId = document.querySelector('input#post_pk');
     
     const inputTitle = document.querySelector('input#title');
     
     const textareaContent = document.querySelector('textarea#content');
     
     const btnDelete = document.querySelector('button#btnDelete');
     
     const btnUpdate = document.querySelector('button#btnUpdate');
     
     btnDelete.addEventListener('click', () => {
         const result = confirm('정말 삭제하시겠습니까?');
         if(result) {
            location.href = `delete?post_pk=${inputId.value}`; 
         }
     });
     
     btnUpdate.addEventListener('click', () => {
         if(inputTitle.value === '' || textareaContent.value === '' ){
             alert('제목과 내용은 반드시 입력해야 합니다.');
             return; 
         } 
         
         const result = confirm('정말 수정하시겠습니까?');
         
         if(result){
             form.action = 'update'; 
             form.method = 'post'; 
             form.submit(); 
         }
     });
     
     
 });