document.addEventListener('DOMContentLoaded', function () {
    // 폼 요소와 이메일 입력 필드 가져오기
    const form = document.querySelector('form');
    const emailInput = document.getElementById('email');

    // 폼 제출 이벤트를 감지하여 이메일을 서버에 보내고 비밀번호 재설정을 요청하는 함수
    form.addEventListener('submit', function (event) {
        // 기본 제출 동작 막기
        event.preventDefault();

        // 이메일 입력 값 가져오기
        const email = emailInput.value;

        // 이메일이 입력되어 있는지 확인
        if (!email) {
            alert('이메일을 입력해주세요.');
            return;
        }

        // 비동기 요청으로 서버에 이메일 보내기
        fetch('/api/member/email/password?email=' + email, {
            method: 'PATCH', // PatchMapping에 맞게 PATCH 메서드 사용
        })
            .then(response => {
                if (response.ok) {
                    return response.json();
                } else {
                    throw new Error('서버 오류: ' + response.status);
                }
            })
            .then(data => {
                // 성공적인 응답 처리
                alert(data.message); // 성공 또는 실패 메시지 표시
                location.href = "/page/change/password/finish";
            })
            .catch(error => {
                // 오류 처리
                console.error('오류 발생:', error);
                alert('비밀번호 변경에 실패했습니다.');
            });
    });
});