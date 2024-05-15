document.addEventListener('DOMContentLoaded', function() {
    // 백엔드에서 피드를 가져오는 XMLHttpRequest
    let xhr = new XMLHttpRequest(); // XMLHttpRequest 객체 생성
    xhr.open('GET', '/feed/newfeed', true); // 요청을 초기화합니다.
    xhr.onload = function () {
        if (xhr.status >= 200 && xhr.status < 300) {
            // 요청이 성공적으로 완료되면 실행됩니다.
            InitNewFeed();          // 피드값 초기화
            ImageSelectEvent();     // 이미지 선택
            CafeSelectEvent();      // 카페 선택
            StarGradEvent();        // 별점
            NewFeedClickEvent();    // 클릭 이벤트
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

    function InitNewFeed() {
        // 이미지 초기화
        imageUrlList.forEach(imageUrl => {
            addImageUrlToImageList(imageUrl);
        });
        function addImageUrlToImageList(imageUrl) {
            fetch(imageUrl)
                .then(response => response.blob())
                .then(blob => {
                    const file = new File([blob], "imageFileName");
                    const reader = new FileReader();

                    reader.onload = function(e) {
                        let imgBox = document.querySelector('.img-box');
                        const img = document.createElement('img');
                        img.src = e.target.result;
                        img.classList.add('newfeed_upload_img');

                        img.addEventListener('click', function() {
                            removeImage(img, file);
                        });

                        imgBox.appendChild(img);
                        imageList.push(file);
                    };

                    reader.readAsDataURL(blob);
                })
                .catch(error => console.error('Error fetching image:', error));
        }

        // 평점 초기화
        const stars = document.querySelectorAll('.star');
        stars.forEach(star => {
            const starValue = parseInt(star.getAttribute('data-value'));
            if (starValue <= grade) {
                star.setAttribute('src', '/img/icon/star-fill.png');
            } else {
                star.setAttribute('src', '/img/icon/star.png');
            }
        });

        // 태그 초기화
        const tags = document.querySelectorAll('.newfeed_tag_name');
        tags.forEach(tag => {
            tagNameList.forEach(tagName => {
                if (tag.value === tagName) {
                    tag.classList.add('newfeed_tag_name_active');
                }
            });
        });
    }

    let imageUrlList = [];
    document.querySelectorAll('.newfeed_img_init').forEach(imageUrl => {
        imageUrlList.push(imageUrl.value);
    });

    let imageList = [];
    function ImageSelectEvent() {
        document.getElementById('file-input').addEventListener('change', function() {
            let files = this.files;
            let imgBox = document.querySelector('.img-box');

            // 한 번에 선택한 파일 수가 4개 이하일 때만 처리
            if (files.length <= 4) {
                if ((imageList.length + files.length) > 4) {
                    alert('최대 4개의 이미지만 첨부할 수 있습니다.');
                    return;
                }
                // 선택한 파일 수만큼 반복
                for (let i = 0; i < files.length; i++) {
                    let file = files[i];
                    let reader = new FileReader();

                    reader.onload = (function(file) {
                        return function(e) {
                            let img = document.createElement('img');
                            img.src = e.target.result;
                            img.classList.add('newfeed_upload_img');

                            // 이미지를 클릭하면 해당 이미지 제거
                            img.addEventListener('click', function() {
                                removeImage(img, file);
                            });

                            imgBox.appendChild(img); // 이미지 박스에 이미지 추가
                            imageList.push(file); // uploadImages 배열에 파일 추가
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
    }
    function removeImage(img, file) {
        if (confirm("이미지를 제거하시겠습니까?")) {
            img.parentNode.removeChild(img); // 이미지 박스에서 이미지 제거
            const index = imageList.indexOf(file);
            if (index !== -1) {
                imageList.splice(index, 1); // uploadImages 배열에서 해당 파일 제거
            }
        }
    }

    let cafeId = document.querySelector('.newfeed_cafe_id').value;
    function CafeSelectEvent() {
        const input = document.querySelector('.newfeed_input');
        const cafeListContainer = document.querySelector('.newfeed_cafe_list');
        if (cafeListContainer)

        input.addEventListener('input', async () => {
            const input = document.querySelector('.newfeed_input');
            const keyword = input.value;
            const cafes = await searchCafes(keyword);
            displayCafes(cafes);
        });

        function displayCafes(cafes) {
            const cafeListContainer = document.querySelector('.newfeed_cafe_list');
            cafeListContainer.innerHTML = '';
            if (cafes.length === 0) {
                cafeListContainer.style.display = 'none'; // 카페 데이터가 없으면 숨김
                return;
            }
            cafeListContainer.style.display = 'block'; // 카페 데이터가 있으면 보이도록 설정
            cafes.forEach(cafe => {
                const cafeItem = document.createElement('div');
                cafeItem.textContent = cafe.name;
                cafeItem.classList.add('cafe-item');
                cafeItem.addEventListener('click', () => {
                    selectCafe(cafe);
                });

                const addressSpan = document.createElement('span');
                addressSpan.textContent = ` (${cafe.address})`; // 카페 주소 설정
                addressSpan.classList.add('cafe-item-address');

                cafeItem.appendChild(addressSpan);
                cafeListContainer.appendChild(cafeItem);
            });
        }

        function selectCafe(cafe) {
            const input = document.querySelector('.newfeed_input');
            const cafeListContainer = document.querySelector('.newfeed_cafe_list');
            cafeId = cafe['cafeId'];
            input.value = cafe.name;
            cafeListContainer.innerHTML = ''; // 선택 후 카페 목록 지우기
            cafeListContainer.style.display = 'none';
        }
    }

    let grade = document.querySelector('.newfeed_grade_init').value;
    function StarGradEvent() {
        const stars = document.querySelectorAll('.star');

        stars.forEach(star => {
            star.addEventListener('click', () => {
                if (cafeId === '') {
                    alert("카페를 선택해주세요.");
                    return;
                }
                grade = parseInt(star.getAttribute('data-value'));
                highlightStars(grade);
            });
        });

        function highlightStars(grade) {
            stars.forEach(star => {
                const starValue = parseInt(star.getAttribute('data-value'));
                if (starValue <= grade) {
                    star.setAttribute('src', '/img/icon/star-fill.png');
                } else {
                    star.setAttribute('src', '/img/icon/star.png');
                }
            });
        }
    }

    let tagNameList = []
    document.querySelectorAll('.newfeed_tag_init').forEach(tagName => {
        tagNameList.push(tagName.value);
    });
    function NewFeedClickEvent() {
        document.addEventListener('click', async function(event) {
            const target = event.target;

            if (!target.classList.contains('newfeed_input')) {
                const cafeListContainer = document.querySelector('.newfeed_cafe_list');
                cafeListContainer.style.display = 'none';
            }

            // 태그 클릭
            if (target.classList.contains('newfeed_tag_name')) {
                if (cafeId === '') {
                    alert("카페를 선택해주세요.");
                    return;
                }
                if (target.classList.contains('newfeed_tag_name_active')) {
                    // 선택 해제
                    const index = tagNameList.indexOf(target.value);
                    if (index !== -1) {
                        tagNameList.splice(index, 1);
                    }
                    target.classList.remove('newfeed_tag_name_active');
                } else {
                    // 선택
                    if (tagNameList.length >= 3) {
                        alert('3개 이상 선택하실 수 없습니다.');
                    } else {
                        tagNameList.push(target.value);
                        target.classList.add('newfeed_tag_name_active');
                    }
                }
            }

            // 이미지 첨부
            if (target.classList.contains('newfeed_img_upload_btn')) {
                document.getElementById('file-input').click();
            }

            // 등록하기 버튼 클릭
            if (target.classList.contains('newfeed_create_btn')) {
                if (cafeId !== '' && grade < 1) {
                    alert('평점을 선택해주세요.');
                    return
                }

                let content = document.querySelector('.newfeed_content').value;

                // 댓글 내용이 비어있는지 확인
                if (content === '') {
                    alert('본문이 비었습니다.');
                    return;
                }

                await addFeed(content, cafeId, tagNameList, grade, imageList);
            }

            // 수정하기 버튼 클릭
            if (target.classList.contains('newfeed_update_btn')) {
                if (cafeId !== '' && grade < 1) {
                    alert('평점을 선택해주세요.');
                    return
                }

                const feedId = new URLSearchParams(window.location.search).get('feedId');
                let content = document.querySelector('.newfeed_content').value;

                // 댓글 내용이 비어있는지 확인
                if (!content.trim()) {
                    alert('본문이 비었습니다.');
                    return;
                }

                await updateFeed(feedId, content, cafeId, tagNameList, grade, imageList);
            }
        });
    }
});

// 피드 등록
async function addFeed(content, cafeId, tagNameList, grade, imageList) {
    try {
        let formData = new FormData();
        formData.append('content', content);
        formData.append('cafeId', cafeId);
        for (let i = 0; i < tagNameList.length; i++) {
            formData.append('tagNameList', tagNameList[i]);
        }
        formData.append('grade', grade);
        for (let i = 0; i < imageList.length; i++) {
            formData.append('imageList', imageList[i]);
        }

        const response = await fetch(`/feed/newfeed`, {
            method: 'POST',
            body: formData
        });

        if (response.ok) {
            const result = await response.json();
            console.log(result.message); // 성공 메시지 출력
            let memberId = document.querySelector('.newfeed_member_id').value;
            location.replace(`/mypage/${memberId}`);
        } else {
            // 실패했을 때의 처리
            console.error('피드 등록에 실패했습니다.');
        }
    } catch (error) {
        console.error('피드 등록 중 오류가 발생했습니다:', error);
    }
}

// 피드 수정
async function updateFeed(feedId, content, cafeId, tagNameList, grade, imageList) {
    try {
        let formData = new FormData();
        formData.append('feedId', feedId);
        formData.append('content', content);
        formData.append('cafeId', cafeId);
        for (let i = 0; i < tagNameList.length; i++) {
            formData.append('tagNameList', tagNameList[i]);
        }
        formData.append('grade', grade);
        for (let i = 0; i < imageList.length; i++) {
            formData.append('imageList', imageList[i]);
        }

        const response = await fetch(`/feed/${feedId}`, {
            method: 'PUT',
            body: formData
        });

        if (response.ok) {
            const result = await response.json();
            console.log(result.message); // 성공 메시지 출력
            let memberId = document.querySelector('.newfeed_member_id').value;
            location.replace(`/mypage/${memberId}`);
        } else {
            // 실패했을 때의 처리
            console.error('피드 수정에 실패했습니다.');
        }
    } catch (error) {
        console.error('피드 수정 중 오류가 발생했습니다:', error);
    }
}

async function searchCafes(keyword) {
    try {
        const response = await fetch(`/api/cafe/search-cafe?query=${encodeURIComponent(keyword)}`);
        if (response.ok) {
            const cafes = await response.json();
            return cafes;
        } else if (response.status === 204) {
            console.warn('No cafes found.');
            return [];
        } else {
            console.error('카페 검색에 실패했습니다.');
            return [];
        }
    } catch (error) {
        console.error('카페 검색 중 오류가 발생했습니다:', error);
        return [];
    }
}
