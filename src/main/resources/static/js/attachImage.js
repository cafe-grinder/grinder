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
        imageContainer.style.height = '240px';
        imageContainer.style.marginBottom = '40px';
        imageContainer.appendChild(imageElement); // 이미지를 div 태그에 추가
    };

    reader.readAsDataURL(file); // 파일을 읽어서 이미지 URL 생성
});