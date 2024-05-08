document.addEventListener('DOMContentLoaded', function() {
    // 백엔드에서 피드를 가져오는 XMLHttpRequest
    let xhr = new XMLHttpRequest(); // XMLHttpRequest 객체 생성
    xhr.open('GET', '/get-feed', true); // 요청을 초기화합니다.
    xhr.onload = function () {
        if (xhr.status >= 200 && xhr.status < 300) {
            // 요청이 성공적으로 완료되면 실행됩니다.
            document.getElementById('feedContainer').innerHTML = xhr.responseText; // 응답을 headerContainer에 삽입
            initPage(); // 페이지 초기화 함수 호출
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

    // 페이지 초기화 함수
    function initPage() {
        // 톱니바퀴 드롭다운 초기화
        initGearDropdown();

        // 피드 삭제 이벤트 설정
        initDeleteFeedEvent();

        // 댓글 작성 이벤트 설정
        initCommentEvent();
    }

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

    // 피드 삭제 이벤트 설정
    function initDeleteFeedEvent() {
        const deleteButtons = document.querySelectorAll('.feed_delete_btn');
        deleteButtons.forEach(function(btn) {
            btn.addEventListener('click', async function() {
                // 피드 ID 가져오기
                const feedId = this.closest('.feed_container').querySelector('.feed_feed_id').value;

                // 확인 대화상자 표시
                if (confirm('삭제하시겠습니까?')) {
                    // 사용자가 확인을 선택한 경우 AJAX 요청을 통해 피드 삭제
                    await deleteFeed(feedId);
                    // 삭제 후 해당 피드를 화면에서 숨깁니다.
                    this.closest('.feed_container').style.display = 'none';
                }
            });
        });
    }

    // 댓글 작성 이벤트 설정
    function initCommentEvent() {
        document.addEventListener('click', async function(event) {
            const target = event.target;

            // 댓글 작성 버튼 클릭 시
            if (target.classList.contains('feed_comment_create_btn')) {
                let commentTextarea;
                let content;
                let parentCommentId;

                const childCommentWrite = target.closest('.feed_child_comment_write');
                if (childCommentWrite) {
                    commentTextarea = childCommentWrite.querySelector('.feed_comment_textarea');
                    content = commentTextarea.value;
                    parentCommentId = target.closest('.feed_comment').querySelector('.feed_parent_comment_id').value;
                } else {
                    commentTextarea = target.closest('.feed_parent_comment_write').querySelector('.feed_comment_textarea');
                    content = commentTextarea.value;
                    parentCommentId = '';
                }

                // 댓글 내용이 비어있는지 확인
                if (!content.trim()) {
                    alert('댓글 내용을 입력하세요.');
                    return;
                }

                // AJAX를 통해 댓글 저장
                const feedId = target.closest('.feed_container').querySelector('.feed_feed_id').value;
                await saveComment(content, parentCommentId, feedId);

                // 댓글 작성 후 입력창 초기화
                commentTextarea.value = '';
            }
        });
    }
});

// 피드 삭제 비동기 함수
async function deleteFeed(feedId) {
    try {
        const response = await fetch(`/feed/${feedId}`, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json'
            }
        });

        if (response.ok) {
            const result = await response.json();
            console.log(result.message); // 성공 메시지 출력
            // 피드를 화면에서 제거하는 등의 추가적인 동작을 수행할 수 있습니다.
        } else {
            // 실패했을 때의 처리
            console.error('피드 삭제에 실패했습니다.');
        }
    } catch (error) {
        console.error('피드 삭제 중 오류가 발생했습니다:', error);
    }
}

// 댓글 저장 비동기 함수
async function saveComment(content, parentCommentId, feedId) {
    try {
        const response = await fetch(`/comment/${feedId}/newcomment`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                content: content,
                parentCommentId: parentCommentId
            })
        });

        if (response.ok) {
            const result = await response.json();
            console.log(result.message); // 성공 메시지 출력

            // 성공적으로 댓글이 저장된 후에 필요한 추가 작업 수행 가능
        } else {
            // 실패했을 때의 처리
            console.error('댓글 저장에 실패했습니다.');
        }
    } catch (error) {
        console.error('댓글 저장 중 오류가 발생했습니다:', error);
    }
}
