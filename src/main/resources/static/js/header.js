const alarm = document.querySelector('.header_alarm');
let alarm_box = document.querySelector('.header_alarm_box');

if (alarm) {
    alarm.addEventListener('click', () => {
        if (alarm_box) {
            alarm_box.classList.toggle('alarm_active');
        }
    });
}