const alarm = document.querySelector('.header_alarm');
let alarm_box = document.querySelector('.header_alarm_box');

alarm.addEventListener('click', () => {
    alarm_box.classList.toggle('alarm_active');
})