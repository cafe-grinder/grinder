document.addEventListener('DOMContentLoaded', function() {
    let xhr = new XMLHttpRequest(); // XMLHttpRequest 객체 생성
    xhr.open('GET', '/get-main-card', true); // 요청을 초기화합니다.

    xhr.onload = function () {
        if (xhr.status >= 200 && xhr.status < 300) {
            // 요청이 성공적으로 완료되면 실행됩니다.
            document.getElementById('cafeCardContainer').innerHTML = xhr.responseText; // 응답을 headerContainer에 삽입

        } else {
            // 서버에서 4xx, 5xx 응답을 반환하면 오류 처리를 합니다.
            console.error('The request failed!');
            if(xhr.status === 401 || xhr.status === 403) {
                reissue();
            }
        }
    };

    xhr.onerror = function () {
        // 요청이 네트워크 문제로 실패했을 때 실행됩니다.
        console.error('The request failed due to a network error!');
    };
    xhr.send(); // 요청을 서버로 보냅니다.
});