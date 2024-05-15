let cafe_id = document.getElementById("cafeId").value;

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
            if (url === '/get-addpage') {
                myMenuImageSelectEvent();
                MyMenuClickEvent();
                saveMenuForm();
            }
        } else {
            console.error('The request failed with status:', xhr.status);
            if (url === '/get-mymenu/'+cafe_id) {
                document.getElementById(containerId).innerHTML = '아직 메뉴가 등록되지 않았어요!';
            } else {
                document.getElementById(containerId).innerHTML = '문제가 발생했습니다.';
            }
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

//메뉴 추가 관련

function myMenuImageSelectEvent() {
    document.getElementById('image-btn').addEventListener('click', function (event) {
        document.getElementById('file-input').click();
    })

    document.getElementById('file-input').addEventListener('change', function(event) {
        var file = event.target.files[0]; // 파일 선택
        var reader = new FileReader(); // FileReader 객체 생성

        reader.onload = function(event) {
            var imageUrl = event.target.result; // 이미지 URL 가져오기

            var imageElement = document.createElement('img'); // img 요소 생성
            imageElement.src = imageUrl; // 이미지 URL 설정

            var imageContainer = document.getElementById('img-box'); // 이미지를 추가할 div 태그 가져오기
            imageContainer.innerHTML = ''; // 기존에 있는 이미지 삭제
            imageContainer.style.height = '240px';
            imageContainer.style.marginBottom = '40px';
            imageContainer.appendChild(imageElement); // 이미지를 div 태그에 추가
        };

        reader.readAsDataURL(file); // 파일을 읽어서 이미지 URL 생성
    });

}
function saveMenuForm() {
    document.getElementById('menu-form').addEventListener('submit', async function(event) {
        event.preventDefault(); // 폼의 기본 제출 동작 방지

        const menuName = document.getElementById('menu-name').value;
        const menuPrice = document.getElementById('menu-price').value;
        const menuType = document.getElementById('menu-type').value;
        const menuIsLimited = document.getElementById('menu-is-limited').value;

        if (!menuName || !menuPrice || !menuType || menuIsLimited === '') {
            alert('메뉴명, 가격, 메뉴 타입과 한정메뉴 여부는 반드시 기입해주세요');
            return;
        }

        await submitMenuForm(event); // 폼 데이터 제출 함수 호출
    });

    function submitMenuForm(event) {
        event.preventDefault(); // 폼의 기본 제출 동작 방지
        const formData = new FormData();
        const fileInput = document.getElementById('file-input');

        if (fileInput.files.length > 0) {
            formData.append('menuImage', fileInput.files[0]); // 파일 추가
        }

        // 다른 폼 필드는 이미 FormData에 자동으로 포함되어 있을 수 있으므로, 별도의 추가는 필요 없습니다.
        // 다음과 같이 필요한 경우 추가적인 데이터를 추가할 수 있습니다.
        formData.append('cafeId', cafe_id);
        formData.append('menuName', document.getElementById('menu-name').value);
        formData.append('menuPrice', document.getElementById('menu-price').value);
        formData.append('menuVolume', document.getElementById('menu-volume').value);
        formData.append('menuAllergy', document.getElementById('menu-allergens').value);
        formData.append('menuDetails', document.getElementById('menu-description').value);
        formData.append('menuType', document.getElementById('menu-type').value); // 이 부분은 선택된 타입에 따라 값을 설정해야 할 수 있습니다.
        formData.append('menuIsLimited', document.getElementById('menu-is-limited').value);

        // FormData의 내용 확인을 위한 로그 출력
        for (let [key, value] of formData.entries()) {
            console.log(`${key}: ${value}`);
        }

        fetch('/api/menu', {
            method: 'POST',
            body: formData,
            // 서버에서 'multipart/form-data' 처리가 필요하므로 Content-Type 헤더는 설정하지 않습니다.
        }).then(response => {
            if (response.ok) {
                return response.json();
            } else {
                throw new Error('Network response was not ok.');
            }
        }).then(data => {
            console.log('Success:', data);
            alert('메뉴가 추가되었습니다!');
            //새로고침
            window.location.reload();
        }).catch(error => {
            console.error('Error:', error);
            alert('메뉴 추가에 실패했습니다.');
        });
    }
}
function MyMenuClickEvent() {
    document.addEventListener('click', async function(event) {
        const target = event.target;

        // 메뉴 타입 클릭
        if (target.classList.contains('menuInfo_type_name')) {
            // 다른 메뉴 타입의 활성화를 제거
            document.querySelectorAll('.menuInfo_type_name').forEach(button => {
                button.classList.remove('menuInfo_type_name_active');
            });

            // 새로 선택된 메뉴 타입 활성화
            target.classList.add('menuInfo_type_name_active');
            document.getElementById('menu-type').value = target.textContent; // 폼의 hidden input에 선택된 메뉴 타입 설정
        }

        // 한정 메뉴 여부 클릭
        if (target.classList.contains('menuInfo_Limited')) {
            // 다른 메뉴 타입의 활성화를 제거
            document.querySelectorAll('.menuInfo_Limited').forEach(button => {
                button.classList.remove('menuInfo_Limited_active');
            });

            // 새로 선택된 메뉴 타입 활성화
            target.classList.add('menuInfo_Limited_active');
            document.getElementById('menu-is-limited').value = target.textContent; // 폼의 hidden input에 선택된 메뉴 타입 설정
        }
    });
}