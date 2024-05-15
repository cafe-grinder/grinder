// 각 목록 조회시 표시되는 페이지 수
let memberPageNum = 0;
let reportPageNum = 0;
let registerPageNum = 0;
let applyPageNum = 0;
let cafePageNum = 0;

// 각 목록 테이블 body 태그
const memberTableBody = document.getElementById('member_list');
const reportTableBody = document.getElementById('report_list');
const registerTableBody = document.getElementById('register_list');
const applyTableBody = document.getElementById('apply_list');
const cafeTableBody = document.getElementById('cafe_list');

//목록 검색 form 태그
const memberForm = document.getElementById('member_form');
const reportForm = document.getElementById('report_form');
const cafeForm = document.getElementById('cafe_form');

// 목록 별 더보기 button 태그
const addMemberBtn = document.getElementById('more_member_button');
const addReportBtn = document.getElementById('more_report_button');
const addRegisterBtn = document.getElementById('more_register_button');
const addApplyBtn = document.getElementById('more_apply_button');
const addCafeBtn = document.getElementById('more_cafe_button');

// 조회를 위한 select 태그
const memberSelect = document.getElementById('member_role');
const reportSelect = document.getElementById('report_content_type');

//사업자등록증 확인을 위한 모달창
const modal = document.getElementById('modal');

// 처음 페이지 접근 시 목록 불러오기
document.addEventListener('DOMContentLoaded', () => {

    loadMemberList(memberPageNum)

    loadReportList(reportPageNum);

    loadRegisterList(registerPageNum);

    loadApplyList(applyPageNum);

    loadCafeList(cafePageNum);

    adminClickEvent();

    adminChangeEvent();

    adminSubmitEvent();

});

// 목록 불러오기 함수
function loadMemberList(memberPageNum) {
    const role = memberSelect.options[memberSelect.selectedIndex].value;

    const nickname = document.getElementById('member_nickname').value;

    const url = '/admin/api/member/search?nickname=' + nickname + '&role=' + role + '&page=' + memberPageNum;
    fetch(url, {
        method: 'GET'
    }).then(response => {
        if (response.status === 401 || response.status === 403) {
            reissue();
        }
        return response.json();
    })
            .then(data => {
                if (data.empty) {
                    addMemberBtn.classList.add('display_none')
                } else {
                    addMemberBtn.classList.remove('display_none');
                    renderMemberList(data.content);
                }
            })
            .catch(error => {
                console.error('The request failed', error);
            });
}
function loadReportList(reportPageNum) {
    const keyword = document.getElementById('report_keyword').value;

    const contentType = reportSelect.options[reportSelect.selectedIndex].value;

    const url = '/admin/api/report/search?keyword='+ keyword +'&contentType=' + contentType +'&page=' + reportPageNum
    fetch(url , {
        method: 'GET'
    })
            .then(response => {
                if (response.status === 401 || response.status === 403) {
                    reissue();
                }
                return response.json();
            })
            .then(data => {
                if (data.empty) {
                    addReportBtn.classList.add('display_none')
                } else {
                    addReportBtn.classList.remove('display_none');
                    renderReportList(data.content);
                }
            })
            .catch(error => {
                console.error('The request failed', error);
            });
}
function loadRegisterList(registerPageNum) {
    const url = '/admin/api/cafe_register?page=' + registerPageNum;
    fetch(url, {
        method: 'GET'
    })
            .then(response => {
                if (response.status === 401 || response.status === 403) {
                    reissue();
                }
                return response.json();
            })
            .then(data => {
                if (data.empty) {
                    addRegisterBtn.classList.add('display_none')
                } else {
                    addRegisterBtn.classList.remove('display_none');
                    renderRegisterList(data.content);
                }
            })
            .catch(error => {
                console.error('The request failed', error);
            });
}
function loadApplyList(applyPageNum) {
    const url = '/admin/api/seller_apply?page=' + applyPageNum;
    fetch(url, {
        method: "GET"
    })
            .then(response => {
                if (response.status === 401 || response.status === 403) {
                    reissue();
                }
                return response.json();
            })
            .then(data => {
                if (data.empty) {
                    addApplyBtn.classList.add('display_none')
                } else {
                    addApplyBtn.classList.remove('display_none');
                    renderApplyList(data.content);
                }
            })
            .catch(error => {
                console.error('The request failed', error)
            })
}
function loadCafeList(cafePageNum) {
    let keyword = document.getElementById('cafe_keyword').value;
    const url = '/admin/api/cafe?keyword=' + keyword + '&page=' + cafePageNum;
    fetch(url, {
        method: 'GET'
    })
            .then(response => {
                if (response.status === 401 || response.status === 403) {
                    reissue();
                }
                return response.json();
            })
            .then(data => {
                if (data.empty) {
                    addCafeBtn.classList.add('display_none')
                } else {
                    addCafeBtn.classList.remove('display_none');
                    renderCafeList(data.content);
                }
            })
            .catch(error => {
                console.error('The request failed', error);
            })
}

// 목록을 화면에 렌더링
function renderMemberList(memberList) {
    memberList.forEach(member => {
        // 테이블 행 생성
        let row = document.createElement('tr');
        row.classList.add('admin_list_row_body');
        row.innerHTML =
                ` <td class="admin_list_data">${member.email}</td>
                 <td>|</td>
                 <td class="admin_list_data">${member.nickname}</td>
                 <td>|</td>
                 <td class="admin_list_data">
                    <select class="change_role" data-member-id="${member.memberId}" ${member.role == "SELLER" || member.role == "ADMIN" ? 'disabled' : ''}>
                        <option value="MEMBER" ${member.role == 'MEMBER' ? 'selected' : ''}>일반회원</option>
                        <option value="VERIFIED_MEMBER" ${member.role == 'VERIFIED_MEMBER' ? 'selected' : ''}>인증회원</option>
                        <option value="SELLER" disabled ${member.role == 'SELLER' ? 'selected' : ''}>판매자</option>
                        <option value="ADMIN" disabled ${member.role == 'ADMIN' ? 'selected' : ''}>관리자</option>
                    </select>
                </td>
                 <td>|</td>
                 <td class="admin_list_data">${member.isDeleted ? '사용불가' : '정상'}</td>
                 <td class="admin_list_blank"></td>
                 <td class="admin_list_button_container"> <button class="admin_list_button member_delete_button" data-member-id="${member.memberId}">정지</button> <button class="admin_list_button member_recover_button" data-member-id="${member.memberId}">해제</button> </td>`;
        memberTableBody.appendChild(row);
    })
}
function renderReportList(reportList) {
    reportList.forEach(report => {
        let row = document.createElement('tr');
        row.classList.add('admin_list_row_body');
        row.innerHTML =
                `<td class="admin_list_data">${report.nickname}</td>
                 <td>|</td>
                 <td class="admin_list_data">${report.contentType == 'FEED' ? '피드' : '댓글'}</td>
                 <td>|</td>
                 <td class="admin_list_data">${report.content}</td>
                 <td class="admin_list_blank"></td>
                 <td class="admin_list_button_container"> <button class="admin_list_button report_accept_button" data-report-id="${report.reportId}">신고 처리</button> <button class="admin_list_button report_delete_button" data-report-id="${report.reportId}">요청 삭제</button> </td>`;
        reportTableBody.appendChild(row);
    })
}
function renderRegisterList(registerList) {
    registerList.forEach(register => {
        let row = document.createElement('tr');
        row.classList.add('admin_list_row_body');
        row.innerHTML =
                `<td class="admin_list_data">${register.nickname}</td>
                <td>|</td>
                <td class="admin_list_data">${register.cafeName}</td>
                <td>|</td>
                <td class="admin_list_data">${register.address}</td>
                <td>|</td>
                <td class="admin_list_data">${register.phoneNum}</td>
                <td class="admin_list_blank"></td>
                <td class="admin_list_button_container"> <button class="admin_list_button register_accept_button" data-register-id="${register.registerId}">등록</button> <button class="admin_list_button register_delete_button" data-register-id="${register.registerId}">삭제</button> </td>`;
        registerTableBody.appendChild(row);
    })
}
function renderApplyList(applyList) {
    applyList.forEach(apply => {
        let row = document.createElement('tr');
        row.classList.add('admin_list_row_body');
        row.innerHTML =
                `<td class="admin_list_data">${apply.nickname}</td>
                <td>|</td>
                <td class="admin_list_data">${apply.cafeName}</td>
                <td>|</td>
                <td class="admin_list_data">${apply.address}</td>
                <td>|</td>
                <td class="admin_list_data">${apply.phoneNum}</td>
                <td>|</td>
                <td class="admin_list_data"><button class="reg_image_button" data-apply-image="${apply.regImageUrl}">사업자등록증</button></td>
                <td class="admin_list_blank"></td>
                <td class="admin_list_button_container"> <button class="admin_list_button apply_accept_button" data-apply-id="${apply.applyId}">등록</button> <button class="admin_list_button apply_delete_button" data-apply-id="${apply.applyId}">삭제</button> </td>`;
        applyTableBody.appendChild(row);
        // 신청시 제출한 사업자등록증 확인
        let regImageBtn = row.querySelector('.reg_image_button');
        regImageBtn.addEventListener('click', () => {
            modal.innerHTML = '';
            modal.innerHTML =
                    `<form method="dialog">
                        <img src="${apply.regImageUrl}" alt="사업자등록증">
                        <button class="modal_button">닫기</button>
                    </form>`;
            modal.showModal();
        })
    })
}
function renderCafeList(cafeList) {
    cafeList.forEach(cafe => {
        let row = document.createElement('tr');
        row.classList.add('admin_list_row_body');
        row.innerHTML =
                `<td class="admin_list_data">${cafe.name}</td>
                <td>|</td>
                <td class="admin_list_data">${cafe.address}</td>
                <td>|</td>
                <td class="admin_list_data">${cafe.phoneNum}</td>
                <td class="admin_list_blank"></td>`;
        cafeTableBody.appendChild(row);
    })
}

function adminClickEvent() {
    document.addEventListener('click', (event) => {
        const target = event.target;
        // 판매자 정보 저장 + 신청 내역 삭제
        if (target.classList.contains('apply_accept_button')) {
            const applyAccept = target.closest('.admin_list_button_container').querySelector('.apply_accept_button');
            let applyId = applyAccept.dataset.applyId;
            let url = '/admin/api/seller_info/' + applyId
            fetch(url, {
                method: 'POST'
            })
                    .then(response => {
                        return response.json();
                    })
                    .then(data => {
                        console.log(data.code)
                        alert(data.message)
                    })
        }
        // 판매자 신청 삭제
        if (target.classList.contains('apply_delete_button')) {
            const applyDelete = target.closest('.admin_list_button_container').querySelector('.apply_delete_button');
            let applyId = applyDelete.dataset.applyId;
            let url = '/admin/api/seller_apply/' + applyId
            fetch(url, {
                method: 'DELETE'
            })
                    .then(response => {
                        return response.json();
                    })
                    .then(data => {
                        console.log(data.code)
                        alert(data.message)
                    })
        }
        // 신규 장소 등록 + 신청 내역 삭제
        if (target.classList.contains('register_accept_button')) {
            const registerAccept = target.closest('.admin_list_button_container').querySelector('.register_accept_button')
            let registerId = registerAccept.dataset.registerId;
            let url = '/admin/api/cafe/' + registerId
            fetch(url, {
                method: 'POST'
            })
                    .then(response => {
                        return response.json();
                    })
                    .then(data => {
                        console.log(data.code)
                        alert(data.message)
                    })
        }
        // 신규 장소 신청 삭제
        if (target.classList.contains('register_delete_button')) {
            const registerDelete = target.closest('.admin_list_button_container').querySelector('.register_delete_button')
            let registerId = registerDelete.dataset.registerId;
            let url = '/admin/api/cafe_register/' + registerId
            fetch(url, {
                method: 'DELETE'
            })
                    .then(response => {
                        return response.json();
                    })
                    .then(data => {
                        console.log(data.code)
                        alert(data.message)
                    })
        }
        // 신고 처리 버튼 누르면 해당 컨텐츠 삭제 + 같은 컨텐츠 신고 내역 모두 삭제
        if (target.classList.contains('report_accept_button')) {
            const reportAccept = target.closest('.admin_list_button_container').querySelector('.report_accept_button')
            let reportId = reportAccept.dataset.reportId;
            let url = '/admin/api/report/' + reportId + '/accepted'
            fetch(url, {
                method: 'DELETE'
            })
                    .then(response => {
                        return response.json();
                    })
                    .then(data => {
                        console.log(data.code)
                        alert(data.message)
                    }).catch(error => {
                console.error('Error:', error);
            })
        }
        // 해당 신고 요청 삭제
        if (target.classList.contains('report_delete_button')) {
            const reportDelete = target.closest('.admin_list_button_container').querySelector('.report_delete_button')
            let reportId = reportDelete.dataset.reportId;
            let url = '/admin/api/report/' + reportId
            fetch(url, {
                method: 'DELETE'
            })
                    .then(response => {
                        return response.json();
                    })
                    .then(data => {
                        console.log(data.code)
                        alert(data.message)
                    }).catch(error => {
                console.error('Error:', error);
            })
        }
        // 정지 버튼 누르면 회원 삭제
        if (target.classList.contains('member_delete_button')) {
            const deleteMember = target.closest('.admin_list_button_container').querySelector('.member_delete_button')
            let memberId = deleteMember.dataset.memberId;
            let url = '/admin/api/member/'+ memberId
            fetch(url, {
                method: 'DELETE'
            })
                    .then(response => {
                        return response.json();
                    })
                    .then(data => {
                        console.log(data.code)
                        alert(data.message)
                    }).catch(error => {
                console.error('Error:', error);
            })
        }
        // 해제 버튼 누르면 회원 정보 복구
        if (target.classList.contains('member_recover_button')) {
            const recoveryMember = target.closest('.admin_list_button_container').querySelector('.member_recover_button')
            let memberId = recoveryMember.dataset.memberId;
            let url = '/admin/api/member/'+ memberId + '/recovery'
            fetch(url, {
                method: 'PUT'
            })
                    .then(response => {
                        return response.json();
                    })
                    .then(data => {
                        console.log(data.code)
                        alert(data.message)
                    }).catch(error => {
                console.error('Error:', error);
            })
        }
        // 더보기 버튼 클릭시 다음 페이지를 불러옴
        switch (target) {
            case addMemberBtn:
                loadMemberList(++memberPageNum);
                break;

            case addReportBtn:
                loadReportList(++reportPageNum);
                break;

            case addRegisterBtn:
                loadRegisterList(++registerPageNum);
                break;

            case addApplyBtn:
                loadApplyList(++applyPageNum);
                break;

            case addCafeBtn:
                loadCafeList(++cafePageNum);
                break;
        }
    })
}

function adminChangeEvent() {
    document.addEventListener('change', (event) => {
        const target = event.target;
        // 회원 권한을 변경하면 수정
        if (target.classList.contains('change_role')) {
            const selectRole = target.closest('.admin_list_data').querySelector('.change_role')
            let memberId = selectRole.dataset.memberId;
            let url = '/admin/api/member/' + memberId + '/role'
            fetch(url, {
                method: 'PUT'
            })
                    .then(response => {
                        return response.json();
                    })
                    .then(data => {
                        console.log(data.code)
                        alert(data.message)
                    }).catch(error => {
                console.error('Error:', error);
            })
        }
        // 회원 권한으로 목록 조회
        if (target == memberSelect) {
            memberTableBody.innerHTML = '';
            memberPageNum = 0;
            loadMemberList(memberPageNum);
        }
        // 콘텐츠 타입으로 신고목록 조회
        if (target == reportSelect) {
            reportTableBody.innerHTML = '';
            reportPageNum = 0;
            loadReportList(reportPageNum);
        }
    })
}

//검색시 목록을 새로 불러옴
function adminSubmitEvent() {
    document.addEventListener('submit', (event) => {
        const target = event.target;

        if (target == memberForm) {
            event.preventDefault();
            memberTableBody.innerHTML = '';
            memberPageNum = 0;
            loadMemberList(memberPageNum);
        }

        if (target == reportForm) {
            event.preventDefault();
            reportTableBody.innerHTML = '';
            reportPageNum = 0;
            loadReportList(reportPageNum);
        }

        if (target == cafeForm) {
            event.preventDefault();
            cafeTableBody.innerHTML = '';
            cafePageNum = 0;
            loadCafeList(cafePageNum);
        }

    })
}


