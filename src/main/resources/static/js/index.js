document.addEventListener('DOMContentLoaded', function() {
    let xhr = new XMLHttpRequest(); // XMLHttpRequest 객체 생성
    xhr.open('GET', '/get-header', true); // 요청을 초기화합니다.

    xhr.onload = function () {
        if (xhr.status >= 200 && xhr.status < 300) {
            // 요청이 성공적으로 완료되면 실행됩니다.
            document.getElementById('headerContainer').innerHTML = xhr.responseText; // 응답을 headerContainer에 삽입
        } else {
            // 서버에서 4xx, 5xx 응답을 반환하면 오류 처리를 합니다.
            console.error('The request failed!');
            reissue();
        }
    };

    xhr.onerror = function () {
        // 요청이 네트워크 문제로 실패했을 때 실행됩니다.
        console.error('The request failed due to a network error!');
    };

    xhr.send(); // 요청을 서버로 보냅니다.
});

function logout() {
    let url = '/api/logout';

    // 기본 요청 설정
    let fetchOptions = {
        method: 'POST',
    };

    // AJAX 요청 예시
    fetch(url, fetchOptions)
        .then(data => {
            console.log('Success:', data);
            window.location.href="/";

        })
        .catch((error) => {
            console.error('Error:', error);
            window.location.href = "/";
        });
}

function reissue() {
    let url = '/api/reissue';

    // 기본 요청 설정
    let fetchOptions = {
        method: 'GET',
    };

    // AJAX 요청 예시
    fetch(url, fetchOptions)
        .then(data => {
            console.log('Success:', data);
            window.location.href = "/";
        })
        .catch((error) => {
            console.error('Error:', error);
            window.location.href = "/";
        });
}