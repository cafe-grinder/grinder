document.addEventListener('DOMContentLoaded', function() {
  const bookmarkButton = document.querySelector('.cafe_info_bookmark');

  bookmarkButton.addEventListener('click', function() {
    const cafeId = window.location.pathname.substring(1); // 현재 카페의 cafeId

    fetch(`/api/bookmark/${cafeId}`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      }
    })
    .then(response => {
      if (response.ok) {
        console.log('Bookmark added successfully!');
        // 성공적으로 추가된 경우 추가 처리를 할 수 있습니다.
      } else {
        console.error('Bookmark adding failed.');
        // 실패한 경우에 대한 처리
      }
    })
    .catch(error => {
      console.error('Error adding bookmark:', error);
      // 에러 처리
    });
  });
});
