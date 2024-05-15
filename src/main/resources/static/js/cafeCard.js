
// 톱니바퀴 버튼
document.addEventListener('DOMContentLoaded', function() {
    const queryString = window.location.search;
    const urlParams = new URLSearchParams(queryString);
    let query = document.getElementById('hidden_query').value;
    let category = urlParams.get('category')
    let page = 0;
    if (category == 'cafe') {
        // query = document.getElementById('hidden_query').value
        let xhr = new XMLHttpRequest(); // XMLHttpRequest 객체 생성
        let url = '/get-cafeCard?query=' + query + '&page=' + page;
        xhr.open('GET', url, true); // 요청을 초기화합니다.
        xhr.onload = function () {
            if (xhr.status >= 200 && xhr.status < 300) {
                // 요청이 성공적으로 완료되면 실행됩니다.
                document.getElementById('cafeCardContainer').innerHTML = xhr.responseText; // 응답을 headerContainer에 삽입
                if (document.querySelectorAll('.cafe_card_info').length == 0) {
                    document.getElementById('cafeCardContainer').innerHTML = `<div class="no_cafe_container"> <p class="no_cafe_message">관련 카페가 존재하지 않습니다. <br> 해당 카페를 추가해주세요!</p>
                        <button onclick="location.href='/cafe/add'" class="cafe_register_button">신규 장소 신청</button></div>`
                    document.getElementById('more_contents_button').classList.add('display_none')
                    } else {
                    const moreBtn = document.getElementById('more_contents_button');
                    moreBtn.addEventListener('click', (e) => {
                        page++;
                        let url = '/get-cafeCard?query=' + query + '&page=' + page;
                        fetch(url, {
                            method: 'GET'
                        })
                                .then(response => {
                                    if (response.status == 204) {
                                        document.getElementById('more_contents_button').classList.add('display_none')
                                    }
                                    return response.text()
                                })
                                .then(data => {
                                    document.getElementById('cafeCardContainer').insertAdjacentHTML('beforeend' ,data)
                                })
                    })
                }
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
    }
});