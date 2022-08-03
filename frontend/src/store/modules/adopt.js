import api from '@/api/api';
import axios from 'axios';

export default {
	namespaced: true,
	state() {
		return {
			adoptType: '',
			applicantMainList: [],
			protectorMainList: [],
			applicantDetail: {},
			protectorDetail: {},
			adoptProcess: [],
			activeApplicant: null,
			adoptionProcessId: null,
			applicationStatus: null,
			you: {},
			chatList: [],
		};
	},
	getters: {
		adoptType: state => state.adoptType,
		applicantMainList: state => state.applicantMainList,
		protectorMainList: state => state.protectorMainList,
		isApplicant: state => state.adoptType === 'applicant',
		applicantDetail: state => state.applicantDetail,
		protectorDetail: state => state.protectorDetail,
		adoptProcess: state => state.adoptProcess,
		activeApplicant: state => state.activeApplicant,
		adoptionProcessId: state => state.adoptionProcessId,
		applicationStatus: state => state.applicationStatus,
		chatList: state => state.chatList,
		you: state => state.you,
	},
	mutations: {
		SET_ADOPT_TYPE: (state, adoptType) => (state.adoptType = adoptType),
		SET_APPLICANT_MAIN_LIST: (state, applicantMainList) =>
			(state.applicantMainList = applicantMainList),
		SET_PROTECTOR_MAIN_LIST: (state, protectorMainList) =>
			(state.protectorMainList = protectorMainList),
		SET_APPLICANT_DETAIL: (state, applicantDetail) =>
			(state.applicantDetail = applicantDetail),
		SET_PROTECTOR_DETAIL: (state, protectorDetail) =>
			(state.protectorDetail = protectorDetail),
		SET_ADOPT_PROCESS: (state, adoptProcess) =>
			(state.adoptProcess = adoptProcess),
		SET_ACTIVE_APPICANT: (state, activeApplicant) =>
			(state.activeApplicant = activeApplicant),
		SET_ADOPTION_PROCESS_ID: (state, adoptionProcessId) =>
			(state.adoptionProcessId = adoptionProcessId),
		SET_APPLICATION_STATUS: (state, applicationStatus) =>
			(state.applicationStatus = applicationStatus),
		SET_CHAT_LIST: (state, chatList) => (state.chatList = chatList),
		SET_YOU: (state, you) => (state.you = you),
		UPDATE_APPLICATION_STATUS: state => {
			state.applicationStatus += 1;
		},
		UPDATE_NEW_CHAT: (state, chat) => {
			state.chatList.push(chat);
		},
	},
	actions: {
		// 입양하기 메인
		fetchApplicantMainList: ({ commit, getters }) => {
			axios({
				url: api.adopt.applicantMain(getters.user.userId),
				method: 'get',
				headers: getters.authHeader,
			})
				.then(res => {
					console.log(res.data.application_list);
					commit('SET_ADOPT_TYPE', 'applicant');
					commit('SET_APPLICANT_MAIN_LIST', res.data.application_list);
				})
				.catch(err => {
					console.error(err.response);
				});
			// const newApplicationList = [
			// 	{
			// 		application_id: 1,
			// 		dog_img:
			// 			'https://upload.wikimedia.org/wikipedia/commons/c/c4/Emily_Maltese.jpg', // 제일 첫번째 사진
			// 		dog_name: '처음',
			// 		application_status: 1,
			// 	},
			// 	{
			// 		application_id: 2,
			// 		dog_img:
			// 			'https://upload.wikimedia.org/wikipedia/commons/c/c4/Emily_Maltese.jpg', // 제일 첫번째 사진
			// 		dog_name: '예시',
			// 		application_status: 6,
			// 	},
			// ];
			// commit('SET_ADOPT_TYPE', 'applicant');
			// commit('SET_APPLICANT_MAIN_LIST', newApplicationList);
		},
		// 입양하기 & 입양보내기 메인
		changeAdoptType: ({ commit, getters }, adoptType) => {
			if (adoptType === 'applicant') {
				// 입양하기 버튼
				axios({
					url: api.adopt.applicantMain(getters.user.userId),
					method: 'get',
					headers: getters.authHeader,
				})
					.then(res => {
						console.log(res.data.application_list);
						commit('SET_ADOPT_TYPE', 'applicant');
						commit('SET_APPLICANT_MAIN_LIST', res.data.application_list);
					})
					.catch(err => {
						console.error(err.response);
					});
			} else {
				// 입양보내기 버튼
				axios({
					url: api.adopt.protectorMain(getters.user.userId),
					method: 'get',
					headers: getters.authHeader,
				})
					.then(res => {
						console.log(res.data.board_list);
						commit('SET_ADOPT_TYPE', 'protector');
						commit('SET_PROTECTOR_MAIN_LIST', res.data.board_list);
					})
					.catch(err => {
						console.error(err.response);
					});
			}

			// if (adoptType === 'applicant') {
			// 	const newApplicationList = [
			// 		{
			// 			application_id: 1,
			// 			dog_img:
			// 				'https://upload.wikimedia.org/wikipedia/commons/c/c4/Emily_Maltese.jpg', // 제일 첫번째 사진
			// 			dog_name: '버튼누르기',
			// 			application_status: 1,
			// 		},
			// 		{
			// 			application_id: 2,
			// 			dog_img:
			// 				'https://upload.wikimedia.org/wikipedia/commons/c/c4/Emily_Maltese.jpg', // 제일 첫번째 사진
			// 			dog_name: '예시',
			// 			application_status: 6,
			// 		},
			// 	];
			// 	commit('SET_ADOPT_TYPE', 'applicant');
			// 	commit('SET_APPLICANT_MAIN_LIST', newApplicationList);
			// } else {
			// 	const newProtectorMainList = [
			// 		{
			// 			board_id: 1,
			// 			dog_img:
			// 				'https://upload.wikimedia.org/wikipedia/commons/c/c4/Emily_Maltese.jpg', // 제일 첫번째 사진
			// 			dog_name: '보호자 예시',
			// 			adoption_status: 0, // 0, 1, 2
			// 		},
			// 		{
			// 			board_id: 2,
			// 			dog_img:
			// 				'https://upload.wikimedia.org/wikipedia/commons/c/c4/Emily_Maltese.jpg', // 제일 첫번째 사진
			// 			dog_name: '몽이',
			// 			adoption_status: 1, // 0, 1, 2
			// 		},
			// 	];
			// 	commit('SET_ADOPT_TYPE', 'protector');
			// 	commit('SET_PROTECTOR_MAIN_LIST', newProtectorMainList);
			// }

			//  백엔드에 요청
		},

		// 입양하기 상세
		fetchApplicantDetail: ({ commit, getters }, applicationId) => {
			axios({
				url: api.adopt.applicantDetail(applicationId),
				method: 'get',
				headers: getters.authHeader,
			})
				.then(res => {
					console.log(res.data);
					commit('SET_APPLICANT_DETAIL', res.data);
					commit('SET_APPLICATION_STATUS', res.data.application_status);
				})
				.catch(err => {
					console.error(err.response);
				});

			// const newApplicantDetail = {
			// 	dog_img:
			// 		'https://upload.wikimedia.org/wikipedia/commons/c/c4/Emily_Maltese.jpg', // 제일 첫번째 사진
			// 	dog_name: '몽이',
			// 	board_id: 1,
			// 	chat_room_id: 1,
			// 	application_status: 2,
			// 	meeting_room: {
			// 		meeting_room_id: 1,
			// 		active: true,
			// 	},
			// };
			// commit('SET_APPLICANT_DETAIL', newApplicantDetail);
			// commit('SET_APPLICATION_STATUS', newApplicantDetail.application_status);
		},
		// 입양하기 - 입양절차
		fetchApplicantAdoptProcess: ({ commit, getters }, applicationId) => {
			axios({
				url: api.adopt.applicantDetailProcess(applicationId),
				method: 'get',
				headers: getters.authHeader,
			})
				.then(res => {
					console.log(res.data.adopt_process);
					commit('SET_ADOPT_PROCESS', res.data.adopt_process);
					commit('SET_ADOPTION_PROCESS_ID', res.data.adoption_process_id);
				})
				.catch(err => {
					console.error(err.response);
				});
			// const adoptionProcessId = 1;
			// const adoptProcesses = [
			// 	{
			// 		step: 1,
			// 		step_status: true,
			// 		date: '2022.07.10',
			// 	},
			// 	{
			// 		step: 2, // 1,2,3,4,5
			// 		step_status: true,
			// 		date: '2022.07.12 2PM',
			// 	},
			// 	{
			// 		step: 3, // 1,2,3,4,5
			// 		step_status: false,
			// 		date: '',
			// 	},
			// 	{
			// 		step: 4, // 1,2,3,4,5
			// 		step_status: false,
			// 		date: '',
			// 	},
			// 	{
			// 		step: 5, // 1,2,3,4,5
			// 		step_status: false,
			// 		date: '',
			// 	},
			// ];
			// commit('SET_ADOPTION_PROCESS_ID', adoptionProcessId);
			// commit('SET_ADOPT_PROCESS', adoptProcesses);
		},

		// 입양보내기 상세
		fetchProtectorDetail: ({ commit, getters }, boardId) => {
			axios({
				url: api.adopt.protectorDetail(boardId),
				method: 'get',
				headers: getters.authHeader,
			})
				.then(res => {
					console.log(res.data);
					commit('SET_PROTECTOR_DETAIL', res.data);
				})
				.catch(err => {
					console.error(err.response);
				});
			// const newProtectorDetail = {
			// 	board_id: 1,
			// 	dog_img:
			// 		'https://upload.wikimedia.org/wikipedia/commons/c/c4/Emily_Maltese.jpg', // 제일 첫번째 사진
			// 	dog_name: '몽이',
			// 	application_list: [
			// 		{
			// 			user_id: 1,
			// 			profile: 'https://freesvg.org/img/abstract-user-flat-4.png',
			// 			name: '황희원',
			// 			application_status: 4,
			// 			chat_room_id: 1,
			// 			meeting_room: {
			// 				meeting_room_id: 1,
			// 				active: true,
			// 			},
			// 		},
			// 	],
			// };
			// commit('SET_PROTECTOR_DETAIL', newProtectorDetail);
		},
		// 입양 보내기 - 입양절차
		fetchProtectorAdoptProcess: (
			{ commit, getters },
			{ boardId, userId, applicationStatus },
		) => {
			commit('SET_APPLICATION_STATUS', applicationStatus);
			axios({
				url: api.adopt.protectorDetailProcess(boardId, userId),
				method: 'get',
				headers: getters.authHeader,
			})
				.then(res => {
					console.log(res.data);
					commit('SET_ADOPT_PROCESS', res.data.adopt_process);
					commit('SET_ADOPTION_PROCESS_ID', res.data.adoption_process_id);
				})
				.catch(err => {
					console.error(err.response);
				});

			// const adoptionProcessId = 1;
			// const newAdoptProcess = [
			// 	{
			// 		step: 1,
			// 		step_status: true,
			// 		date: '2022.07.10',
			// 	},
			// 	{
			// 		step: 2, // 1,2,3,4,5
			// 		step_status: true,
			// 		date: '2022.07.12 2PM',
			// 	},
			// 	{
			// 		step: 3, // 1,2,3,4,5
			// 		step_status: true,
			// 		date: '2022.07.12 3PM',
			// 	},
			// 	{
			// 		step: 4, // 1,2,3,4,5
			// 		step_status: true,
			// 		date: '2022.07.12 4PM',
			// 	},
			// 	{
			// 		step: 5, // 1,2,3,4,5
			// 		step_status: false,
			// 		date: '',
			// 	},
			// ];
			// commit('SET_ADOPT_PROCESS', newAdoptProcess);
			// commit('SET_ADOPTION_PROCESS_ID', adoptionProcessId);
			// commit('SET_APPLICATION_STATUS', applicationStatus);
		},
		updateActiveApplicant: ({ commit }, activeApplicant) => {
			commit('SET_ACTIVE_APPICANT', activeApplicant);
		},

		// 입양 상태 변경
		updateAdoptProcess: ({ commit, getters }, { step, adoptionProcessId }) => {
			axios({
				url: api.adopt.processUpdate(adoptionProcessId),
				method: 'put',
				data: { step, step_status: true },
				headers: getters.authHeader,
			})
				.then(res => {
					console.log(res.data);
					commit('SET_ADOPT_PROCESS', res.data.adopt_process);
					commit('UPDATE_APPLICATION_STATUS');
				})
				.catch(err => {
					console.error(err.response);
				});
			// step axios 전달
			console.log(step, adoptionProcessId);

			const newAdoptProcess = [
				{
					step: 1,
					step_status: true,
					date: '2022.07.10',
				},
				{
					step: 2, // 1,2,3,4,5
					step_status: true,
					date: '2022.07.12 2PM',
				},
				{
					step: 3, // 1,2,3,4,5
					step_status: true,
					date: '2022.07.12 2PM',
				},
				{
					step: 4, // 1,2,3,4,5
					step_status: true,
					date: '2022.07.12 2PM',
				},
				{
					step: 5, // 1,2,3,4,5
					step_status: true,
					date: '2022.07.12 2PM',
				},
			];
			commit('SET_ADOPT_PROCESS', newAdoptProcess);
			commit('UPDATE_APPLICATION_STATUS');
		},

		// chatList 받기
		fetchChatMain: ({ commit, getters }, chatRoomId) => {
			axios({
				url: api.adopt.chats(chatRoomId),
				method: 'get',
				data: { pageNo: 1, range: 15 },
				headers: getters.authHeader,
			})
				.then(res => {
					console.log(res.data);

					const you = {
						user_id: res.data.user_id,
						nickname: res.data.nickname,
						profile: res.data.profile,
					};
					commit('SET_CHAT_LIST', res.data.chat_list);
					commit('SET_YOU', you);
				})
				.catch(err => {
					console.error(err.response);
				});
			// const data = {
			// 	pageNo: 1,
			// 	range: 15,
			// 	user_id: 1, //상대방 id
			// 	nickname: '황희원', //상대방 닉네임
			// 	profile: '', //상대방 프로필
			// 	chat_list: [
			// 		{
			// 			user_id: 1,
			// 			time: '1시간 전',
			// 			message:
			// 				'안녕하세요! 몽이 입양신청서 작성하신거 보고 연락드렸어요!!',
			// 		},
			// 		{
			// 			user_id: 2,
			// 			time: '1시간 전',
			// 			message: '안녕하세요:)',
			// 		},
			// 		{
			// 			user_id: 2,
			// 			time: '30분 전',
			// 			message:
			// 				'면담일정은 어떻게 되나요? 제가 내일은 발표가 있어서 어렵습니다.:)',
			// 		},
			// 		{
			// 			user_id: 1,
			// 			time: '25분 전',
			// 			message: '화요일 오후 2시 어떠신가요? 그때가 좋을 것 같습니다^^',
			// 		},
			// 		{
			// 			user_id: 2,
			// 			time: '20분 전',
			// 			message: '네 그때 좋습니다^^',
			// 		},
			// 		{
			// 			user_id: 1,
			// 			time: '10분 전',
			// 			message: '네 그럼 일정 예약해두겠습니다!',
			// 		},
			// 	],
			// };
			// const you = {
			// 	user_id: data.user_id,
			// 	nickname: data.nickname,
			// 	profile: data.profile,
			// };
			// commit('SET_CHAT_LIST', data.chat_list);
			// commit('SET_YOU', you);
		},
		// 메세지 보내기
		sendChat: ({ commit, getters }, { chat, chatRoomId }) => {
			axios({
				url: api.adopt.chatCreate(chatRoomId, getters.user.userId),
				method: 'post',
				data: { message: chat.message },
				headers: getters.authHeader,
			})
				.then(res => {
					console.log(res.data);
					commit('UPDATE_NEW_CHAT', chat);
				})
				.catch(err => {
					console.error(err.response);
				});
			// chat 보내기 axios 구현
			// console.log(chat);
			// commit('UPDATE_NEW_CHAT', chat);
		},
	},
};
