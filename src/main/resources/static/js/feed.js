// 백-프론트 연동
document.addEventListener('DOMContentLoaded', function() {
    let xhr = new XMLHttpRequest(); // XMLHttpRequest 객체 생성
    xhr.open('GET', '/get-feed', true); // 요청을 초기화합니다.
    xhr.onload = function () {
        if (xhr.status >= 200 && xhr.status < 300) {
            // 요청이 성공적으로 완료되면 실행됩니다.
            document.getElementById('feedContainer').innerHTML = xhr.responseText; // 응답을 headerContainer에 삽입
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
})

// 톱니바퀴 버튼
document.addEventListener('DOMContentLoaded', function() {
    // feed_gear_btn 클래스를 가진 버튼 요소들을 모두 가져옴
    const gearButtons = document.querySelectorAll('.feed_gear_btn');

    // 가져온 버튼 요소들에 대해 반복하여 이벤트 리스너를 추가
    gearButtons.forEach(function(button) {
        // 클릭 이벤트에 대한 리스너 추가
        button.addEventListener('click', function(event) {
            // 이벤트 버블링 방지
            event.stopPropagation();

            // 현재 클릭된 버튼과 그 버튼의 드롭다운을 가져옴
            const currentButton = this;
            const currentDropdown = currentButton.parentElement.querySelector('.feed_gear_dropdown');

            // 다른 모든 드롭다운을 닫음
            hideAllDropdowns(currentDropdown);

            // 현재 클릭된 버튼의 드롭다운을 토글
            currentDropdown.classList.toggle('show');
        });
    });

    // 문서의 다른 곳을 클릭했을 때 모든 드롭다운을 숨기는 함수
    function hideAllDropdowns(excludeDropdown) {
        const dropdowns = document.querySelectorAll('.feed_gear_dropdown.show');
        dropdowns.forEach(function(dropdown) {
            // 현재 클릭된 버튼의 드롭다운을 제외하고 모든 다른 드롭다운을 닫음
            if (dropdown !== excludeDropdown) {
                dropdown.classList.remove('show');
            }
        });
    }

    // 문서의 어느 곳을 클릭하든 모든 드롭다운을 닫음
    document.addEventListener('click', function(event) {
        hideAllDropdowns();
    });

    // 드롭다운 메뉴를 클릭해도 닫히지 않도록 이벤트 버블링을 방지
    document.querySelectorAll('.feed_gear_dropdown').forEach(function(dropdown) {
        dropdown.addEventListener('click', function(event) {
            event.stopPropagation();
        });
    });
});
