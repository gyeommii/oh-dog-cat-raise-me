/**
 * addpet.js
 */

document.addEventListener('DOMContentLoaded', () => {

    const img_file = document.querySelector('#img_file');

    img_file.addEventListener('change', previewImage);

    function previewImage() {
        const preview = document.querySelector('#imagePreview');
        const file = document.querySelector('#img_file').files[0];
        const clearButton = document
            .querySelector('#clearButton');

        const reader = new FileReader();

        reader.onloadend = function() {
            preview.src = reader.result;
            preview.classList.remove('d-none'); // 이미지를 선택하면 감춰진 이미지를 보여줍니다.
            clearButton.classList.remove('d-none');
        }

        if (file) {
            reader.readAsDataURL(file);
        } else {
            preview.src = "";
            preview.classList.add('d-none'); // 이미지가 없을 때는 이미지를 감춥니다.
            clearButton.classList.add('d-none');
        }
    }

    const clearButton = document.querySelector('#clearButton');

    clearButton.addEventListener('click', clearFileInput);

    function clearFileInput(event) {
        event.preventDefault();

        const input = document.querySelector('#img_file');
        const preview = document.querySelector('#imagePreview');
        const clearButton = document
            .querySelector('#clearButton');

        input.value = ''; // 파일 입력 필드 초기화
        preview.src = ''; // 미리보기 이미지 초기화
        preview.classList.add('d-none'); // 이미지 감추기
        clearButton.classList.add('d-none');
    }

});