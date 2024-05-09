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

const memberSelect = document.getElementById('member_role');
const reportSelect = document.getElementById('report_content_type');

// 처음 페이지 접근 시 목록 불러오기
document.addEventListener('DOMContentLoaded', () => {

    loadMemberList(memberPageNum)

    loadReportList(reportPageNum);

    loadRegisterList(registerPageNum);

    loadApplyList(applyPageNum);

    loadCafeList(cafePageNum);
});

// 검색 시 목록을 새로 불러옴
memberForm.addEventListener('submit', () => {
    event.preventDefault();
    memberTableBody.innerHTML = '';
    memberPageNum = 0;
    loadMemberList(memberPageNum);
});
reportForm.addEventListener('submit', () => {
    event.preventDefault();
    reportTableBody.innerHTML = '';
    reportPageNum = 0;
    loadReportList(reportPageNum);
});
cafeForm.addEventListener('submit', () => {
    event.preventDefault();
    cafeTableBody.innerHTML = '';
    cafePageNum = 0;
    loadCafeList(cafePageNum);
})

// 회원 권한으로 목록 조회
memberSelect.addEventListener('change', () => {
    memberTableBody.innerHTML = '';
    memberPageNum = 0;
    loadMemberList(memberPageNum);
})

// 콘텐츠 타입으로 신고목록 조회
reportSelect.addEventListener('change', () => {
    reportTableBody.innerHTML = '';
    reportPageNum = 0;
    loadReportList(reportPageNum);
})

// 더보기 버튼 클릭시 다음 페이지를 불러옴
addMemberBtn.addEventListener('click', () => {
    loadMemberList(++memberPageNum);
});
addReportBtn.addEventListener('click', () => {
    loadReportList(++reportPageNum);
});
addRegisterBtn.addEventListener('click', () => {
    loadRegisterList(++registerPageNum);
})
addApplyBtn.addEventListener('click', () => {
    loadApplyList(++applyPageNum);
})
addCafeBtn.addEventListener('click', () => {
    loadCafeList(++cafePageNum);
})


// 목록 불러오기 함수
function loadMemberList(memberPageNum) {
    const role = memberSelect.options[memberSelect.selectedIndex].value;

    const nickname = document.getElementById('member_nickname').value;

    const url = '/api/member/search?nickname=' + nickname + '&role=' + role + '&page=' + memberPageNum;
    fetch(url, {
        method: 'GET'
    }).then(response => {
        if (!response.ok) {
            throw new Error('The request failed');
        }
        return response.json();
    })
            .then(data => {
                renderMemberList(data.content);
            })
            .catch(error => {
                console.error('The request failed', error);
            });
};
function loadReportList(reportPageNum) {
    const keyword = document.getElementById('report_keyword').value;

    const contentType = reportSelect.options[reportSelect.selectedIndex].value;

    const url = '/api/report/search?keyword='+ keyword +'&contentType=' + contentType +'&page=' + reportPageNum
    fetch(url , {
        method: 'GET'
    })
            .then(response => {
                if (!response.ok) {
                    throw new Error('The request failed');
                }
                return response.json();
            })
            .then(data => {
                renderReportList(data.content);
            })
            .catch(error => {
                console.error('The request failed', error);
            });
};
function loadRegisterList(registerPageNum) {
    const url = '/api/cafe_register?page=' + registerPageNum;
    fetch(url, {
        method: 'GET'
    })
            .then(response => {
                if (!response.ok) {
                    throw new Error('The request failed');
                }
                return response.json();
            })
            .then(data => {
                renderRegisterList(data.content);
            })
            .catch(error => {
                console.error('The request failed', error);
            });
};
function loadApplyList(applyPageNum) {
    const url = '/api/seller_apply?page=' + applyPageNum;
    fetch(url, {
        method: "GET"
    })
            .then(response => {
                if (!response.ok) {
                    throw new Error('The request failed');
                }
                return response.json();
            })
            .then(data => {
                renderApplyList(data.content);
            })
            .catch(error => {
                console.error('The request failed', error)
            })
}
function loadCafeList(cafePageNum) {
    let keyword = document.getElementById('cafe_keyword').value;

    const url = '/api/cafe/admin?keyword=' + keyword + '&page=' + cafePageNum;
    fetch(url, {
        method: 'GET'
    })
            .then(response => {
                if (!response.ok) {
                    throw new Error('The request failed');
                }
                return response.json();
            })
            .then(data => {
                renderCafeList(data.content);
            })
            .catch(error => {
                console.error('The request failed', error);
            })
}

// 목록을 화면에 렌더링
function renderMemberList(memberList) {
    memberList.forEach(member => {
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
                 <td class="admin_list_button_container"> <button class="admin_list_button member_delete_button" data-member-id="${member.memberId}")">정지</button> <button class="admin_list_button member_recover_button" data-member-id="${member.memberId}">해제</button> </td>`;
        memberTableBody.appendChild(row);
        let select = row.querySelector('.change_role');
        select.addEventListener('change', () => {
            let memberId = select.dataset.memberId;
            let url = '/api/member/' + memberId + '/role'
            fetch(url, {
                method: 'PUT'
            })
                    .then(response => {
                        if (!response.ok) {
                            throw new Error('The request failed');
                        }
                        return response.json();
                    })
                    .then(data => {
                        console.log(data.code)
                        alert(data.message)
                    })
        });

        let deleteMember = row.querySelector('.member_delete_button');
        deleteMember.addEventListener('click', () => {
            let memberId = deleteMember.dataset.memberId;
            let url = '/api/member/'+ memberId
            fetch(url, {
                method: 'DELETE'
            })
                    .then(response => {
                        if (!response.ok) {
                            throw new Error('The request failed');
                        }
                        return response.json();
                    })
                    .then(data => {
                        console.log(data.code)
                        alert(data.message)
                    })
        })

        let recoverMember = row.querySelector('.member_recover_button');
        recoverMember.addEventListener('click', () => {
            let memberId = recoverMember.dataset.memberId;
            let url = '/api/member/'+ memberId + '/recovery'
            fetch(url, {
                method: 'PUT'
            })
                    .then(response => {
                        if (!response.ok) {
                            throw new Error('The request failed');
                        }
                        return response.json();
                    })
                    .then(data => {
                        console.log(data.code)
                        alert(data.message)
                    })
        })
    })
}
function renderReportList(reportList) {
    reportList.forEach(report => {
        let row = document.createElement('tr');
        row.classList.add('admin_list_row_body');
        row.innerHTML =
                `<td class="admin_list_data">${report.nickname}</td>
                 <td>|</td>
                 <td class="admin_list_data">${report.contentType}</td>
                 <td>|</td>
                 <td class="admin_list_data">${report.content}</td>
                 <td class="admin_list_blank"></td>
                 <td class="admin_list_button_container"> <button class="admin_list_button report_accept_button">신고 처리</button> <button class="admin_list_button report_delete_button">요청 삭제</button> </td>`;
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
                <td class="admin_list_button_container"> <button class="admin_list_button register_accept_button">등록</button> <button class="admin_list_button register_delete_button">삭제</button> </td>`;
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
                <td class="admin_list_data"><a href="${apply.regImageUrl}">사업자등록증</a></td>
                <td class="admin_list_blank"></td>
                <td class="admin_list_button_container"> <button class="admin_list_button apply_accept_button">등록</button> <button class="admin_list_button apply_delete_button">삭제</button> </td>`;
        applyTableBody.appendChild(row);
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