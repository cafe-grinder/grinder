let myPageMenu = document.querySelector('.mypage_menu_list');
let myMenuButton = document.querySelector('.mypage_menu_button');
let nextPage = 0;
const token = getCookie('access');

document.addEventListener('DOMContentLoaded', function() {
    let xhr = new XMLHttpRequest(); // XMLHttpRequest 객체 생성
    xhr.open('GET', '/get-header', true); // 요청을 초기화합니다.

    const token = getCookie('access');
    if (token) {
        xhr.setRequestHeader('access', 'Bearer ' + token);
    }

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


//햄버거 메뉴 불러오기
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

// 공통 함수로 비동기 요청 및 내용 업데이트 처리
function fetchContent(url, containerId) {
    let xhr = new XMLHttpRequest();
    xhr.open('GET', url, true);

    // TODO : 쿠키로 변경 필요
    const token = getCookie('access');
    if (token) {
        xhr.setRequestHeader('access', 'Bearer ' + token);
    }

    xhr.onload = function () {
        if (xhr.status >= 200 && xhr.status < 300) {
            document.getElementById(containerId).innerHTML = xhr.responseText;
        } else { // TODO : 403 에러 처리 필요
            console.error('The request failed with status:', xhr.status);
            document.querySelector('.myPage_title').innerHTML = '문제가 발생했습니다.';
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
        method: 'GET',
        headers: new Headers()
    };

    if (token) {
        options.headers.append('access', 'Bearer ' + token);
    }

    fetch(url, options)
        .then(response => {
            if (!response.ok) throw new Error('Failed to fetch bookmarks');
            return response.json();
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
            <button onclick="unfollow(${followEmail})">팔로우 해제</button>
        </div>
        `;

        container.insertAdjacentHTML("beforeend", bookmarkHTML);
    });
}

// 북마크 추가 더보기
function MoreContent(url, insertTag) {
    const options = {
        method: 'GET',
        headers: new Headers()
    };

    if (token) {
        options.headers.append('access', 'Bearer ' + token);
    }

    fetch(url, options)
        .then(response => {
            if (!response.ok) throw new Error('Failed to fetch bookmarks');
            return response.json();
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
function unfollow(email) {
    let url = '/api/follow/' + encodeURIComponent(email);

    // 기본 요청 설정
    let fetchOptions = {
        method: 'DELETE',
    };

    // 토큰이 존재하면 헤더를 추가
    if (token) {
        fetchOptions.headers = {
            'access': 'Bearer ' + token  // Authorization 헤더에 JWT 추가
        };
    }

    // AJAX 요청 예시
    fetch(url, fetchOptions)
        .then(response => response.json())
        .then(data => {
            console.log('Success:', data);
            // 성공 시 UI 업데이트 또는 통지
        })
        .catch((error) => {
            console.error('Error:', error);
        });
}

//블랙리스트 해제
function unblock(blackId) {
    let url = '/api/blacklist/' + encodeURIComponent(blackId);

    let fetchOptions = {
        method: 'DELETE',
    };

    // 토큰이 존재하면 헤더를 추가
    if (token) {
        fetchOptions.headers = {
            'access': 'Bearer ' + token  // Authorization 헤더에 JWT 추가
        };
    }

    // AJAX 요청 예시
    fetch(url, fetchOptions)
        .then(response => response.json())
        .then(data => {
            console.log('Success:', data);
            // 성공 시 UI 업데이트 또는 통지
        })
        .catch((error) => {
            console.error('Error:', error);
        });
}

//북마크 해제
function unBookmark(cafeId) {
    let url = 'api/bookmark/' + encodeURIComponent(cafeId);

    let fetchOptions = {
        method: 'DELETE',
    };

    // 토큰이 존재하면 헤더를 추가
    if (token) {
        fetchOptions.headers = {
            'access': 'Bearer ' + token  // Authorization 헤더에 JWT 추가
        };
    }

    // AJAX 요청 예시
    fetch(url, fetchOptions)
        .then(response => response.json())
        .then(data => {
            console.log('Success:', data);
            // 성공 시 UI 업데이트 또는 통지
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

//쿠키 불러오기 메서드
function getCookie(name) {
    const value = `; ${document.cookie}`;
    const parts = value.split(`; ${name}=`);
    if (parts.length === 2) return parts.pop().split(';').shift();
}
