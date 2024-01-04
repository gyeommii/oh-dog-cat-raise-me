/**
 * reviewregister.js
 */

document.addEventListener('DOMContentLoaded', () => {

    const ratingRadios = document.querySelectorAll('input[name="score"]');

    function validateRating() {
        let checked = false;
        ratingRadios.forEach(radio => {
            if (radio.checked) {
                checked = true;
            }
        });
        return checked;
    }

    // 리뷰 제출 시 평점이 선택되었는지 확인
    document.querySelector('form').addEventListener('submit', (event) => {
        if (!validateRating()) {
            event.preventDefault(); // 제출 막기
            alert('평점을 선택해주세요.');
        }
    });

    // textarea 요소와 글자 수를 표시할 span 요소를 변수에 할당합니다.
    const textArea = document.querySelector('#content');
    const charCount = document.querySelector('#charCount');

    // textarea의 input 이벤트를 사용하여 텍스트 입력이 발생할 때마다 글자 수를 업데이트합니다.
    textArea.addEventListener('input', () => {
        // 입력된 텍스트의 길이를 구합니다.
        const currentLength = textArea.value.length;

        // 최대 입력 가능한 글자 수를 설정합니다.
        const maxLength = 300;

        // 화면에 글자 수와 최대 글자 수를 표시합니다.
        charCount.textContent = currentLength + ' / ' + maxLength;

        // 만약 최대 글자 300자를 초과하면 입력을 막습니다.
        if (currentLength > maxLength) {
            textArea.value = textArea.value.substring(0, maxLength);
            charCount.textContent = '300 / 300 글자수를 초과합니다.';
        }
    });

    const img_file = document.querySelector('#img_file');

    img_file.addEventListener('change', previewImage);

    function previewImage() {
        const file = document.querySelector('#img_file').files[0];
        const preview = document.querySelector('#imagePreview');
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