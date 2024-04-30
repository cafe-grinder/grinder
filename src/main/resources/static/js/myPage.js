let myPageMenu = document.querySelector('.mypage_menu_list');
let myMenuButton = document.querySelector('.mypage_menu_button');

myMenuButton.addEventListener('click', () => {
    myPageMenu.classList.toggle('menu_active');
})