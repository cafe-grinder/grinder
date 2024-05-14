# Cafe Grinder
### ☕ 카페 정보와 메뉴에 대한 정보가 있는 커뮤니티
> 카페 정보 제공 소셜네트워크 기반 커뮤니티 웹사이트

![img.png](readme/grinder_banner.png)

### 💎Description
- 국내 1인당 연간 커피 소비량은 405잔(세계 1인당 소비량은 152잔)
- 브랜드 선호도는 카페의 맛이 대부분의 선택 기준

🤔카페 정보 공유와 함께 소통의 장을 연다면 보다 다양하고 넓은 카페 문화가 형성되지 않을까?

🙄SNS처럼 내 생각을 편하게 이야기하고 평가할 수 있는 공간이 있으면 좋지 않을까?

카페 평가 기능과, 팔로우, 북마크 기능을 활용하여 개인 맞춤 카페 추천을 해주는 SNS 웹사이트입니다.

### 시연 영상 



## 💫 Team Members

| 김요한                                               | 김중석                                    | 유호준                                                                    | 윤인선                                | 이은지           | 정인우                                                    |
|---------------------------------------------------|----------------------------------------|------------------------------------------------------------------------|------------------------------------|------------------|--------------------------------------------------------|
| ![img.png](readme/member1.png)                    | ![img.png](readme/member3.png)         | ![img.png](readme/member4.png)                                         | ![img.png](readme/member5.png)     |![img.png](readme/member6.png) | ![img.png](readme/member2.png)                         |
| - 마이페이지(블랙리스트, 팔로우, 북마크) <br/> - 추천 기능(GPT, 스케줄링) | - 관리자 페이지(회원 관리, 신고 관리, 카페 관리, 판매자 관리) | - 로그인 / 회원가입 화면 개발 <br/> - 카페 추가 신청 / 피드 작성 외 6 화면 개발| - 카페 정보(피드 조회, 메뉴 조회, 판매자 신청, 북마크) | - 추후 수정      | - 피드(등록, 수정, 삭제, 좋아요, 태그)<br/>- 댓글(등록 수정 삭제, 대댓글, 좋아요) |



## 💻 Stacks Used
### Back-end
|   Java   |   Spring   |   Spring Boot   |   PostgreSQL   |   AWS   |
| :----------------------------------------------------------: | :----------------------------------------------------------: | :----------------------------------------------------------: | :----------------------------------------------------------: | :----------------------------------------------------------: |
| <div style="display: flex; align-items: flex-start;"><img src="https://techstack-generator.vercel.app/java-icon.svg" alt="icon" width="65" height="65" /></div> | <img alt="spring logo" src="https://www.vectorlogo.zone/logos/springio/springio-icon.svg" height="50" width="50" > | <img alt="spring-boot logo" src="https://t1.daumcdn.net/cfile/tistory/27034D4F58E660F616" width="65" height="65" > | <div style="display: flex; align-items: flex-start;"><img src=“https://github.com/cafe-grinder/grinder/assets/94281256/dc931298-a2d8-46f0-8159-bf4fab1f0264” alt="postgresql" width="65" height="65" /></div> | <div style="display: flex; align-items: flex-start;"><img src="https://techstack-generator.vercel.app/aws-icon.svg" alt="icon" width="65" height="65" /></div> |



### Front-end
|     Html     |     CSS     |     JavaScript     |     Figma     |  
| :----------------------------------------------------------: | :----------------------------------------------------------: | :----------------------------------------------------------: | :----------------------------------------------------------: | 
| <img alt="Html" src ="https://upload.wikimedia.org/wikipedia/commons/thumb/6/61/HTML5_logo_and_wordmark.svg/440px-HTML5_logo_and_wordmark.svg.png" width="65" height="65" /> | <div style="display: flex; align-items: flex-start;"><img src="https://user-images.githubusercontent.com/111227745/210204643-4c3d065c-59ec-481d-ac13-cea795730835.png" alt="CSS" width="50" height="65" /></div> | <div style="display: flex; align-items: flex-start;"><img src="https://techstack-generator.vercel.app/js-icon.svg" alt="icon" width="75" height="75" /></div> | <div style="display: flex; align-items: flex-start;"><img alt="jiralogo" src="https://www.vectorlogo.zone/logos/figma/figma-icon.svg" width="100" height="65"/></div>  |



### Tools
| Github | Discord | Notion | Jira|
| :--------: | :--------: | :------: | :------: |
| <img alt="github logo" src="https://techstack-generator.vercel.app/github-icon.svg" width="65" height="65"> | <img alt="Discord logo" src="https://assets-global.website-files.com/6257adef93867e50d84d30e2/62595384e89d1d54d704ece7_3437c10597c1526c3dbd98c737c2bcae.svg" height="65" width="65"> | <img alt="Notion logo" src="https://www.notion.so/cdn-cgi/image/format=auto,width=640,quality=100/front-static/shared/icons/notion-app-icon-3d.png" height="65" width="65"> |<img alt="Notion logo" src="https://github.com/cafe-grinder/grinder/assets/94281256/6fae7fe5-8441-49be-9d65-5ef66013340d" height="65" width=“100”> |




## 📄 Project Docs



## 🎨사용자 요구사항 명세서

### ✨초안
![img.png](readme/mindmap.png)

### 🏷회원 관리

---

- **회원가입**

| 기능 | 설명 |
| --- | --- |
| 회원가입 | 이메일 인증을 통한 회원 가입 (가입 시 이메일로 임의의 번호를 보내 확인한다)
필수 입력 : [이메일], [이름], [닉네임], [비밀번호], [비밀번호 확인], [성별], [연락처], [생년월일] |
| 이메일,  닉네임 중복 확인 | 아이디 닉네임 [중복 확인] 버튼 클릭 시 확인 할 수 있다. |
- **로그인**

| 기능 | 설명 |
| --- | --- |
| 계정 로그인 | [이메일], [비밀번호]를 입력하여 로그인 |
| 소셜 로그인 | Oauth2를 사용한 소셜 로그인(google,naver) |
| 비밀번호 찾기 | 비밀번호 찾기 요청 시 임의의 값으로 비밀번호를 초기화 하고, 해당 값을 이메일로 전송 |
- **회원 정보**

| 기능 | 설명 |
| --- | --- |
| 마이 페이지 | 로그인 상태에서 [마이페이지] 클릭 시 마이페이지로 이동 |
| 회원 정보 변경 | 마이 페이지에서 [메뉴] 버튼 클릭 시 [회원 정보 수정] 클릭 시 회원 정보 수정 페이지로 이동
변경 가능 : [비밀번호], [닉네임] |
| 작성 글 확인 | 마이페이지에서 [메뉴] ⇒ [내가 쓴 글 보기] 클릭 시 내가 작성한 글을 확인할 수 있다. |
| 팔로워, 팔로잉 목록 | 마이페이지에서 [메뉴] ⇒ [팔로워], [팔로잉] 클릭 시 해당 회원을 확인할 수 있다. |
| 차단한 회원 목록 | 마이페이지에서 [메뉴] ⇒ [차단한 회원] 클릭 시 해당 회원을 확인할 수 있다. |
| 장바구니 목록 | 마이페이지에서 [메뉴] ⇒ [장바구니] 클릭 시 해당 제품을 확인할 수 있다. |

### 👻비회원

---

- **공통**

| 기능 | 설명 |
| --- | --- |
| 열람 | 비회원은 카페정보와 댓글을 볼 수 있으나, 피드는 볼 수 없다. |

### 😀회원(공통)

---

- **공통**

| 기능 | 설명 |
| --- | --- |
| 열람 | 회원은 카페 정보와 댓글과 피드를 모두 볼 수 있다. |
- **신규 장소 등록**

| 기능 | 설명 |
| --- | --- |
| 신규 장소 등록 신청 | 회원은 [장소 등록] 버튼 클릭 시 [카페명],[주소],[연락처]을 작성하고, [신청하기] 버튼을 클릭하여 신청할 수 있다. |
- **신고**

| 기능 | 설명 |
| --- | --- |
| 피드 및 댓글 신고 | 회원은 피드와 댓글의 우측 하단에 있는 [신고] 버튼을 클릭하여 신고할 수 있다. |
- **인증 회원**

| 기능 | 설명 |
| --- | --- |
| 인증 회원 | 특정 팔로잉 수와 리뷰 수, 리뷰 추천 수를 충족하면 자동으로 인증회원으로 분류된다. |
- **차단**

| 기능 | 설명 |
| --- | --- |
| 회원 차단 | 피드나 댓글의 회원 사진을 클릭 후 [차단] 버튼을 클릭하면 해당 회원은 차단 목록에 들어가게 된다. |
- **팔로우**

| 기능 | 설명 |
| --- | --- |
| 팔로우 | 다른 유저의 프로필에서 [팔로우] 버튼을 클릭하여 팔로우할 수 있다. |
| 언팔로우 | 팔로우한 유저의 프로필에서 [언팔로우] 버튼을 클릭하여 언팔로우할 수 있다. |
- **판매자 신청**

| 기능 | 설명 |
| --- | --- |
| 판매자 신청 | [사업자등록번호], [사업자명] 을 입력하고 신청할 수 있다. |

### 😎인증 회원

---

💡 인증 회원은 회원 마크 우측 하단에 특정 마크를 추가하여 인증 회원을 구분할 수 있도록 한다.


### 🐣판매자

---

- **카페 정보**

| 기능 | 설명 |
| --- | --- |
| 카페 정보 페이지 | 판매자는 마이페이지에서 [카페 정보] 페이지를 들어갈 수 있다. |
| 카페 정보 수정 | 판매자는 카페 정보 페이지에서 [수정] 버튼을 클릭하면 수정 페이지로 이동하고 [등록] 버튼을 클릭하여 메뉴를 추가할 수 있다.
필수 정보 : [카페명], [주소], [영업시간], [사진]
선택 정보 : [좌석수] |

- **메뉴 정보**

| 기능 | 설명 |
| --- | --- |
| 메뉴 정보 페이지 | 판매자는 마이페이지에서 [메뉴 정보] 페이지를 들어갈 수 있다. |
| 메뉴 추가 | 판매자는 메뉴 정보 페이지에서 [추가] 버튼을 클릭하면 추가 페이지로 이동하고 [등록] 버튼을 클릭하여 메뉴를 추가할 수 있다.
필수 정보 : [메뉴명], [가격], [용량], [판매유형(상시, 기간한정)]
선택 정보 : [사진], [재료], [산미], [당도] |
| 메뉴 수정 및 삭제 | 판매자는 메뉴 정보 페이지에서 [수정] 및 [삭제] 버튼을 클릭하여 해당 메뉴를 수정 및 삭제할 수 있다. |

### 💼카페 정보

---

> 이커머스 기능은 고려하여 설계하되, 초기 구현에는 추가하지 않기로
>
- **카페  조회**

| 기능 | 설명 |
| --- | --- |
| 카페 목록 | 카페 목록([카페명],[주소],[연락처],[사진], [평균점수])을 보여줍니다. |
| 카페 검색 | 카페의 정보를 검색어로 하여 해당하는 카페를 조회(위치, 영업시간, 평점 등) |
- **카페 정보**

| 기능 | 설명 |
| --- | --- |
| 북마크 | 카페 정보를 북마크할 수 있다. |
| 카페 상세조회 | 카페의 상세정보([카페명], [주소], [연락처],[영업시간],[리뷰 목록],[메뉴 목록]) 조회합니다. |
- **북마크**

| 기능 | 설명 |
| --- | --- |
| 즐겨찾기 | 카페의 [🏷] 클릭 시 카페 즐겨찾기할 수 있다. |
| 북마크 해제 | 카페의 [🏷] 클릭 시 카페 즐겨찾기 해제할 수 있다. |

### 📄피드(리뷰 게시판)

---

- **리뷰 게시판**

| 기능 | 설명 |
| --- | --- |
| 게시글 목록 | 게시글 목록을 팔로잉 게시물 우선 추천과 추천 점수가 높은 피드를 우선으로 보여준다. |
- **피드**

| 기능 | 설명 |
| --- | --- |
| 피드 보기 | 회원은 인증 회원과 관련 없이 카페정보와 피드, 댓글을 볼 수 있다. |
| 피드 작성 | 회원은 [작성] 버튼 클릭 시 피드를 작성할 수 있다.
필수정보: 사진, 리뷰글
선택정보: 시설(화장실이 청결해요,매장이 넓어요,,등등), 분위기(분위기가 좋아요,…등등), 맛(커피가 맛있어요, 디저트가 맛있어요,… 등등) 이 중 최대 3개 고를 수 있다. |
| 피드 수정 | 회원은 [수정] 버튼 클릭 시 피드를 수정할 수 있다. |
| 피드 삭제 | 회원은 [삭제] 버튼 클릭 시 피드를 삭제할 수 있다. |
- **댓글**

| 기능 | 설명 |
| --- | --- |
| 댓글 보기 | 피드의 [댓글] 클릭 시 피드에 댓글을 확인할 수 있다. |
| 댓글 작성 | 회원은 [작성] 버튼 클릭 시 댓글을 작성할 수 있다. |
| 댓글 수정 | 회원은 [수정] 버튼 클릭 시 댓글을 수정할 수 있다. |
| 댓글 삭제 | 회원은 [삭제] 버튼 클릭 시 댓글을 삭제할 수 있다. |
- **추천**

| 기능 | 설명 |
| --- | --- |
| 피드 추천 | 피드의 [♡] 클릭 시 피드를 추천할 수 있다. |
| 피드 추천 해제 | 피드의 [♥] 클릭 시 피드를 추천 해제할 수 있다. |
| 댓글 추천 | 댓글의 [♡] 클릭 시 댓글을 추천할 수 있다. |
| 댓글 추천 해제 | 댓글의 [♥] 클릭 시 댓글을 추천할 해제할 수 있다. |

### 🧙‍♂️분류 및 검색

---

- **메인화면 피드**

| 기능 | 설명 |
| --- | --- |
| 메인화면 피드 생성 | 팔로우한 회원의 피드 5개와, 추천 점수가 높은 피드를 랜덤으로 출력한다. |
- **검색**

| 기능 | 설명 |
| --- | --- |
| 카페 검색 | [주소], [분위기], [이름] 을 검색하여 카페를 찾을 수 있다. |
| 메뉴 검색 | [메뉴명]을 검색하여 카페를 찾을 수 있다. |
- **분류**

| 기능 | 설명 |
| --- | --- |
| 최근 평점 높은 순 | 최근 평점이 높은 순으로 정렬하여 피드를 출력한다. |
| 최근 게시물 순 | 최근 게시물 순으로 정렬하여 피드를 출력한다. |

### 🤖AI 앨런

---

- **지점별 카페 요약**

| 기능 | 설명 |
| --- | --- |
| 카페 리뷰 요약 | “[카페명]에 대한 리뷰를 3줄로 요약해줘.”을 요청하여 카페 정보의 리뷰 요약을 해준다. |
- **추천**

| 기능 | 설명 |
| --- | --- |
| 카페 추천 | [인원수], [목적], [위치], [평점]에 따른 추천을 한다. |
| 메뉴 추천 | [시그니처 메뉴], [평점]에 따른 추천을 한다. |

### 👩‍💻관리자

---

- **공통**

| 기능 | 설명 |
| --- | --- |
| 관리자 페이지 이동 | 관리자로 로그인 시 우측 상단에 [관리자 페이지 이동] 버튼을 통해 관리자 페이지로 이동할 수 있다. |
- **카페 정보**

| 기능 | 설명 |
| --- | --- |
| 카페 정보 추가 | 관리자 페이지에서 카페 정보([카페명], [주소], [연락처], [영업시간])를 입력 후 [생성] 버튼을 클릭하여 추가할 수 있다. |
- **회원**

| 기능 | 설명 |
| --- | --- |
| 회원 검색 | 회원 관리 탭에서 회원 [이메일] 을 입력하면 회원을 검색할 수 있다. |
| 회원 관리 | 등록된 회원의 [정지] 버튼 클릭 시 회원 테이블의 탈퇴가 true로 변경된다.
단, 탈퇴 된 회원은 [정지 해제] 로 나타난다.
- 추가 요망 : [일시 정지] 클릭 시 input 박스가 나타나며, 날짜 입력 시 해당 일시 동안 일시 정지된다. (이 후 해당 일자가 지나면 자동으로 해제) |
  | 인증 회원 관리 | [인증 회원 보기] 클릭 시 인증회원 목록을 확인할 수 있다. |
- **피드**

| 기능 | 설명 |
| --- | --- |
| 피드 검색 | 게시글 관리 탭에서 [게시글]을 입력하면 게시글 목록을 검색할 수 있다. |
| 피드 관리 | 업로드 된 게시글의 [삭제] 버튼 클릭 시 게시글이 삭제된다.(반드시 삭제 여부를 확인한다)
[숨기기] 버튼 클릭 시 게시글 테이블의 [가시여부]가 false로 변경된다. |
- **신고**

| 기능 | 설명 |
| --- | --- |
| 신고 검색 | [게시글] 또는 [신고자]를 입력하면 해당 목록을 검색할 수 있다. |
| 신고 관리 | 신고된 게시글의 [승인] 버튼 클릭 시 [게시글 가시 여부]가 false로 변경된다. |
- **신규 장소 등록 목록**

| 기능 | 설명 |
| --- | --- |
| 신규 장소 등록 검색 | 회원이 신청한 신규 장소 등록의 대한 정보를 검색할 수 있다. |
| 신규 장소 관리 | 등록된 정보의 [승인] 버튼 클릭 시 신규 장소 테이블에서 카페 정보 테이블의 정보로 저장한다. |
- **판매자 신청 목록**

| 기능 | 설명 |
| --- | --- |
| 판매자 신청 검색 | 회원이 신청한 판매자 인증 신청을 검색할 수 있다. |
| 판매자 신청 관리 | 등록된 정보의 [승인] 버튼 클릭 시 회원 테이블의 [판매자정보] 칼럼에 [카페 id]를 입력한다. |

### 🔔알림

---

- **공통**

| 기능 | 설명 |
| --- | --- |
| 알림 확인 | 모든 회원은 [알림] 버튼 클릭 시 알림을 확인할 수 있다. |
- **판매자**

| 기능 | 설명 |
| --- | --- |
| 알림 전송 | 판매자는 즐겨찾기한 회원에 한해 [메시지], [URL] 을 전송할 수 있다. |

## 🔎**개발 일정**

![img.png](readme/jira.png)


## ✨UI(화면) 설계서

|                                     |                                    |
|-------------------------------------|------------------------------------|
| Main Page (Member)                  | Main Page (Guest)                  |
| ![img.png](main.png)                | ![img.png](main(guest).png)        |
| Login Page                          | SignUp Page                        |
| ![img.png](readme/login.png)        | ![img.png](readme/signup.png)      |
| SignUp complete Page                | Modify-info Page                   |
| ![img.png](readme/signupfinish.png) | ![img.png](readme/updatemember.png) |
| Find Password Page                  | Change Password Page               |
| ![img.png](readme/findpassword.png) | ![img.png](readme/changefinish.png) |
| Feed Write Page                     | Admin Page                         |
| ![img.png](readme/feedadd.png)      | ![img.png](readme/admin.png)       |
| my Page                             | follow Page                        |
| ![img.png](readme/myPage.png)       | ![img.png](readme/follow.png)      |
| bookmark Page                       | my Cafe List Page                  |
| ![img.png](readme/bookmark.png)     | ![img.png](readme/myCafeList.png)  |
| myCafe Page                         | myCafe menu add Page               |
| ![img.png](readme/myCafe.png)       | ![img.png](readme/myCafemenu.png)  |
| CafeInfo Page                       | CafeInfoMenu Page                  |
| ![img.png](readme/cafeInfoPage.png)        | ![img.png](readme/cafeInfoMenu.png)       |
| Alan Recommend Page                 | Alan cafe Summary Page             |
| ![img.png](readme/alan_recommend.png)      | ![img.png](readme/Alan%20cafe%20Summary.png)        |


## 📂Project Structure
```
├─ 📁 .github
│  ├─ 📃 pull_request_template.md
│  └─ 📁 workflows
│     ├─ 📃 build.yml
│     └─ 📃 gradle.yml
├─ 📃 .gitignore
├─ 📃 README.md
├─ 📃 appspec.yml
├─ 📃 build.gradle
├─ 📁 gradle
│  └─ 📁 wrapper
│     ├─ 📃 gradle-wrapper.jar
│     └─ 📃 gradle-wrapper.properties
├─ 📃 gradlew
├─ 📃 gradlew.bat
├─ 📁 scripts
│  └─ 📃 deploy.sh
├─ 📃 settings.gradle
└─ 📁 src
   ├─ 📁 main
   │  ├─ 📁 java
   │  │  └─ 📁 com
   │  │     └─ 📁 grinder
   │  │        ├─ 📃 GrinderApplication.java
   │  │        ├─ 📁 config
   │  │        │  ├─ 📃 EmailConfig.java
   │  │        │  ├─ 📃 RedisConfig.java
   │  │        │  ├─ 📃 SchedulingConfig.java
   │  │        │  ├─ 📃 SecurityConfig.java
   │  │        │  └─ 📃 SwaggerConfig.java
   │  │        ├─ 📁 controller
   │  │        │  ├─ 📃 ExControllerAdvice.java
   │  │        │  ├─ 📃 ExRestControllerAdvice.java
   │  │        │  ├─ 📃 FileController.java
   │  │        │  └─ 📃 TestController.java
   │  │        ├─ 📁 domain
   │  │        │  └─ 📁 dto
   │  │        │     ├─ 📃 AlanDTO.java
   │  │        │     └─ 📃 BlacklistDTO.java
   │  │        ├─ 📁 entity
   │  │        │  ├─ 📃 AnalysisTag.java
   │  │        │  └─ 📃 BaseEntity.java
   │  │        └─ 📁 repository
   │  │           ├─ 📃 AnalysisTagRepository.java
   │  │           └─ 📃 BlacklistRepository.java
   │  └─ 📁 resources
   │     ├─ 📃 application-local.properties
   │     ├─ 📃 application-prod.properties
   │     ├─ 📃 application-test.properties
   │     └─ ✏ static
   │        ├─ ✏ css
   │        │  └─ ✏ MyMenuCard.css
   │        ├─ ✏ img
   │        │  └─ ✏ Alan.png
   │        └─ ✏ js
   │           └─ ✏ addMemberForm.js
   └─ 📁 test
      └─ 📁 java
         └─ 📁 com
            └─ 📁 grinder
               └─ 📃 GrinderApplicationTests.java

```

## 🔐ERD Structure
![img.png](readme/ERD.png)

## 🎈API 명세서


## 🏭System Structure
![img.png](readme/System.png)


## 🛠Coding Convention

### Java Convention

[자바 컨벤션](https://github.com/cafe-grinder/grinder/wiki/01-Java-Coding-Convention)

### Other Convention

[기타 컨벤션](https://github.com/cafe-grinder/grinder/wiki/02-Other-Convention)