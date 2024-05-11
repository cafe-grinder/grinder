// email input 요소
const emailInput = document.querySelector('#email');
// span 요소
const emailSpanElement = document.querySelector('#email-check-result');
const nicknameInput = document.querySelector('#nick-name');
const nicknameSpanElement = document.querySelector('#nickname-check-result');
const sendButton = document.querySelector('#email-request-btn');
const codeInput = document.querySelector('#verify-input');
const verifyButton = document.querySelector('#email-verity-btn');
const verifySpanElement = document.querySelector('#email-verify-result');
const passwordInput = document.querySelector('#pw');
const passwordReInput = document.querySelector('#pwCheck');
const passwordSpanElement = document.querySelector('#password-recheck');
const joinButton = document.querySelector('#join-btn');
let emailCheck = false;
let emailVerifyCheck = false;
let passwordCheck = false;
let nicknameCheck = false;

window.addEventListener('DOMContentLoaded', function() {
    const urlParams = new URLSearchParams(window.location.search);
    const email = urlParams.get('email');
    if (email) {
        emailInput.value = email;
        emailVerifyCheck = true;
        sendButton.disabled = true;
        verifyButton.disabled = true;
        codeInput.disabled = true;
        sendButton.style.backgroundColor = "gray";
        verifyButton.style.backgroundColor = "gray";
        codeInput.placeholder = "인증이 완료되었습니다";
        verifySpanElement.textContent = "(소셜 로그인)인증된 이메일입니다.";
        verifySpanElement.style.color ='#00780C';
        emailSpanElement.textContent = '사용 가능한 이메일입니다.';
        emailSpanElement.style.color ='#00780C';
        emailCheck = true;
    }
});

// 이메일 중복확인
emailInput.addEventListener('input', function() {
    const email = emailInput.value;

    // 이메일 형식 체크
    if (!isValidEmail(email)) {
        emailSpanElement.textContent = '이메일 형식을 지켜주세요.';
        emailSpanElement.style.color ='#E10000';
        return;
    }

    // API 호출
    fetch(`/api/member/email/check?email=${encodeURIComponent(email)}`)
        .then(response => response.json())
        .then(data => {
            // API 결과에 따라 span 내용 변경요
            if (data.message === "사용 가능한 이메일입니다.") {
                emailSpanElement.textContent = '사용 가능한 이메일입니다.';
                emailSpanElement.style.color ='#00780C';
                emailCheck = true;
            } else {
                emailSpanElement.textContent = '중복된 이메일입니다.';
                emailSpanElement.style.color ='#E10000';
                emailCheck = false;
            }
            console.log(emailCheck);
        })
        .catch(error => console.error('Error:', error));
});

// 이메일 형식 검증 함수
function isValidEmail(email) {
    // 간단한 이메일 형식 검사. 좀 더 엄격한 검증이 필요하다면 정규표현식을 사용하세요.
    return /^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email);
}
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
// 이메일 인증요청
sendButton.addEventListener('click', function() {
    // 입력된 이메일 값을 가져옵니다.
    const email = emailInput.value;

    // 이메일 값이 비어 있는지 확인합니다.
    if (email.trim() === '') {
        alert('이메일을 입력하세요.');
        return;
    }
    if(emailCheck === false){
        alert('이메일 중복을 확인하세요.');
        return;
    }
    // AJAX 요청을 보냅니다.
    const xhr = new XMLHttpRequest();
    xhr.open('POST', '/api/member/email/verification-requests', true);
    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    xhr.onreadystatechange = function() {
        if (xhr.readyState === 4) {
            if (xhr.status === 200) {
                // 요청이 성공하면 메시지를 표시합니다.
                const response = JSON.parse(xhr.responseText);
                alert(response.message);
            } else {
                // 요청이 실패하면 에러를 표시합니다.
                alert('오류가 발생했습니다.');
            }
        }
    };
    xhr.send(`email=${encodeURIComponent(email)}`);
})
// 이메일 인증 확인
verifyButton.addEventListener('click', function() {
    // 입력된 코드 값을 가져옵니다.
    const code = codeInput.value;
    const email = emailInput.value;

    // 코드 값이 비어 있는지 확인합니다.
    if (code.trim() === '') {
        alert('코드를 입력하세요.');
        return;
    }

    // AJAX 요청을 보냅니다.
    const xhr = new XMLHttpRequest();
    xhr.open('GET', `/api/member/email/verifications?email=${encodeURIComponent(email)}&code=${encodeURIComponent(code)}`, true);
    xhr.onreadystatechange = function() {
        if (xhr.readyState === 4) {
            if (xhr.status === 200) {
                // 요청이 성공하면 메시지를 표시합니다.
                const response = JSON.parse(xhr.responseText);
                alert(response.message);
                emailVerifyCheck = true;
                verifySpanElement.textContent = response.message;
                verifySpanElement.style.color ='#00780C';
            } else {
                alert('이메일 인증에 실패했습니다.다시 시도해주세요.');
            }
        }
    };
    xhr.send();
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

// 로그인
joinButton.addEventListener('click',function (){
    const email = emailInput.value;
    const password = passwordInput.value;
    const nickname = nicknameInput.value;
    var phonenum0 = document.querySelector('#contact').value;
    var phonenum1 = document.querySelector('#phonenum1').value;
    var phonenum2 = document.querySelector('#phonenum2').value;
    const phonenum = phonenum0 +phonenum1+phonenum2;

    const requestData = {
        email: email,
        password: password,
        nickname: nickname,
        phoneNum: phonenum
    };
    if(emailCheck ===false){
        alert("이메일 중복을 확인하세요.");
    } else if(emailVerifyCheck === false){
        alert("이메일 인증을 진행해주세요.");
    } else if(passwordCheck === false){
        alert("비밀번호가 일치하지 않습니다.");
    } else if(nicknameCheck === false){
        alert("닉네임 중복을 확인하세요.");
    } else{
        try {
            const response =  fetch('/api/member/signup', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(requestData)
            }).then(response => {
                    if (response.status === 201) {
                        return response.json();
                    } else {
                        throw new Error('서버 오류');
                    }
            }).then(data => {
                    alert('회원가입이 성공적으로 완료되었습니다!'); // 성공 메시지를 표시합니다.
                    location.href="/page/login";
            })

        }   catch (error) {
            console.error(error);
            alert('회원가입에 실패했습니다.');
        }
    }
});