// var cafeId = window.location.pathname.substring(1);
//
// fetch("/api/cafe/" + cafeId)
// .then(response => {
//   if (!response.ok) {
//     throw new Error("Failed to fetch cafe info: " + response.statusText);
//   }
//   return response.json();
// })
// .then(cafe => {
//   var cafeInfoDiv = document.getElementById("cafeInfo");
//   cafeInfoDiv.innerHTML = "<h1>" + cafe.name + "</h1>";
//   cafeInfoDiv.innerHTML += "<p>Address: " + cafe.address + "</p>";
//   cafeInfoDiv.innerHTML += "<p>Phone: " + cafe.phoneNum + "</p>";
//
//   // 페이지 제목 변경
//   document.getElementById("pageTitle").innerText = cafe.name + " - 그라인더";
// })
// .catch(error => {
//   console.error(error);
// });

const sellerApplyBtn = document.getElementById('seller_apply_button');
sellerApplyBtn.addEventListener('click', () => {
    let cafeId = sellerApplyBtn.dataset.cafeId
    window.location.href = '/api/cafe/seller_apply/' + cafeId;
})