// span 요소
const nicknameInput = document.querySelector('#nick-name');
const nicknameSpanElement = document.querySelector('#nickname-check-result');
const nowPasswordInput = document.getElementById('now_pw');
const passwordInput = document.querySelector('#pw');
const passwordReInput = document.querySelector('#pwCheck');
const passwordSpanElement = document.querySelector('#password-recheck');
const updateButton = document.querySelector('#update-btn');
let passwordCheck = false;
let nicknameCheck = false;


// 닉네임 중복확인
nicknameInput.addEventListener('input', function() {
    const nickname = nicknameInput.value;

    // API 호출
    fetch(`/api/member/nickname/check?nickname=${encodeURIComponent(nickname)}`)
        .then(response => response.json())
        .then(data => {
            // API 결과에 따라 span 내용 변경요
            if (data.message === "사용 가능한 닉네임입니다.") {
                nicknameSpanElement.textContent = '사용 가능한 닉네임입니다.';
                nicknameSpanElement.style.color ='#00780C';
                nicknameCheck = true;
            } else {
                nicknameSpanElement.textContent = '중복된 닉네임입니다.';
                nicknameSpanElement.style.color = '#E10000';
                nicknameCheck = false;
            }
        })
        .catch(error => console.error('Error:', error));
});

// 비밀번호 재확인
passwordReInput.addEventListener('input', function() {
    const password = passwordInput.value;
    const passwordRetry = passwordReInput.value;
    if(password.trim() === ''){
        passwordSpanElement.textContent = '비밀번호를 입력하세요.';
        return;
    }
    if(password===passwordRetry){
        passwordSpanElement.textContent = '비밀번호가 일치합니다.';
        passwordSpanElement.style.color ='#00780C';
        passwordCheck = true;
    }else{
        passwordSpanElement.textContent = '비밀번호가 일치하지않습니다.';
        passwordSpanElement.style.color ='#E10000'
        passwordCheck = false;
    }

});

// 회원 정보 수정
updateButton.addEventListener('click',function (){
    const currentUrl = window.location.href;
    const urlSegments = currentUrl.split('/');
    const uuid = urlSegments[urlSegments.length - 1];

    console.log(uuid);
    const memberId = uuid;
    const nowPassword = nowPasswordInput.value;
    const password = passwordInput.value;
    const nickname = nicknameInput.value;
    var phonenum0 = document.querySelector('#contact').value;
    var phonenum1 = document.querySelector('#phonenum1').value;
    var phonenum2 = document.querySelector('#phonenum2').value;
    const phonenum = phonenum0 +phonenum1+phonenum2;

    const requestData = {
        memberId : memberId,
        nowPassword : nowPassword,
        password: password,
        nickname: nickname,
        phoneNum: phonenum
    };

     if (passwordCheck === false) {
        alert("비밀번호가 일치하지 않습니다.");
    } else if(nicknameCheck === false){
        alert("닉네임 중복을 확인하세요.");
    } else{
        try {
            const response =  fetch('/api/member/update', {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(requestData)
            }).then(response => {
                if (response.status === 200) {
                    alert('정보 변경이 성공적으로 완료되었습니다!');
                    location.href="/";
                } else {
                    return response.json().then(data => {
                        throw new Error(data.message); // 서버에서 보낸 오류 메시지를 이용해 Error 객체 생성
                    });
                }
            }).catch(error => {
                alert(error.message);

            })

        }  catch (error) {
            console.error(error);
            alert('정보 변경에 실패했습니다.');
        }
    }
});