const inputFile = document.getElementById('file-input');
const applyFrom = document.getElementById('apply_form');

// 이미지 첨부 시 input 클릭 이벤트
function openFileUploader() {
   inputFile.click()
}

// input에 파일 업로드시 파일명 표시
inputFile.addEventListener('change', (e) => {
    const file =  e.target.files[0]
    let reader = new FileReader();
    reader.onload = (e) => {
        let imageBox = document.getElementById('image-box');
        let imageName = document.createElement('span');
        imageName.classList.add('image_name');
        imageBox.innerHTML = ''
        imageName.innerText = file.name
        imageBox.appendChild(imageName)
    }
    reader.readAsDataURL(file)
})

// 판매자 신청 요청
applyFrom.addEventListener('submit', (e) => {
    e.preventDefault();
    const cafeId = document.getElementById('save_cafe_id').value
    const url = '/api/seller_apply/' + cafeId
    let formData = new FormData();
    formData.append('file', inputFile.files[0])

    fetch(url, {
        method: 'POST',
        body: formData
    })
            .then(response => {
                if (response.status === 401 || response.status === 403) {
                    reissue();
                }
                return response.json()
            })
            .then(data => {
                alert(data.message)
            })
            .catch(error => {
                console.error('The request failed', error)
            })
})

// 토큰 재발급
function reissue() {
    let url = '/api/reissue';
    // 기본 요청 설정
    let fetchOptions = {
        method: 'GET',
    };
    // AJAX 요청 예시
    fetch(url, fetchOptions)
            .then(response => {
                return response.json();
            })
            .then(data => {
                window.location.href = "/admin";
                console.log('Success:', data);
            })
            .catch((error) => {
                window.location.href = "/";
                console.error('Error:', error);
            });
}



