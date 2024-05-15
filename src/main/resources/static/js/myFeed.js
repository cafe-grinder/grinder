let memberEmail = document.getElementById('memberEmail').value;
let url = '/get-myFeed/'+ memberEmail;
let containerName = 'myPageMenuContainer';
let pageNum = 1;

document.addEventListener('DOMContentLoaded', function() {
    // 백엔드에서 피드를 가져오는 XMLHttpRequest
    let xhr = new XMLHttpRequest(); // XMLHttpRequest 객체 생성
    xhr.open('GET', url, true); // 요청을 초기화합니다.
    xhr.onload = function () {
        if (xhr.status >= 200 && xhr.status < 300) {
            // 요청이 성공적으로 완료되면 실행됩니다.
            document.getElementById(containerName).insertAdjacentHTML("afterbegin", xhr.responseText); // 응답을 headerContainer에 삽입
            FeedClickEvent(); // 클릭 이벤트 함수 호출
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

    // 피드 이벤트 설정
    function FeedClickEvent() {
        document.addEventListener('click', async function(event) {
            const target = event.target;

            // 다른 dropdown이 열려있는 경우 닫음
            const allDropdowns = document.querySelectorAll('.feed_gear_dropdown');
            allDropdowns.forEach(function(dropdownItem) {
                dropdownItem.classList.add('display_none');
            });
            // 톱니바퀴 버튼 클릭
            if (target.classList.contains('feed_gear_btn')) {
                const dropdown = target.closest('.feed_gear_btn_parent').querySelector('.feed_gear_dropdown');
                dropdown.classList.toggle('display_none');
            }

            // 좋아요 버튼 클릭
            if (target.classList.contains('feed_like') || target.classList.contains('feed_like_yet')) {
                let contentId;
                let contentType;

                if (target.classList.contains('feed_parent_comment')) { // 부모 댓글
                    contentId = target.closest('.feed_parent_comment_area').querySelector('.feed_parent_comment_id').value;
                    contentType = 'COMMENT';
                } else if (target.classList.contains('feed_child_comment')) {   // 자식 댓글
                    contentId = target.closest('.feed_child_comment_area').querySelector('.feed_child_comment_id').value;
                    contentType = 'COMMENT';
                } else {    // 피드
                    contentId = target.closest('.feed_container').querySelector('.feed_feed_id').value;
                    contentType = 'FEED';
                }

                const likeForm = target.closest('.feed_like_num_align');
                let likeNum = parseInt(likeForm.querySelector('.feed_like_num').innerHTML);
                if (target.classList.contains('feed_like')) {   // 좋아요 취소
                    deleteHeart(contentId, contentType);
                    likeForm.innerHTML = `<button class="feed_like_yet"></button><span class="feed_like_num">${likeNum-1}</span>`;
                } else {    // 좋아요
                    addHeart(contentId, contentType);
                    likeForm.innerHTML = `<button class="feed_like"></button><span class="feed_like_num">${likeNum+1}</span>`;
                }
            }

            // 댓글 보기 버튼 클릭
            if (target.classList.contains('feed_comment_view_btn')) {
                const commentContainer = target.closest('.feed_container').querySelector('.feed_comment_container');
                commentContainer.classList.toggle('display_none');
            }

            // 답글 입력창 토글 버튼
            if (target.classList.contains('feed_child_comment_textarea_view_btn')) {
                const childCommentWriteArea = target.closest('.feed_parent_comment_info').querySelector('.feed_child_comment_write');
                childCommentWriteArea.classList.toggle('display_none');

                const allCommentArea = document.querySelectorAll('.feed_child_comment_write');
                allCommentArea.forEach(function(commentArea) {
                    if (commentArea !== childCommentWriteArea) {
                        commentArea.classList.add('display_none');
                    }
                });

                const commentContent = target.closest('.feed_parent_comment_area').querySelector('.feed_comment_content, .feed_parent_comment');
                const commentUpdateForm = target.closest('.feed_parent_comment_area').querySelector('.feed_comment_content_update');
                commentContent.classList.remove('display_none');
                commentUpdateForm.classList.add('display_none');
            }

            // 피드 삭제 버튼 클릭
            if (target.classList.contains('feed_delete_btn')) {
                const feedId = target.closest('.feed_container').querySelector('.feed_feed_id').value;
                if (confirm('삭제하시겠습니까?')) {
                    await deleteFeed(feedId);
                    target.closest('.feed_container').style.display = 'none';
                }
            }

            // 댓글 작성 버튼 클릭
            if (target.classList.contains('feed_comment_create_btn')) {
                const feedId = target.closest('.feed_container').querySelector('.feed_feed_id').value;
                let commentTextarea;
                let content;
                let parentCommentId = '';

                if (target.classList.contains('feed_parent_comment')) { // 부모 댓글
                    commentTextarea = target.closest('.feed_parent_comment_write').querySelector('.feed_comment_textarea');
                    content = commentTextarea.value;
                } else {    // 자식 댓글
                    const childCommentWriteArea = target.closest('.feed_child_comment_write');
                    childCommentWriteArea.classList.toggle('display_none');
                    commentTextarea = childCommentWriteArea.querySelector('.feed_comment_textarea');
                    content = commentTextarea.value;
                    parentCommentId = target.closest('.feed_parent_comment_area').querySelector('.feed_parent_comment_id').value;
                }

                // 댓글 내용이 비어있는지 확인
                if (!content.trim()) {
                    alert('댓글 내용을 입력하세요.');
                    return;
                }

                await saveComment(content, parentCommentId, feedId);
                commentTextarea.value = '';
            }

            // 댓글 삭제 버튼 클릭
            if (target.classList.contains('feed_comment_delete_btn')) {
                const feedId = target.closest('.feed_container').querySelector('.feed_feed_id').value;
                let commentArea;
                let commentId;

                if (target.classList.contains('feed_parent_comment')) { // 부모 댓글
                    commentArea = target.closest('.feed_parent_comment_area');
                    commentId = commentArea.querySelector('.feed_parent_comment_id').value;
                } else {    // 자식 댓글
                    commentArea = target.closest('.feed_child_comment_area');
                    commentId = commentArea.querySelector('.feed_child_comment_id').value;
                }

                await deleteComment(feedId, commentId);
                commentArea.style.display = 'none';
            }

            // 댓글 수정 or 취소 버튼 클릭
            if (target.classList.contains('feed_comment_update_btn') || target.classList.contains('feed_comment_content_update_cancel')) {
                let commentArea;
                let commentContent;
                let commentUpdateForm;

                if (target.classList.contains('feed_parent_comment')) { // 부모 댓글
                    commentArea = target.closest('.feed_parent_comment_area');
                    commentContent = commentArea.querySelector('.feed_comment_content, .feed_parent_comment');
                    commentUpdateForm = commentArea.querySelector('.feed_comment_content_update');

                    // 답글 입력창 닫기
                    const childCommentWriteArea = target.closest('.feed_parent_comment_info').querySelector('.feed_child_comment_write');
                    childCommentWriteArea.classList.add('display_none');
                } else {    // 자식 댓글
                    commentArea = target.closest('.feed_child_comment_area');
                    commentContent = commentArea.querySelector('.feed_comment_content, .feed_child_comment');
                    commentUpdateForm = commentArea.querySelector('.feed_comment_content_update');
                }

                if (target.classList.contains('feed_comment_update_btn')) {
                    // 드롭다운 메뉴창 닫기
                    const dropdown = target.closest('.feed_gear_dropdown');
                    dropdown.classList.add('display_none');

                    // 다른 댓글 수정창이 열려있는 경우 닫음
                    const allCommentUpdateArea = document.querySelectorAll('.feed_writer_comment');
                    allCommentUpdateArea.forEach(function(updateArea) {
                        if (updateArea !== commentUpdateForm) {
                            updateArea.querySelector('.feed_comment_content').classList.remove('display_none');
                            updateArea.querySelector('.feed_comment_content_update').classList.add('display_none');
                        }
                    });
                }

                commentUpdateForm.querySelector('.feed_comment_content_update_textarea').value = commentContent.innerText;

                commentContent.classList.toggle('display_none');
                commentUpdateForm.classList.toggle('display_none');
            }

            // 댓글 수정 완료 버튼을 누르면
            if (target.classList.contains('feed_comment_content_update_btn')) {
                const feedId = target.closest('.feed_container').querySelector('.feed_feed_id').value;
                let commentArea;
                let commentContent;
                let commentUpdateForm;
                let commentId;
                let updatedContent;

                if (target.classList.contains('feed_parent_comment')) { // 부모 댓글
                    commentArea = target.closest('.feed_parent_comment_area');
                    commentContent = commentArea.querySelector('.feed_comment_content');
                    commentUpdateForm = commentArea.querySelector('.feed_comment_content_update');
                    commentId = commentArea.querySelector('.feed_parent_comment_id').value;
                } else {    // 자식 댓글
                    commentArea = target.closest('.feed_child_comment_area');
                    commentContent = commentArea.querySelector('.feed_comment_content');
                    commentUpdateForm = commentArea.querySelector('.feed_comment_content_update');
                    commentId = commentArea.querySelector('.feed_child_comment_id').value;
                }

                // 수정 입력창을 꺼지고, 댓글 내용을 켜기
                commentContent.classList.remove('display_none');
                commentUpdateForm.classList.add('display_none');

                updatedContent = commentUpdateForm.querySelector('.feed_comment_content_update_textarea').value.trim();

                // 수정된 내용이 비어 있는지 확인
                if (!updatedContent) {
                    alert('수정할 내용을 입력하세요.');
                    return;
                }

                // 댓글 수정 처리 함수 호출
                await updateComment(feedId, commentId, updatedContent);

                // 수정 입력창을 숨기고 수정된 내용을 댓글 내용에 반영
                commentUpdateForm.classList.add('display_none');
                commentContent.textContent = updatedContent;
                commentContent.classList.remove('display_none');
            }

            // 피드 더보기 버튼 클릭
            if (target.classList.contains('feed_more_load_btn')) {

                // AJAX를 이용하여 다음 페이지의 피드를 가져옴
                const xhr = new XMLHttpRequest();
                let pageUrl = url + '?page=' + pageNum++;
                console.log(pageUrl)
                xhr.open('GET', pageUrl , true);
                console.log(pageNum);
                xhr.onload = function() {
                    console.log(xhr.status);
                    if (xhr.status >= 200 && xhr.status < 300) {
                        // 요청이 성공적으로 완료되면 실행됩니다.
                        if (xhr.status === 204) {
                            document.querySelector('.feed_more_load_btn').style.display='none';
                        }
                        const newFeeds = xhr.responseText;
                        document.getElementById(containerName).insertAdjacentHTML('beforeend', newFeeds); // 새로운 피드를 추가합니다.

                    } else {
                        // 서버에서 4xx, 5xx 응답을 반환하면 오류 처리를 합니다.
                        console.error('The request failed!');
                    }
                };
                xhr.onerror = function() {
                    // 요청이 네트워크 문제로 실패했을 때 실행됩니다.
                    console.error('The request failed due to a network error!');
                };
                xhr.send(); // 요청을 서버로 보냅니다.
            }
        });
    }
});

// 좋아요 삭제
async function deleteHeart(contentId, contentType) {
    try {
        const response = await fetch(`/heart`, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                contentId: contentId,
                contentType: contentType
            }),
        });

        if (response.ok) {
            const result = await response.json();
            console.log(result.message); // 성공 메시지 출력
            // 피드를 화면에서 제거하는 등의 추가적인 동작을 수행할 수 있습니다.
        } else {
            // 실패했을 때의 처리
            console.error('좋아요 삭제에 실패했습니다.');
        }
    } catch (error) {
        console.error('좋아요 삭제 중 오류가 발생했습니다:', error);
    }
}

// 좋아요 등록
async function addHeart(contentId, contentType) {
    try {
        const response = await fetch(`/heart`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                contentId: contentId,
                contentType: contentType
            }),
        });

        if (response.ok) {
            const result = await response.json();
            console.log(result.message); // 성공 메시지 출력
            // 피드를 화면에서 제거하는 등의 추가적인 동작을 수행할 수 있습니다.
        } else {
            // 실패했을 때의 처리
            console.error('좋아요 삭제에 실패했습니다.');
        }
    } catch (error) {
        console.error('좋아요 삭제 중 오류가 발생했습니다:', error);
    }
}

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

// 댓글 삭제 비동기 함수
async function deleteComment(feedId, commentId) {
    try {
        const response = await fetch(`/comment/${feedId}/${commentId}`, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json'
            }
        });

        if (response.ok) {
            const result = await response.json();
            console.log(result.message); // 성공 메시지 출력
            // 댓글 삭제 성공 시 추가 동작 수행 가능
        } else {
            // 실패했을 때의 처리
            console.error('댓글 삭제에 실패했습니다.');
        }
    } catch (error) {
        console.error('댓글 삭제 중 오류가 발생했습니다:', error);
    }
}

// 댓글 수정 비동기 함수
async function updateComment(feedId, commentId, updatedContent) {
    try {
        const response = await fetch(`/comment/${feedId}/${commentId}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                content: updatedContent,
                parentCommentId: ''
            })
        });

        if (response.ok) {
            const result = await response.json();
            console.log(result.message); // 성공 메시지 출력
        } else {
            // 실패했을 때의 처리
            console.error('댓글 수정에 실패했습니다.');
        }
    } catch (error) {
        console.error('댓글 수정 중 오류가 발생했습니다:', error);
    }
}
