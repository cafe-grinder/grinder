let myPageMenu = document.querySelector('.mypage_menu_list');
let myMenuButton = document.querySelector('.mypage_menu_button');

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
        }
    };

    xhr.onerror = function () {
        // 요청이 네트워크 문제로 실패했을 때 실행됩니다.
        console.error('The request failed due to a network error!');
    };

    xhr.send(); // 요청을 서버로 보냅니다.
});



document.addEventListener('DOMContentLoaded', function() {
    // 팔로워 보기 버튼 이벤트 리스너 추가
    document.getElementById('view_follower').addEventListener('click', function() {
        fetchContent('/get-follower', 'myPageMenuContainer');
    });

    // 팔로잉 보기 버튼 이벤트 리스너 추가
    document.getElementById('view_following').addEventListener('click', function() {
        fetchContent('/get-following', 'myPageMenuContainer');
    });

    // 차단목록 보기 버튼 이벤트 리스너 추가
    document.getElementById('view_blacklist').addEventListener('click', function() {
        fetchContent('/get-blacklist', 'myPageMenuContainer');
    });

});

// 공통 함수로 비동기 요청 및 내용 업데이트 처리
function fetchContent(url, containerId) {
    let xhr = new XMLHttpRequest();
    xhr.open('GET', url, true);

    xhr.onload = function () {
        if (xhr.status >= 200 && xhr.status < 300) {
            document.getElementById(containerId).innerHTML = xhr.responseText;
        } else {
            console.error('The request failed with status:', xhr.status);
        }
    };

    xhr.onerror = function () {
        console.error('The request failed due to a network error!');
    };

    xhr.send();
}

function unfollow(email) {
    let url = '/follow/' + encodeURIComponent(email);

    // AJAX 요청 예시
    fetch(url, {
        method: 'DELETE'
    })
        .then(response => response.json())
        .then(data => {
            console.log('Success:', data);
            // 성공 시 UI 업데이트 또는 통지
        })
        .catch((error) => {
            console.error('Error:', error);
        });
}

function unblock(blackId) {
    let url = '/blacklist/' + encodeURIComponent(blackId);

    // AJAX 요청 예시
    fetch(url, {
        method: 'DELETE'
    })
        .then(response => response.json())
        .then(data => {
            console.log('Success:', data);
            // 성공 시 UI 업데이트 또는 통지
        })
        .catch((error) => {
            console.error('Error:', error);
        });
}

if (myMenuButton) {
    myMenuButton.addEventListener('click', () => {
        if (myPageMenu) {
            myPageMenu.classList.toggle('menu_active');
        }
    });
}

XMLHttpRequest.HEADERS_RECEIVED.
localStorage.getItem("access")