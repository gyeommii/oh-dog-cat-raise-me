/**
 * reviewregister.js
 */

document.addEventListener('DOMContentLoaded', () => {

    // textarea 요소와 글자 수를 표시할 span 요소를 변수에 할당합니다.
    const textArea = document.querySelector('#content');
    const charCount = document.querySelector('#charCount');

    // textarea의 input 이벤트를 사용하여 텍스트 입력이 발생할 때마다 글자 수를 업데이트합니다.
    textArea.addEventListener('input', () => {
        // 입력된 텍스트의 길이를 구합니다.
        const currentLength = textArea.value.length;

        // 최대 입력 가능한 글자 수를 설정합니다.
        const maxLength = 500;

        // 화면에 글자 수와 최대 글자 수를 표시합니다.
        charCount.textContent = currentLength + ' / ' + maxLength;

        // 만약 최대 글자 수를 초과하면 입력을 막습니다.
        if (currentLength > maxLength) {
            textArea.value = textArea.value.substring(0, maxLength);
            charCount.textContent = '500 / 500 글자수를 초과합니다.';
        }
    });    

});