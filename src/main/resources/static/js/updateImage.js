function openFileUploader() {
    document.getElementById('file-input').click();
}

document.getElementById('file-input').addEventListener('change', function(event) {
    var file = event.target.files[0]; // 파일 선택
    var reader = new FileReader(); // FileReader 객체 생성

    reader.onload = function(event) {
        var imageUrl = event.target.result; // 이미지 URL 가져오기

        var imageElement = document.createElement('img'); // img 요소 생성
        imageElement.src = imageUrl; // 이미지 URL 설정

        var imageContainer = document.getElementById('img-box'); // 이미지를 추가할 div 태그 가져오기
        imageContainer.innerHTML = ''; // 기존에 있는 이미지 삭제
        imageContainer.style.height = '200px';
        imageContainer.style.marginBottom = '40px';
        imageContainer.appendChild(imageElement); // 이미지를 div 태그에 추가
    };

    reader.readAsDataURL(file); // 파일을 읽어서 이미지 URL 생성
});

document.getElementById('image-form').addEventListener('submit', async function(event) {
    event.preventDefault(); // 폼의 기본 제출 동작 방지

    const formData = new FormData();
    const fileInput = document.getElementById('file-input');

    if (fileInput.files.length > 0) {
        formData.append('image', fileInput.files[0]); // 파일 추가
    } else {
        if (document.getElementById('cafeId')) {
            DeleteImage('/api/image/' + document.getElementById('cafeId').value);
            return
        } else {
            DeleteImage('/api/image');
            return
        }
    }

    // FormData의 내용 확인을 위한 로그 출력
    for (let [key, value] of formData.entries()) {
        console.log(`${key}: ${value}`);
    }

    if (document.getElementById('cafeId')) {
        formData.append('cafeId', document.getElementById('cafeId').value);
        updateImage('/api/image', formData);
    } else {
        updateImage('/api/image', formData);
    }
});

function DeleteImage(url) {
    let fetchOptions = {
        method: 'DELETE',
    };

    fetch(url, fetchOptions)
        .then(data => {
            console.log('삭제되었습니다.');
            window.location.href='/';
        })
        .catch((error) => {
            console.error('Error:', error);
        });
}

function updateImage(url, formData) {
    fetch(url, {
        method: 'POST',
        body: formData,
        // 서버에서 'multipart/form-data' 처리가 필요하므로 Content-Type 헤더는 설정하지 않습니다.
    }).then(response => {
        if (response.ok) {
            return response.json();
        } else {
            throw new Error('Network response was not ok.');
        }
    }).then(data => {
        console.log('Success:', data);
        alert('이미지가 변경되었습니다!');
        //새로고침
        window.location.href='/';
    }).catch(error => {
        console.error('Error:', error);
        alert('이미지 변경을 실패했습니다.');
    });
}