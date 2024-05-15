document.getElementById("cafe-btn").addEventListener("click", function (){
  const cafeName = document.getElementById("cafe-name").value;
  const address = document.getElementById("address").value;
  const selectValue = document.getElementById("contact").value;
  const input1Value = document.querySelector(".contact-box input:nth-of-type(1)").value;
  const input2Value = document.querySelector(".contact-box input:nth-of-type(2)").value;
  const phoneNum = selectValue + input1Value + input2Value;

  // const days = ['monday', 'tuesday', 'wednesday', 'thursday', 'friday', 'saturday', 'sunday'];
  // let openingHoursData = [];
  //
  // days.forEach(day => {
  //   let openTime = document.querySelector(`input[name="${day}_open"]`).value;
  //   let closeTime = document.querySelector(`input[name="${day}_close"]`).value;
  //   let isHoliday = document.querySelector(`input[name="${day}_holiday"]`).checked;
  //
  //   if (!isHoliday && (!validateTime(openTime) || !validateTime(closeTime) || !openTime >= closeTime)) {
  //     alert(`${day.charAt(0).toUpperCase() + day.slice(1)} 올바르지 않은 형식입니다.`);
  //     return; // 오류 발생 시 함수 종료
  //   }
  //
  //   openingHoursData.push({
  //     day: day,
  //     openTime: openTime,
  //     closeTime: closeTime,
  //     isHoliday: isHoliday
  //   });
  // });
  const requestData = {
    "name": cafeName,
    "address": address,
    "phoneNum": phoneNum,
  };
  // CafeRegisterController.addCafeRegister
  fetch('/api/cafe_register/newcafe', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(requestData)
  })
  .then(response => {
    if (response.ok) {
      alert("신규 장소 신청이 등록되었습니다.")
      return response.json();
    } else {
      console.log(response);
      alert("신규 장소 신청에 실패했습니다.")
      throw new Error('카페정보 등록 실패');
    }
  })
  // .then(data => {
  //   const secondUrl = `/api/cafe_register/${data.message}/opening_hours`;
  //   // OpeningHoursController.addOpeningHours
  //   return fetch(secondUrl, {
  //     method: 'POST',
  //     headers: {
  //       'Content-Type': 'application/json'
  //     },
  //     body: JSON.stringify(openingHoursData)
  //   });
  // })
  // .then(response => response.json())
  // .then(data => {
  //   console.log('Success:', data);
  // })
  // // .then(response => {
  // //   console.log(response);
  // //   if (response.ok) {
  // //     alert("운영시간이 등록되었습니다.")
  // //   } else {
  // //     alert("운영시간 등록에 실패했습니다.")
  // //   }
  // // })
  .catch(error => {
    console.error('Error:', error);
  });
});

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

function validateTime(time) {
  const regex = /^([01]?[0-9]|2[0-3]):[0-5][0-9]$/;
  return regex.test(time);
}