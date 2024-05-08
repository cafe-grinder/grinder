document.addEventListener('DOMContentLoaded', function() {
    // 백엔드에서 피드를 가져오는 XMLHttpRequest
    let xhr = new XMLHttpRequest(); // XMLHttpRequest 객체 생성
    xhr.open('GET', '/get-feed', true); // 요청을 초기화합니다.
    xhr.onload = function () {
        if (xhr.status >= 200 && xhr.status < 300) {
            // 요청이 성공적으로 완료되면 실행됩니다.
            document.getElementById('feedContainer').innerHTML = xhr.responseText; // 응답을 headerContainer에 삽입
            initGearDropdown(); // 톱니바퀴 드롭다운 초기화
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

    // 톱니바퀴 드롭다운 초기화
    function initGearDropdown() {
        const gearBtns = document.querySelectorAll('.feed_gear_btn');
        gearBtns.forEach(function(btn) {
            btn.addEventListener('click', function(event) {
                const dropdown = this.nextElementSibling;
                dropdown.classList.toggle('show');

                // 다른 dropdown이 열려있는 경우 닫음
                const allDropdowns = document.querySelectorAll('.feed_gear_dropdown');
                allDropdowns.forEach(function(dropdownItem) {
                    if (dropdownItem !== dropdown) {
                        dropdownItem.classList.remove('show');
                    }
                });

                // 화면 어느 곳이나 클릭할 때 드롭다운 닫기
                document.addEventListener('click', function(e) {
                    if (!dropdown.contains(e.target) && !btn.contains(e.target)) {
                        dropdown.classList.remove('show');
                    }
                });
            });
        });
    }
});
