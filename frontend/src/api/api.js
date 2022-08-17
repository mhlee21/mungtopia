// const HOST = 'https://i7d209.p.ssafy.io/api/v1/';
// const HOST = 'https://i7d209.p.ssafy.io:8081/api/v1/';
const HOST = 'http://localhost:8081/api/v1/';

const AUTH = 'auth/';
const BOARD = 'board/';
const GAME = 'game/';
const ADOPT = 'manage/';
const USER = 'users/';
const CHAT = 'chat/';
const SCHEDULE = 'schedule/';
const APPLICANT = 'applicant/';
const PROTECTOR = 'protector/';
const MEETING = 'meeting/';

export default {
	auth: {
		// 로그인
		login: () => HOST + AUTH + 'login',
		// 유저 정보 받기
		getUser: () => HOST + 'users',
	},
	board: {
		// 전체 글 불러오기
		boardMain: ({ tagNo, userSeq }) =>
			HOST + BOARD + `${tagNo}/` + `${userSeq}`,
		// 상세 글 불러오기
		boardDetail: boardId => HOST + BOARD + `detail/` + `${boardId}`,
		// 입양 상태 확인
		adoptionStatusCheck: userSeq => HOST + BOARD + `status/` + `${userSeq}`,
		// 글 쓰기
		boardCreate: () => HOST + BOARD,
		// 글 수정
		boardUpdate: boardId => HOST + BOARD + `${boardId}`,
		// 글 삭제
		boardDelete: boardId => HOST + BOARD + `${boardId}`,
		// 댓글 리스트 가져오기
		commentList: boardId => HOST + BOARD + `${boardId}` + `/comments`,
		// 댓글 쓰기
		commentCreate: boardId => HOST + BOARD + `${boardId}` + `/comments`,
		// 댓글 수정
		commentUpdate: (boardId, commentId) =>
			HOST + BOARD + `${boardId}/` + `comments/` + `${commentId}`,
		// 댓글 삭제
		commentDelete: (boardId, commentId) =>
			HOST + BOARD + `${boardId}/` + `comments/` + `${commentId}`,
		// 대댓글 쓰기
		replyCreate: (boardId, commentId) =>
			HOST + BOARD + `${boardId}/` + `comments/` + `${commentId}`,
		// 대댓글 수정
		replyUpdate: (boardId, commentId, replyId) =>
			HOST +
			BOARD +
			`${boardId}/` +
			`comments/` +
			`${commentId}/` +
			`reply/` +
			`${replyId}`,
		// 대댓글 삭제
		replyDelete: (boardId, commentId, replyId) =>
			HOST +
			BOARD +
			`${boardId}/` +
			`comments/` +
			`${commentId}/` +
			`reply/` +
			`${replyId}`,
		// 검색
		boardSearch: tagNum => HOST + BOARD + `search/` + `${tagNum}`,
		// 좋아요 여부
		isLike: (boardId, userSeq) =>
			HOST + BOARD + `like/` + `${boardId}/` + `${userSeq}`,
		// 좋아요
		likeCreate: (boardId, userSeq) =>
			HOST + BOARD + `like/` + `${boardId}/` + `${userSeq}`,
		// 좋아요 삭제
		likeDelete: (boardId, userSeq) =>
			HOST + BOARD + `like/` + `${boardId}/` + `${userSeq}`,
		// 별표 여부
		haveStar: (boardId, userSeq) =>
			HOST + BOARD + `star/` + `${boardId}/` + `${userSeq}`,
		// 별표
		starCreate: (boardId, userSeq) =>
			HOST + BOARD + `star/` + `${boardId}/` + `${userSeq}`,
		// 별표삭제
		starDelete: (boardId, userSeq) =>
			HOST + BOARD + `star/` + `${boardId}/` + `${userSeq}`,
		// 입양신청서 작성
		applicationCreate: boardId =>
			HOST + BOARD + 'detail/' + `${boardId}/` + `applicant`,
	},
	game: {
		// 게임 기록 저장
		saveGame: () => HOST + GAME,
		receiveGame: () => HOST + GAME + 'matching/',
		clearGame: userSeq => HOST + GAME + `${userSeq}`,
	},
	adopt: {
		// 입양하기 메인
		applicantMain: userSeq => HOST + ADOPT + APPLICANT + `${userSeq}`,
		// 입양하기 상세
		applicantDetail: applicationId =>
			HOST + ADOPT + APPLICANT + 'detail/' + `${applicationId}`,
		// 입양하기 상세 - 입양절차
		applicantDetailProcess: applicationId =>
			HOST + ADOPT + APPLICANT + 'process/' + `${applicationId}`,
		// 입양 취소
		applicantCancel: adoptionProcessId =>
			HOST + ADOPT + APPLICANT + `${adoptionProcessId}`,
		// 입양보내기 메인
		protectorMain: userSeq => HOST + ADOPT + PROTECTOR + `${userSeq}`,
		// 입양보내기 상세
		protectorDetail: boardId =>
			HOST + ADOPT + PROTECTOR + 'detail/' + `${boardId}`,
		// 입양 보내기 상세 - 입양 절차
		protectorDetailProcess: adoptionProcessId =>
			HOST + ADOPT + PROTECTOR + `detail/` + `user/` + `${adoptionProcessId}`,
		// 입양 반려
		protectorCancel: adoptionProcessId =>
			HOST + ADOPT + PROTECTOR + `${adoptionProcessId}`,
		// 입양 반려
		deleteAdoption: adoptionProcessId =>
			HOST + ADOPT + 'adoption/' + `${adoptionProcessId}`,
		// 입양 상태 변경
		processUpdate: adoptionProcessId =>
			HOST + ADOPT + PROTECTOR + `${adoptionProcessId}`,
		// 입양 신청서
		application: applicationId => HOST + ADOPT + `${applicationId}`,
		// 웹소켓 연결
		chatStart: () => HOST + CHAT,
		// 채팅
		chats: () => HOST + CHAT + 'log',
		// 채팅 보내기
		chatCreate: (chatRoomId, userSeq) =>
			HOST + ADOPT + CHAT + `${chatRoomId}/` + `${userSeq}`,
		// 일정 가져오기
		schedules: adoptionProcessId =>
			HOST + ADOPT + SCHEDULE + `${adoptionProcessId}`,
		// 일정 예약
		scheduleCreate: adoptionProcessId =>
			HOST + ADOPT + SCHEDULE + `${adoptionProcessId}`,
		// 일정 수정
		scheduleUpdate: adoptionProcessId =>
			HOST + ADOPT + SCHEDULE + `${adoptionProcessId}`,
		// 일정 삭제
		scheduleDelete: adoptionProcessId =>
			HOST + ADOPT + SCHEDULE + `${adoptionProcessId}`,
	},
	meeting: {
		// 오픈비두 들어오기
		getOpenViduToken: userSeq => HOST + MEETING + `${userSeq}`,
		// 오픈비두 나가기
		sessionDelete: applicationId => HOST + MEETING + `${applicationId}`,
	},
	user: {
		// 유저 게시판 정보
		profileBoardList: userSeq => HOST + USER + BOARD + `${userSeq}`,
		// 유저 프로필 이미지 수정
		profileImageUpdate: userSeq => HOST + USER + `${userSeq}`,
		// 유저 상세 정보
		profileDetail: userSeq => HOST + USER + 'detail/' + `${userSeq}`,
		// 유저 정보 수정
		profileDetailUpdate: userSeq => HOST + USER + 'detail/' + `${userSeq}`,
		// 유저 닉네임 수정
		profileNameUpdate: userSeq => HOST + USER + 'name/' + `${userSeq}`,
	},
};
