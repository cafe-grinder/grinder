function registerCafe() {
  const cafeName = document.getElementById("cafe-name").value;
  const address = document.getElementById("address").value;
  const selectValue = document.getElementById("contact").value;
  const input1Value = document.querySelector(".contact-box input:nth-of-type(1)").value;
  const input2Value = document.querySelector(".contact-box input:nth-of-type(2)").value;
  const phoneNum = selectValue + input1Value + input2Value;

  let openingHoursData = [];
  const dayContainers = document.querySelectorAll('.day-box');

  dayContainers.forEach(dayContainer => {
    const day = dayContainer.querySelector('span').textContent.trim();
    const openTime = dayContainer.querySelector('.open-time').value.trim();
    const closeTime = dayContainer.querySelector('.close-time').value.trim();
    const isHoliday = dayContainer.querySelector('.closed-checkbox').checked;

    const dayData = {
      day: day,
      openTime: openTime,
      closeTime: closeTime,
      isHoliday: isHoliday
    };

    openingHoursData.push(dayData);
  });

  const requestData = {
    "name": cafeName,
    "address": address,
    "phoneNum": phoneNum,
  };

  // CafePageController.saveAddCafeForm
  fetch('/cafe/newcafe', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(requestData)
  })
  .then(response => {
    console.log(response);
    if (response.ok) {
      alert("카페정보가 등록되었습니다.")
    } else {
      alert("카페정보 등록에 실패했습니다.")
    }
  })
  .catch(error => {
    console.error('Error:', error);
  });

  // OpeningHoursController.saveNewOpeningHours
  fetch('/cafe/newcafe/openinghours', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(openingHoursData)
  })
  .then(response => {
    console.log(response);
    if (response.ok) {
      alert("운영시간이 등록되었습니다.")
    } else {
      alert("운영시간 등록에 실패했습니다.")
    }
  })
  .catch(error => {
    console.error('Error:', error);
  });
}

document.querySelectorAll('.closed-checkbox').forEach(function(checkbox) {
  checkbox.addEventListener('change', function() {
    const day = this.closest('.day-box').id;
    manageInputs(day);
  });
});
function manageInputs(day) {
  const openTimeInput = document.querySelector("#" + day + " .open-time");
  const closeTimeInput = document.querySelector("#" + day + " .close-time");
  const closedCheckbox = document.querySelector("#" + day + " .closed-checkbox");

  // checkbox 상태에 따라 input 요소들을 활성화 또는 비활성화
  if (closedCheckbox.checked) {
    openTimeInput.disabled = true; // 비활성화
    closeTimeInput.disabled = true; // 비활성화
  } else {
    openTimeInput.disabled = false; // 활성화
    closeTimeInput.disabled = false; // 활성화
  }
}

