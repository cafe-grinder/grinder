document.addEventListener('DOMContentLoaded', function() {
  let xhr = new XMLHttpRequest(); // XMLHttpRequest 객체 생성
  xhr.open('GET', '/get-header', true); // 요청을 초기화합니다.

  xhr.onload = function () {
    if (xhr.status >= 200 && xhr.status < 300) {
      // 요청이 성공적으로 완료되면 실행됩니다.
      document.getElementById('headerContainer').innerHTML = xhr.responseText; // 응답을 headerContainer에 삽입
      alarmTab();
    } else {
      // 서버에서 4xx, 5xx 응답을 반환하면 오류 처리를 합니다.
      console.error('The request failed!');
      if(xhr.status === 401 || xhr.status === 403) {
        reissue();
      }
    }
  };

  xhr.onerror = function () {
    // 요청이 네트워크 문제로 실패했을 때 실행됩니다.
    console.error('The request failed due to a network error!');
  };

  xhr.send(); // 요청을 서버로 보냅니다.
});

function alarmTab() {
  const alarm = document.querySelector('.header_alarm');
  let alarm_box = document.querySelector('.header_alarm_box');

  alarm.addEventListener('click', () => {
    if (alarm_box) {
      alarm_box.classList.toggle('alarm_active');
    }
  });
}

function addBookmark(cafeId) {
  // AJAX 요청을 보냄
  fetch(`/bookmark/${cafeId}`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
      // 다른 필요한 헤더가 있다면 여기에 추가
    },
    // 필요한 경우에는 body에 데이터를 추가할 수 있음
    // body: JSON.stringify({ cafeId: cafeId })
  })
  .then(response => {
    if (response.ok) {
      return response.json();
    }
    throw new Error('Network response was not ok.');
  })
  .then(data => {
    // 책갈피 추가에 성공한 경우 UI 업데이트
    document.getElementById('bookmark_null').style.display = 'none';
    document.getElementById('bookmark_fill').style.display = 'block';
    console.log(data.message); // 성공 메시지 로깅
  })
  .catch(error => {
    console.error('There has been a problem with your fetch operation:', error);
    // 예상치 못한 에러가 발생한 경우
    alert('예상치 못한 에러가 발생했습니다.');
  });
}

function deleteBookmark(cafeId) {
  // AJAX 요청을 보냄
  fetch(`/bookmark/${cafeId}`, {
    method: 'DELETE',
    headers: {
      'Content-Type': 'application/json',
      // 다른 필요한 헤더가 있다면 여기에 추가
    },
    // 필요한 경우에는 body에 데이터를 추가할 수 있음
    // body: JSON.stringify({ cafeId: cafeId })
  })
  .then(response => {
    if (response.ok) {
      return response.json();
    }
    throw new Error('Network response was not ok.');
  })
  .then(data => {
    // 책갈피 삭제에 성공한 경우 UI 업데이트
    document.getElementById('bookmark_fill').style.display = 'none';
    document.getElementById('bookmark_null').style.display = 'block';
    console.log(data.message); // 성공 메시지 로깅
  })
  .catch(error => {
    console.error('There has been a problem with your fetch operation:', error);
    // 예상치 못한 에러가 발생한 경우
    alert('예상치 못한 에러가 발생했습니다.');
  });
}



const sellerApplyBtn = document.getElementById('seller_apply_button');
sellerApplyBtn.addEventListener('click', () => {
    let cafeId = sellerApplyBtn.dataset.cafeId
    window.location.href = '/cafe/seller_apply/' + cafeId;
})