let myPageMenu = document.querySelector('.mypage_menu_list');
let myMenuButton = document.querySelector('.mypage_menu_button');
let followButton = document.querySelector('.add_follow');
let nextPage = 0;

document.addEventListener('DOMContentLoaded', function() {
    let xhr = new XMLHttpRequest(); // XMLHttpRequest 객체 생성
    xhr.open('GET', '/get-header', true); // 요청을 초기화합니다.


    xhr.onload = function () {
        if (xhr.status >= 200 && xhr.status < 300) {
            // 요청이 성공적으로 완료되면 실행됩니다.
            document.getElementById('headerContainer').innerHTML = xhr.responseText; // 응답을 headerContainer에 삽입
            alarmTab();
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

    //팔로우 취소 버튼
    followButton.addEventListener('click', function () {
        let method = 'GET';
        let url = '/api/follow/' + memberEmail;
        if (followButton.innerText === "팔로우") {
            method = 'POST';
        } else if (followButton.innerText === "팔로우 취소") {
            method = 'DELETE';
        }
        MemberPageFollow(method, url);
    });
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


//햄버거 메뉴 불러오기
if (document.querySelector('.mypage_menu')) {
document.addEventListener('DOMContentLoaded', function() {
    // 팔로워 보기 버튼 이벤트 리스너 추가
    document.getElementById('view_follower').addEventListener('click', function() {
        nextPage = 0;
        document.querySelector('.myPage_title').innerHTML = '팔로워 보기';
        fetchContent('/get-follower', 'myPageMenuContainer');
    });

    // myPageContainer에 이벤트 위임 설정(팔로워)
    document.getElementById('myPageMenuContainer').addEventListener('click', function(event) {
        if (event.target.classList.contains('follower_more')) {
            nextPage++; // 페이지 번호 증가
            MoreFollowContent('/api/follower?page=' + nextPage, '.followerList');
        }
    });

    // 팔로잉 보기 버튼 이벤트 리스너 추가
    document.getElementById('view_following').addEventListener('click', function() {
        nextPage = 0;
        document.querySelector('.myPage_title').innerHTML = '팔로잉 보기';
        fetchContent('/get-following', 'myPageMenuContainer');
    });

    // myPageContainer에 이벤트 위임 설정(팔로잉)
    document.getElementById('myPageMenuContainer').addEventListener('click', function(event) {
        if (event.target.classList.contains('following_more')) {
            nextPage++; // 페이지 번호 증가
            MoreFollowContent('/api/following?page=' + nextPage, '.followerList');
        }
    });

    // 차단목록 보기 버튼 이벤트 리스너 추가
    document.getElementById('view_blacklist').addEventListener('click', function() {
        document.querySelector('.myPage_title').innerHTML = '차단목록 보기';
        fetchContent('/get-blacklist', 'myPageMenuContainer');
    });

    // 북마크 보기 버튼 이벤트 리스너 추가
    document.getElementById('view_bookmark').addEventListener('click', function() {
        nextPage = 0;
        document.querySelector('.myPage_title').innerHTML = '북마크 보기';
        fetchContent('/get-bookmark', 'myPageMenuContainer');
    });

    // myPageContainer에 이벤트 위임 설정
    document.getElementById('myPageMenuContainer').addEventListener('click', function(event) {
        if (event.target.classList.contains('bookmark_more')) {
            nextPage++; // 페이지 번호 증가
            MoreContent('/api/bookmark?page=' + nextPage, '.bookmarkList');
        }
    });

    // 카페 목록 보기 버튼 이벤트 리스너 추가
    document.getElementById('view_mycafelist').addEventListener('click', function() {
        document.querySelector('.myPage_title').innerHTML = '내 카페 목록';
        fetchContent('/get-mycafe', 'myPageMenuContainer');
    });

});
}

// 공통 함수로 비동기 요청 및 내용 업데이트 처리
function fetchContent(url, containerId) {
    let xhr = new XMLHttpRequest();
    xhr.open('GET', url, true);

    xhr.onload = function () {
        if (xhr.status >= 200 && xhr.status < 300) {
            document.getElementById(containerId).innerHTML = xhr.responseText;
        } else { // TODO : 403 에러 처리 필요
            console.error('The request failed with status:', xhr.status);
            document.querySelector('.myPage_title').innerHTML = '문제가 발생했습니다.';
            if(xhr.status === 401 || xhr.status === 403) {
                reissue();
            }
        }
    };

    xhr.onerror = function () {
        console.error('The request failed due to a network error!');
        document.querySelector('.myPage_title').innerHTML = '문제가 발생했습니다.';
    };

    xhr.send();
}

// 팔로워 추가 더보기
function MoreFollowContent(url, insertTag) {
    const options = {
        method: 'GET'
    };

    fetch(url, options)
        .then(response => {
            if (!response.ok) { // 'ok' 상태가 false라면 상태 코드가 200-299 범위에 있지 않다는 것을 의미
                if (response.status === 401 || response.status === 403) {
                    reissue(); // 여기서 reissue는 인증 토큰을 재발급 받는 함수로 가정
                }
            } else {
                return response.json(); // 응답을 JSON으로 파싱
            }
        })
        .then(data => {
            renderFollowList(data, insertTag);
        })
        .catch(error => {
            console.error('Error:', error);
        });
}

function renderFollowList(follows, insertTag) {
    let container = document.querySelector(insertTag);
    follows.forEach(follow => {
        let followImage = "../img/basic-user.png";
        if(`${follow.followImage}` !== "null") {
            followImage = `${follow.followImage}`;
        }
        let followNickname = `${follow.followNickname}` ? `${follow.followNickname}` : "-";
        let followEmail = `${follow.followEmail}` ? `${follow.followEmail}` : "-";
        let followRole = '';
        switch (`${follow.followRole}`) {
            case "인증회원" : followRole = "../img/icon/award-fill.png"; break;
            case "판매자" : followRole = "../img/icon/check-fill.png"; break;
            default : followRole = '';
        }

        const bookmarkHTML = `
            <div class="followerList_follower">
            <img src="${followImage}">
            <p>${followNickname} (${followEmail})</p>
            <span class="followerList_role">
                <img src="${followRole}"/>
            </span>
            <button onclick="unfollow(${followEmail}, event)">팔로우 해제</button>
        </div>
        `;

        container.insertAdjacentHTML("beforeend", bookmarkHTML);
    });
}

// 북마크 추가 더보기
function MoreContent(url, insertTag) {
    const options = {
        method: 'GET'
    };

    fetch(url, options)
        .then(response => {
            if (!response.ok) { // 'ok' 상태가 false라면 상태 코드가 200-299 범위에 있지 않다는 것을 의미
                if (response.status === 401 || response.status === 403) {
                    reissue(); // 여기서 reissue는 인증 토큰을 재발급 받는 함수로 가정
                }
            } else {
                return response.json(); // 응답을 JSON으로 파싱
            }
        })
        .then(data => {
            renderBookmarkList(data, insertTag);
        })
        .catch(error => {
            console.error('Error:', error);
        });
}

function renderBookmarkList(bookmarks, insertTag) {
    let container = document.querySelector(insertTag);
    bookmarks.forEach(bookmark => {
        let imageUrl = "../img/default-cafe-logo.png";
        if(`${bookmark.cafeImageUrl}` !== "null") {
            imageUrl = `${bookmark.cafeImageUrl}`;
        }
        let cafeName = `${bookmark.cafeName}` ? `${bookmark.cafeName}` : "-";
        let cafeAddress = `${bookmark.cafeAddress}` ? `${bookmark.cafeAddress}` : "-";
        console.log(`${bookmark.cafePhoneNum}`);
        let cafePhoneNum = `${bookmark.cafePhoneNum}` ? `${bookmark.cafePhoneNum}` : "-";
        let starsHTML = '';

        for (let i = 1; i <= 5; i++) {
            if (i <= bookmark.averageGrade) {
                starsHTML += '<img src="/img/icon/star-fill.png">';
            }
        }

        const bookmarkHTML = `
            <div class="bookmark">
                <img src="${imageUrl}">
                <ul class="bookmarkList_cafeInfo">
                    <li class="bookmark_name">${cafeName}</li>
                    <li class="bookmark_address">${cafeAddress} (${cafePhoneNum})</li>
                    <li class="bookmark_score">평균 평점 :
                        <span id="starsContainer">
                            ${starsHTML}
                        </span>
                    </li>
                </ul>
                <button onclick="unBookmark(${bookmark.cafeId})">삭제하기</button>
            </div>
        `;

        container.insertAdjacentHTML("beforeend", bookmarkHTML);
    });
}
//북마크 추가 더보기 끝

//팔로우 취소
function unfollow(email, event) {
    let url = '/api/follow/' + encodeURIComponent(email);
    console.log(url)
    // 기본 요청 설정
    let fetchOptions = {
        method: 'DELETE',
    };


    fetch(url, fetchOptions)
        .then(response => {
            if (!response.ok) { // 'ok' 상태가 false라면 상태 코드가 200-299 범위에 있지 않다는 것을 의미
                if (response.status === 401 || response.status === 403) {
                    reissue(); // 여기서 reissue는 인증 토큰을 재발급 받는 함수로 가정
                }
            } else {
                return response.json(); // 응답을 JSON으로 파싱
            }
        })
        .then(data => {
            console.log('Success:', data);
            // 성공 시 UI 업데이트 또는 통지
            const article = event.target.closest('article');
            if (article) {
                article.style.display = "none"; // 부모 article 요소 none
            }
        })
        .catch((error) => {
            console.error('Error:', error);
        });
}

//블랙리스트 해제
function unblock(blackId, event) {
    let url = '/api/blacklist/' + encodeURIComponent(blackId);
    console.log(url)
    let fetchOptions = {
        method: 'DELETE',
    };

    // AJAX 요청 예시
    fetch(url, fetchOptions)
        .then(response => {
            if (!response.ok) { // 'ok' 상태가 false라면 상태 코드가 200-299 범위에 있지 않다는 것을 의미
                if (response.status === 401 || response.status === 403) {
                    reissue(); // 여기서 reissue는 인증 토큰을 재발급 받는 함수로 가정
                }
            } else {
                return response.json(); // 응답을 JSON으로 파싱
            }
        })
        .then(data => {
            console.log('Success:', data);
            // 성공 시 UI 업데이트 또는 통지
            const article = event.target.closest('article');
            if (article) {
                article.style.display = "none"; // 부모 article 요소 none
            }
        })
        .catch((error) => {
            console.error('Error:', error);
        });
}

//북마크 해제
function unBookmark(cafeId, event) {
    let url = '/api/bookmark/' + encodeURIComponent(cafeId);

    let fetchOptions = {
        method: 'DELETE',
    };

    // AJAX 요청 예시
    fetch(url, fetchOptions)
        .then(response => {
            if (!response.ok) { // 'ok' 상태가 false라면 상태 코드가 200-299 범위에 있지 않다는 것을 의미
                if (response.status === 401 || response.status === 403) {
                    reissue(); // 여기서 reissue는 인증 토큰을 재발급 받는 함수로 가정
                }
            } else {
                return response.json(); // 응답을 JSON으로 파싱
            }
        })
        .then(data => {
            console.log('Success:', data);
            // 성공 시 UI 업데이트 또는 통지
            const section = event.target.closest('section');
            if (section) {
                section.style.display = "none"; // 부모 section 요소 none
            }
        })
        .catch((error) => {
            console.error('Error:', error);
        });
}

//햄버거 메뉴 활성화
if (myMenuButton) {
    myMenuButton.addEventListener('click', () => {
        if (myPageMenu) {
            myPageMenu.classList.toggle('menu_active');
        }
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

//마이페이지 팔로우 버튼
function MemberPageFollow(method, url) {
    let xhr = new XMLHttpRequest();
    xhr.open(method, url, true);

    xhr.onload = function () {
        if (xhr.status >= 200 && xhr.status < 300) {
            alert("완료되었습니다.")
            if (followButton.innerText === "팔로우") {
                followButton.innerText = '팔로우 취소';
            } else if (followButton.innerText === "팔로우 취소") {
                followButton.innerText = '팔로우';
            }
            window.location.reload();
        } else { // TODO : 403 에러 처리 필요
            let response = JSON.parse(xhr.responseText);
            alert(response.message);
            console.error('The request failed with status:', xhr.status);
            document.querySelector('.myPage_title').innerHTML = '문제가 발생했습니다.';
            if(xhr.status === 401 || xhr.status === 403) {
                reissue();
            }
            window.location.reload();
        }
    };

    xhr.onerror = function () {
        console.error('The request failed due to a network error!');
        document.querySelector('.myPage_title').innerHTML = '문제가 발생했습니다.';
    };

    xhr.send();
}