
let imgCnt = 0; // 이미지 수 카운트(4개)

// input(type="file") 태그에 이미지 첨부 시
document.getElementById('file-input').addEventListener('change', function() {
    let files = this.files;
    let imgBox = document.querySelector('.img-box');
    let articleBox = document.querySelector('.article-box');

    // 한 번에 선택한 파일 수가 4개 이하일 때만 처리
    if (files.length <= 4) {
        if((imgCnt + files.length) > 4) {
                alert('최대 4개의 이미지만 첨부할 수 있습니다.');
                return;
        }
        // 선택한 파일 수만큼 반복
        for (let i = 0; i < files.length; i++) {
            let file = files[i];
            let reader = new FileReader();

            imgCnt++;

            reader.onload = (function(file) {
                return function(e) {
                    let img = document.createElement('img');
                    img.src = e.target.result;
                    imgBox.appendChild(img); // 이미지 박스에 이미지 추가

                };
            })(file);

            reader.readAsDataURL(file);
        }
    } else { // 한 번에 4개 이상의 이미지를 첨부 시
        alert('최대 4개의 이미지만 선택해주세요.');
        // 파일 입력(input) 초기화
        this.value = '';
    }
});


////////////////////////////////////////////////////
///////////////////// API 연결 //////////////////////
////////////////////////////////////////////////////
document.addEventListener('DOMContentLoaded', function() {
    // 백엔드에서 피드를 가져오는 XMLHttpRequest
    let xhr = new XMLHttpRequest(); // XMLHttpRequest 객체 생성
    xhr.open('GET', '/feed/newfeed', true); // 요청을 초기화합니다.
    xhr.onload = function () {
        if (xhr.status >= 200 && xhr.status < 300) {
            // 요청이 성공적으로 완료되면 실행됩니다.
            NewFeedClickEvent(); // 클릭 이벤트 함수 호출
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

    let selectedTags = [];
    let uploadImages = [];
    function NewFeedClickEvent() {
        document.addEventListener('click', async function(event) {
            const target = event.target;

            // 태그 클릭
            if (target.classList.contains('newfeed_tag_name')) {
                if (target.classList.contains('newfeed_tag_name_active')) {
                    // 선택 해제
                    const index = selectedTags.indexOf(target.innerText);
                    if (index !== -1) {
                        selectedTags.splice(index, 1);
                    }
                    target.classList.remove('newfeed_tag_name_active');
                } else {
                    // 선택
                    if (selectedTags.length >= 3) {
                        alert('3개 이상 선택하실 수 없습니다.');
                    } else {
                        selectedTags.push(target.innerText);
                        target.classList.add('newfeed_tag_name_active');
                    }
                }
            }

            // 이미지 첨부
            if (target.classList.contains('newfeed_img_upload_btn')) {
                document.getElementById('file-input').click();
            }

            // 등록하기 버튼 클릭
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
        });
    }
});