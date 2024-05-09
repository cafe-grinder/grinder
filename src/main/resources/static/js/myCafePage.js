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

document.addEventListener('DOMContentLoaded', function() {
    // 카페 오픈 시간 수정
    document.querySelector('.myCafe_info_modify').addEventListener('click', function () {
        fetchContent('/get-opening', 'myCafeInfoContainer', 'GET');
    });
    // 메뉴 추가
    document.querySelector('.myCafe_menu_add').addEventListener('click', function () {
        fetchContent('/get-addpage', 'myCafeInfoContainer', 'GET');
    });

    // 메뉴 리스트 불러오기
    document.querySelector('.myCafe_menu_list').addEventListener('click', function () {
        fetchContent('/get-mymenu', 'myCafeInfoContainer', 'GET');
    });
});

// 공통 함수로 비동기 요청 및 내용 업데이트 처리
function fetchContent(url, containerId, method) {
    let xhr = new XMLHttpRequest();
    xhr.open(method, url, true);

    xhr.onload = function () {
        if (xhr.status >= 200 && xhr.status < 300) {
            document.getElementById(containerId).innerHTML = xhr.responseText;
        } else {
            console.error('The request failed with status:', xhr.status);
            document.getElementById(containerId).innerHTML = '문제가 발생했습니다.';
            if(xhr.status === 401 || xhr.status === 403) {
                reissue();
            }
        }
    };

    xhr.onerror = function () {
        console.error('The request failed due to a network error!');
        document.getElementById(containerId).innerHTML = '문제가 발생했습니다.';
    };

    xhr.send();
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

