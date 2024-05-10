let cafe_id = document.getElementById("cafeId").value;


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

document.addEventListener('DOMContentLoaded', function() {
    // 카페 오픈 시간 수정
    document.querySelector('.myCafe_info_modify').addEventListener('click', function () {
        fetchContent('/get-opening', 'myCafeInfoContainer', 'GET');
    });
    //myCafeInfoContainer에 이벤트 위임 설정(영업시간 수정)
    document.getElementById('myCafeInfoContainer').addEventListener('submit', function(event) {
        if (event.target.classList.contains('update_opening_form')) {
            event.preventDefault();
            saveOpening(event);
        }
    });
    // 메뉴 추가
    document.querySelector('.myCafe_menu_add').addEventListener('click', function () {
        fetchContent('/get-addpage', 'myCafeInfoContainer', 'GET');
    });

    // 메뉴 리스트 불러오기
    document.querySelector('.myCafe_menu_list').addEventListener('click', function () {
        fetchContent('/get-mymenu/'+cafe_id, 'myCafeInfoContainer', 'GET');
    });
    //메뉴 삭제하기
    document.getElementById('myCafeInfoContainer').addEventListener('click', function(event) {
        if (event.target.classList.contains('menu_delete')) {
            deleteMenu(event.target.dataset.menuid ,event);
        }
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

let update_opening = document.getElementById('cafe-btn');

//TODO: 이벤트 위임으로 처리

function saveOpening(event) {
    event.preventDefault(); // 폼 기본 제출 방지

        const days = ['monday', 'tuesday', 'wednesday', 'thursday', 'friday', 'saturday', 'sunday'];
        let formData = [];

        days.forEach(day => {
            let openTime = document.querySelector(`input[name="${day}_open"]`).value;
            let closeTime = document.querySelector(`input[name="${day}_close"]`).value;
            let isHoliday = document.querySelector(`input[name="${day}_holiday"]`).checked;

            if (!isHoliday && (!validateTime(openTime) || !validateTime(closeTime) || !openTime >= closeTime)) {
                alert(`${day.charAt(0).toUpperCase() + day.slice(1)} 올바르지 않은 형식입니다.`);
                return; // 오류 발생 시 함수 종료
            }

            formData.push({
                day: day,
                openTime: openTime,
                closeTime: closeTime,
                isHoliday: isHoliday
            });
        });

        console.log(JSON.stringify(formData));
    console.log(formData);
        // 데이터를 JSON으로 변환하여 서버에 전송
        fetch('/api/saveOpeningHours/' + cafe_id, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(formData)
        })
            .then(response => response.json())
            .then(data => {
                console.log('Success:', data);
                })
            .catch((error) => console.error('Error:', error));
};

function validateTime(time) {
    const regex = /^([01]?[0-9]|2[0-3]):[0-5][0-9]$/;
    return regex.test(time);
}


function deleteMenu(menuId, event) {
    let url = `/api/myMenu/${menuId}?cafeId=${encodeURIComponent(cafe_id)}`;

    let fetchOptions = {
        method: 'DELETE',
    };

    fetch(url, fetchOptions)
        .then(data => {
            const article = event.target.closest('article');
            if (article) {
                article.style.display = "none"; // 부모 article 요소 none
            }
        })
        .catch((error) => {
            console.error('Error:', error);
        });
}