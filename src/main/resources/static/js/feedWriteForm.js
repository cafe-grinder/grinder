// 이미지 첨부 버튼 클릭시 input(type="file") 태그 클릭
function openFileUploader() {
    document.getElementById('file-input').click();
}

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
        let img = document.createElement('img');

        imgCnt++;

        reader.onload = (function(file) {
            return function(e) {
                let img = document.createElement('img');
                img.src = e.target.result;
                imgBox.appendChild(img); // 이미지 박스에 이미지 추가

                // 이미지 첨부 시 높이 및 마진 조절
                img.onload = function() {
                    imgBox.style.height = '250px';
                    imgBox.style.marginBottom = '60px';
                    articleBox.style.height = '1641px';
                }
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

// 태그 버튼 클릭 시 (button -> 배경색 변경, index -> 체크 박스 선택)
function buttonCheck(button, index) {
    changeBackgroundColor(button, index); // 배경색 변경 함수 내부에서 체크 박스 선택 함수 호출
}

let btnCount = 0;
// 배경색 변경 함수
function changeBackgroundColor(button, index) {
    let currentColor = button.style.backgroundColor;
    if (currentColor === "rgb(250, 255, 250)") { // 현재 배경색이 #FAFFFA라면
        button.style.backgroundColor = "#FFFFFF"; // 배경색을 #FFFFFF로 변경
        button.style.borderColor = "#FFFFFF";
        btnCount--;
        console.log(btnCount);
        toggleCheckbox(index);
    } else {
        if(btnCount === 3) { // 3개 이상의 버튼이 클릭 됐을 시
            alert('3개 이상 선택하실 수 없습니다.');
            return;
        }
        button.style.backgroundColor = "#FAFFFA"; // 그렇지 않으면 배경색을 #FAFFFA 변경
        button.style.borderColor = "#009B55";
        btnCount++;
        console.log(btnCount);
        toggleCheckbox(index);
    }
}

// 체크 박스 선택 함수
function toggleCheckbox(index) {
    let checkboxes = document.querySelectorAll('.check-box input[type="checkbox"]');
    checkboxes[index].checked = !checkboxes[index].checked;
}