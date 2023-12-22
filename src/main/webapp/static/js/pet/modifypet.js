/**
 * modifypet.js
 */
/*
document.addEventListener('DOMContentLoaded', () => {
    const form = document.querySelector('form#modifyForm');
    
    const img = document.querySelector('input#img');
    const pet_pk = document.querySelector('input#pet_pk');
    const pet_name = document.querySelector('input#pet_name');
    //const pet_type = document.querySelector('select#pet_type option:selected').value;
    const age = document.querySelector('input#age');
    //const gender = document.querySelector('input#gender').value;
    //const chehyeong = document.querySelector('select#chehyeong option:selected').value;
    
    const btnDelete = document.querySelector('button#btnDelete');
    const btnUpdate = document.querySelector('button#btnUpdate');
    
    btnDelete.addEventListener('click', () => {
        const result = confirm('정말 삭제하시겠습니까?');
        
        if (result) {
            location.href = `delete?pet_pk=${pet_pk.value}`;
        }        
    });
    
    btnUpdate.addEventListener('click', () => {
       if (pet_name.value === '' || age.value === ''){
           alert('빈 칸을 입력해주세요.');
           return;
       }
    const result = confirm('정말 업데이트하시겠습니까?')
        if (result) {
            form.action = 'modifypet';
            form.method = 'post';
            form.submit();
        }
        
    });
    
});*/