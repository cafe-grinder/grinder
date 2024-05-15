document.addEventListener('DOMContentLoaded', function() {
    let xhr = new XMLHttpRequest(); // XMLHttpRequest 객체 생성
    xhr.open('GET', '/get-header', true); // 요청을 초기화합니다.

    xhr.onload = function () {
        if (xhr.status >= 200 && xhr.status < 300) {
            // 요청이 성공적으로 완료되면 실행됩니다.
            document.getElementById('headerContainer').innerHTML = xhr.responseText; // 응답을 headerContainer에 삽입
            alarmTab();

            const searchForm = document.getElementById('search_form')
            const input = document.getElementById('search_query')
            const select = document.getElementById('search_category')
            const queryString = window.location.search;
            const urlParams = new URLSearchParams(queryString);
            let query = decodeURIComponent(urlParams.get('query'));
            let category = urlParams.get('category')

            if (query != null && query != 'null') {
                input.value = query
            }
            if (category != null) {
                select.value = category
            }
            searchForm.addEventListener('submit', (e) => {
                e.preventDefault()
                search()
            });

            function search() {
                query = input.value
                category = select.value
                let url = '/search?category=' + category + '&query=' + query
                location.href = url
            }


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

function alarmTab() {
    const alarm = document.querySelector('.header_alarm');
    let alarm_box = document.querySelector('.header_alarm_box');

    alarm.addEventListener('click', () => {
        if (alarm_box) {
            alarm_box.classList.toggle('alarm_active');
        }
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

document.addEventListener('DOMContentLoaded', () => {
    const queryString = window.location.search;
    if (queryString.length > 0) {
        const urlParams = new URLSearchParams(queryString);
        let query = urlParams.get('query');
        let category = urlParams.get('category')
        if (category == 'member') {
            let page = 0;
            let url = '/get-search-member?query=' + query + '&page=' + page;
            fetch(url, {
                method: 'GET'
            })
                    .then(response => {
                        if (!(response.status >= 200 && response.status < 300)) {
                            throw new Error("Request failed")
                        }
                        return response.text()
                    })
                    .then(data => {
                        document.getElementById('memberContainer').innerHTML = data;
                        if (document.querySelectorAll('.followerList_follower').length == 0) {
                            document.getElementById('memberContainer').innerHTML = `<div class="no_cafe_container"> <p class="no_cafe_message">관련 회원이 존재하지 않습니다.</p>`
                            document.getElementById('more_contents_button').classList.add('display_none')
                        } else {
                                const moreBtn = document.getElementById('more_contents_button');
                                moreBtn.addEventListener('click', (e) => {
                                    page++;
                                    let url = '/get-search-member?query=' + query + '&page=' + page;
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
                                                document.getElementById('memberContainer').insertAdjacentHTML('beforeend' ,data)
                                            })
                                })
                        }
                    })
        }
    } else {
        document.getElementById('more_contents_button').classList.add('display_none')
    }
})
