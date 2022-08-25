## 📌Index

1. [멍토피아(Mungtopia) 서비스 소개](#🐶멍토피아(Mungtopia)-서비스-소개)
2. [주요 기술 스택](#🔧주요-기술-스택)
3. [개발 기간 및 팀원 역할 소개](#👩‍👩‍👧개발-기간-및-팀원-역할-소개)
4. [프로젝트 산출물](#📚프로젝트-산출물)


# 🐶멍토피아(Mungtopia) 서비스 소개

멍토피아는 '강아지와 함께할 수 있는 유토피아를 꿈꾼다' 라는 뜻을 가지고 있습니다.

멍토피아에서 여러분의 새로운 가족을 만나보세요!

## 주요 서비스 화면
### SNS 형식의 입양/후기/잡담 게시판
<img src="README/mungtopia-main.gif" height="500">


### 게임을 통한 강아지 매칭
<img src="README/game1.gif" height="500"><img src="README/game2.gif" height="500"><img src="README/game3.gif" height="500">


### 입양 신청부터 완료까지 모든 입양 절차를 한 플랫폼에서 진행 가능

입양신청

<img src="README/application.gif" height="500">


입양신청 - 허가

<img src="README/protector-pass.gif" height="500">

채팅

<img src="README/chat-protector.gif" height="500"><img src="README/chat-applicant.gif" height="500">

화상미팅

<img src="README/webRTC-protector.gif" height="500"><img src="README/webRTC-applicant.gif" height="500">

글 생성

<img src="README/create_board.gif" height="500">

마이페이지

<img src="README/mypage.gif" height="500">


## 기대효과

![expected_effect](README/expected_effect.png)

# 🔧주요 기술 스택

![Architecture](README/Architecture.png)

**Frontend**
- ![title](https://img.shields.io/badge/-Vue3-4FC08D?&logo=Vue.js&logoColor=white)
- ![title](https://img.shields.io/badge/-HTML5-E34F26?&logo=html5&logoColor=white)
- ![title](https://img.shields.io/badge/-CSS3-1572B6?&logo=CSS3&logoColor=white)
- ![title](https://img.shields.io/badge/-Node.js_16.16.0-339933?&logo=Node.js&logoColor=white)
- ![title](https://img.shields.io/badge/-Sock.js-000000?&logoColor=white)
- ![title](https://img.shields.io/badge/-WebRTC-333333?&logo=WebRTC&logoColor=white)
- ![title](https://img.shields.io/badge/-Webpack-7ac5f1?&logo=Webpack&logoColor=white)
- ![title](https://img.shields.io/badge/-Babel-eece4f?&logo=Babel&logoColor=white)
​

**Backend**
- ![title](https://img.shields.io/badge/-SpringBoot-6DB33F?&logo=SpringBoot&logoColor=white)
- ![title](https://img.shields.io/badge/-SpringSecurity-6DB33F?&logo=SpringSecurity&logoColor=white)
- ![title](https://img.shields.io/badge/-Java_11-000000?&logoColor=white)
- ![title](https://img.shields.io/badge/-WebRTC-333333?&logo=WebRTC&logoColor=white)
- ![title](https://img.shields.io/badge/-JPA-000000&logoColor=white)
- ![title](https://img.shields.io/badge/-Stomp-000000?&logoColor=white)
- ![title](https://img.shields.io/badge/-MySQL_8.0.29-4479A1?&logo=MySQL&logoColor=white)
​

**Infra**
- ![title](https://img.shields.io/badge/-EC2-232F3E?&logo=Amazon-AWS&logoColor=white)
- ![title](https://img.shields.io/badge/-NGINX-009639?&logo=NGINX&logoColor=white)
- ![title](https://img.shields.io/badge/-Docker-2496ED?&logo=Docker&logoColor=white)
- ![title](https://img.shields.io/badge/-Jenkins-D24939?&logo=Jenkins&logoColor=white)


**ETC**
- ![title](https://img.shields.io/badge/-Github-181717?&logo=Github&logoColor=white)
- ![title](https://img.shields.io/badge/-Mattermost-0058CC?&logo=Mattermost&logoColor=white)
- ![title](https://img.shields.io/badge/-Notion-000000?&logo=Notion&logoColor=white)



# 👩‍👩‍👧개발 기간 및 팀원 역할 소개

## 개발 기간

2022.07.11 ~ 2022.08.19 (6주)

## 팀원

<table>
    <tr>
        <td height="140px" align="center"> <a href="https://github.com/lea-hwang">
            <img src="https://avatars.githubusercontent.com/u/37495515?v=4" width="140px" /> <br>황희원</a> <br>
        </td>
        <td height="140px" align="center"> <a href="https://github.com/mhlee21">
            <img src="https://avatars.githubusercontent.com/u/79842676?v=4" width="140px" /> <br>이미현</a> <br>
        </td>
        <td height="140px" align="center"> <a href="https://github.com/YeonJeongLee00">
            <img src="https://avatars.githubusercontent.com/u/67946956?s=400&u=be57aa0004e475a70f9431b350b4505b7f64181f&v=4" width="140px" /> <br>이연정</a> <br>
        </td>
    </tr>
    <tr>
        <td align="center">
        팀장/Front-End
        </td>
        <td align="center">
        팀원/Back-End
        </td>
        <td align="center">
        팀원/Back-End
        </td>
    </tr>
</table>

## 역할분담
<br>

[![title](https://img.shields.io/badge/DEVLOPER-황희원-ff69b4)](https://github.com/lea-hwang)
  * 목업(Figma) 디자인
  * 컴포넌트 구현 및 CSS(게시판 / 마이페이지 / 입양관리 / 게임 )
  * WebRTC (FE 기능 구현)

[![title](https://img.shields.io/badge/DEVLOPER-이미현-ff69b4)](https://github.com/mhlee21)
  * DB 설계 및 구축 / JPA 도메인 설계
  * 게시판 API 개발
  * 채팅 구현 (STOMP, SockJS)
  * 배포환경 구축 (Docker, Jenkins)
  * WebRTC (Openvidu 서버 배포)

[![title](https://img.shields.io/badge/DEVLOPER-이연정-ff69b4)](https://github.com/YeonJeongLee00)
  * DB 설계 및 구축 / JPA 도메인 설계
  * 입양관리 API 개발
  * 소셜로그인 구현 (Spring Security)
  * 배포환경 구축 (Docker, Jenkins)
  * WebRTC (BE 기능 구현)


# 📚프로젝트 산출물
## Notion
👉[Mungtopia 노션 바로가기](https://mountain-failing-553.notion.site/6360b0ee504e46d99089219e36723bc2)

![notion](README/notion.png)

## Mockup
![mockup](README/mockup.png)

## ERD

