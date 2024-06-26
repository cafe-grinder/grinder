const naverBtn = document.querySelector("#naver-btn");
const googleBtn = document.querySelector("#google-btn");
document.addEventListener('DOMContentLoaded', function() {
        const emailInput = document.querySelector('#emailInput');
        const passwordInput = document.querySelector('#pwInput');
        const loginButton = document.querySelector('.btn-login');

        loginButton.addEventListener('click', function() {
            const email = emailInput.value;
            const password = passwordInput.value;

            const userData = {
                email: email,
                password: password
            };

            fetch('/api/login', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(userData)
            })
                .then(response => {
                    if (!response.ok) {
                        if (response.status === 401) {
                            throw new Error('Unauthorized'); // 401 Unauthorized 에러 발생 시에만 에러 throw
                        } else {
                            throw new Error('Network response was not ok');
                        }
                    }
                    // 응답 쿠키에서 "refresh" 쿠키 추출하여 로컬 스토리지에 저장 -> 필요없지만 일단 놔둘게여
                    const cookies = response.headers.get('set-cookie');
                    if (cookies) {
                        const cookieArray = cookies.split(';');
                        cookieArray.forEach(cookie => {
                            const [name, value] = cookie.trim().split('=');
                            if (name === 'refresh') {
                                localStorage.setItem('refresh', value);
                            }
                            if(name === 'access'){
                                localStorage.setItem('access', value);
                            }
                        });
                    }
                })
                .then(data => {
                    // 서버로부터 받은 응답 처리
                    console.log(data);

                    // // 성공적으로 로그인되었을 때 특정 URL로 이동
                    window.location.href = '/'; // 이동할 URL로 수정
                })
                .catch(error => {
                    if (error.message === 'Unauthorized') {
                        alert("계정과 비밀번호가 일치하지 않습니다. 다시 시도해주세요.");
                    } else {
                        console.error('Error:', error);
                    }
                });
        });
    });

naverBtn.addEventListener('click', () => {
    // GET 요청 보내기
    window.location.href = '/oauth2/authorization/naver';
});

googleBtn.addEventListener('click', () => {
    // GET 요청 보내기
    window.location.href = '/oauth2/authorization/google';
});
