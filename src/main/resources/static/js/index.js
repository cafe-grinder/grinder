const maxTags = 3;
const selectedTags = new Set();
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
                console.log(url)
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
    if (document.querySelector('.header_alarm')) {
        const alarm = document.querySelector('.header_alarm');
        let alarm_box = document.querySelector('.header_alarm_box');


    alarm.addEventListener('click', () => {
        if (alarm_box) {
            alarm_box.classList.toggle('alarm_active');
        }
    });

    document.getElementById('aiFriendListItem').addEventListener('click', function() {
        let modal = document.getElementById('alanModal');
        getAlanMessage('/get-alan');
    });
}

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

function getAlanMessage(url) {
        let xhr = new XMLHttpRequest(); // XMLHttpRequest 객체 생성
        xhr.open('GET', url, true); // 요청을 초기화합니다.

        xhr.onload = function () {
            if (xhr.status >= 200 && xhr.status < 300) {
                // 요청이 성공적으로 완료되면 실행됩니다.
                document.getElementById('alanContainer').innerHTML = xhr.responseText; // 응답을 headerContainer에 삽입
                let modal = document.querySelector('.alan_tab');
                let backdrop = document.getElementById('modalBackdrop');
                let container = document.getElementById('alanContainer');
                container.style.display = 'block';
                backdrop.style.display = 'block';
                modal.style.display = 'block';
                closeAlan();
                checkTagBox();
            } else {
                // 서버에서 4xx, 5xx 응답을 반환하면 오류 처리를 합니다.
                console.error('The request failed!');
                if (xhr.status === 401 || xhr.status === 403) {
                    reissue();
                }
            }
        };

        xhr.onerror = function () {
            // 요청이 네트워크 문제로 실패했을 때 실행됩니다.
            console.error('The request failed due to a network error!');
        };

        xhr.send(); // 요청을 서버로 보냅니다.
}

function closeAlan() {
    document.getElementById('modalBackdrop').addEventListener('click', function() {
        let modal = document.getElementById('alanContainer');
        let backdrop = document.getElementById('modalBackdrop');
        modal.style.display = 'none';
        backdrop.style.display = 'none';
    });
}

function checkTagBox() {
        const aiFriendListItem = document.getElementById('aiFriendListItem');
        const modal = document.getElementById('alanContainer');
        const backdrop = document.getElementById('modalBackdrop');
        const submitButton = document.querySelector('.alan_choice');

        const tagButtons = document.querySelectorAll('.message_tag_name');
        tagButtons.forEach(button => {
            button.addEventListener('click', function () {
                const tagName = this.innerText.trim();
                if (selectedTags.has(tagName)) {
                    selectedTags.delete(tagName);
                    button.classList.remove('message_tag_name_active');
                } else {
                    if (selectedTags.size < maxTags) {
                        selectedTags.add(tagName);
                        button.classList.add('message_tag_name_active');
                    } else {
                        alert(`최대 ${maxTags}개의 태그를 선택할 수 있습니다.`);
                    }
                }
            });
        });


        // Submit selected tags
        submitButton.addEventListener('click', function () {
            if (selectedTags.size === 0) {
                alert('태그를 선택해주세요.');
                return;
            }
            const tagsArray = Array.from(selectedTags);
            fetch('/saveAlanTag', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(tagsArray),
            })
                .then(response => {
                    if (response.status >= 200 && response.status < 300) {
                        return response.json().then(data => {
                            alert('태그가 저장되었습니다.');
                            return data;
                        });
                    } else if (response.status === 406) {
                        return response.json().then(answer => {
                            alert(answer.message);
                            throw new Error(answer.message);  // Stop the chain here
                        });
                    } else {
                        throw new Error('Unexpected response status: ' + response.status);
                    }
                })
                .then(data => {
                    console.log('Success:', data);

                    modal.style.display= 'none';
                    backdrop.style.display = 'none';
                })
                .catch((error) => {
                    console.error('Error:', error);
                });
        });
}