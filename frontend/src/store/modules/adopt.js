import api from '@/api/api';
import axios from 'axios';

export default {
	namespaced: true,
	state() {
		return {
			adoptType: 'applicant',
			mainList: null,
			applicantDetail: {},
			protectorDetail: {},
			adoptProcess: [],
			activeApplicant: null,
			adoptionProcessId: null,
			applicationStatus: null,
			you: {},
			chatList: [],
			date: '',
			chatRoomId: null,
			applicationAnswer: null,
			application: null,
		};
	},
	getters: {
		adoptType: state => state.adoptType,
		mainList: state => state.mainList,
		isApplicant: state => state.adoptType === 'applicant',
		applicantDetail: state => state.applicantDetail,
		protectorDetail: state => state.protectorDetail,
		adoptProcess: state => state.adoptProcess,
		activeApplicant: state => state.activeApplicant,
		adoptionProcessId: state => state.adoptionProcessId,
		applicationStatus: state => state.applicationStatus,
		chatList: state => state.chatList,
		you: state => state.you,
		date: state => state.date,
		chatRoomId: state => state.chatRoomId,
		applicationAnswer: state => state.applicationAnswer,
		application: state => state.application,
	},
	mutations: {
		SET_ADOPT_TYPE: (state, adoptType) => (state.adoptType = adoptType),
		SET_MAIN_LIST: (state, mainList) => (state.mainList = mainList),
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
		SET_APPLICATION: (state, application) => (state.application = application),
		SET_CHAT_LIST: (state, chatList) => state.chatList.unshift(...chatList),
		SET_YOU: (state, you) => (state.you = you),
		SET_DATE: (state, date) => (state.date = date),
		SET_CHAT_ROOM_ID: (state, chatRoomId) => (state.chatRoomId = chatRoomId),
		SET_OPENVIDU_TOKEN: (state, openviduToken) =>
			(state.openviduToken = openviduToken),
		UPDATE_APPLICATION_STATUS: state => {
			state.applicationStatus += 1;
		},
		UPDATE_NEW_CHAT: (state, chat) => {
			state.chatList.push(chat);
		},
		SET_APPLICATION_ANSWER: (state, applicationAnswer) => {
			state.applicationAnswer = applicationAnswer;
		},
		DELETE_APPLICANT: (state, index) => {
			state.protectorDetail.splice(index, 1);
		},
	},
	actions: {
		// ????????????/??????????????? ??????
		fetchMainList: ({ commit, rootGetters }, { userSeq, adoptType }) => {
			if (adoptType === 'applicant') {
				axios({
					url: api.adopt.applicantMain(userSeq),
					method: 'get',
					headers: rootGetters['auth/authHeader'],
				})
					.then(res => {
						commit('SET_ADOPT_TYPE', 'applicant');
						commit('SET_MAIN_LIST', res.data.body.data);
					})
					.catch(err => {
						console.error(err.response);
					});
			} else {
				axios({
					url: api.adopt.protectorMain(userSeq),
					method: 'get',
					headers: rootGetters['auth/authHeader'],
				})
					.then(res => {
						commit('SET_ADOPT_TYPE', 'protector');
						commit('SET_MAIN_LIST', res.data.body.data);
					})
					.catch(err => {
						console.error(err.response);
					});
			}
		},
		// ???????????? ??????
		fetchApplicantDetail: ({ commit, rootGetters }, applicationId) => {
			axios({
				url: api.adopt.applicantDetail(applicationId),
				method: 'get',
				headers: rootGetters['auth/authHeader'],
			})
				.then(res => {
					commit('SET_APPLICANT_DETAIL', res.data.body.data);
					commit(
						'SET_APPLICATION_STATUS',
						res.data.body.data.applicationStatus,
					);
				})
				.catch(err => {
					console.error(err.response);
				});
		},
		// ???????????? - ????????????
		fetchApplicantAdoptProcess: ({ commit, rootGetters }, applicationId) => {
			axios({
				url: api.adopt.applicantDetailProcess(applicationId),
				method: 'get',
				headers: rootGetters['auth/authHeader'],
			})
				.then(res => {
					commit('SET_ADOPT_PROCESS', res.data.body.data.applicantProcessRes);
					commit(
						'SET_ADOPTION_PROCESS_ID',
						res.data.body.data.adoptionProcessId,
					);
				})
				.catch(err => {
					console.error(err.response);
				});
		},
		// ??????????????? ??????
		fetchProtectorDetail: ({ commit, rootGetters }, boardId) => {
			axios({
				url: api.adopt.protectorDetail(boardId),
				method: 'get',
				headers: rootGetters['auth/authHeader'],
			})
				.then(res => {
					commit('SET_PROTECTOR_DETAIL', res.data.body.data);
				})
				.catch(err => {
					console.error(err.response);
				});
		},
		// ?????? ????????? - ????????????
		fetchProtectorAdoptProcess: (
			{ commit, rootGetters },
			{ adoptionProcessId, applicationStatus },
		) => {
			commit('SET_APPLICATION_STATUS', applicationStatus);
			commit('SET_ADOPTION_PROCESS_ID', adoptionProcessId);
			axios({
				url: api.adopt.protectorDetailProcess(adoptionProcessId),
				method: 'get',
				headers: rootGetters['auth/authHeader'],
			})
				.then(res => {
					commit('SET_ADOPT_PROCESS', res.data.body.data);
				})
				.catch(err => {
					console.error(err.response);
				});
		},
		updateActiveApplicant: ({ commit }, activeApplicant) => {
			commit('SET_ACTIVE_APPICANT', activeApplicant);
		},
		// ?????? ??????
		protectorCancel: ({ commit, rootGetters, getters }, index) => {
			const adoptionProcessId =
				getters['protectorDetail'][index]['adoptionProcessId'];
			axios({
				url: api.adopt.processUpdate(adoptionProcessId),
				method: 'delete',
				headers: rootGetters['auth/authHeader'],
			})
				.then(() => {
					commit('DELETE_APPLICANT', index);
				})
				.catch(err => {
					console.error(err.response);
				});
		},
		// ?????? ??????
		applicantCancel: ({ dispatch, rootGetters, getters }) => {
			axios({
				url: api.adopt.applicantCancel(getters.adoptionProcessId),
				method: 'delete',
				headers: rootGetters['auth/authHeader'],
			})
				.then(() => {
					dispatch('fetchMainList', {
						userSeq: rootGetters['auth/user'].userSeq,
						adoptType: 'applicant',
					});
				})
				.catch(err => {
					console.error(err.response);
				});
		},
		// ?????? ?????? ??????
		deleteAdoption: (
			{ commit, rootGetters, getters },
			{ isApplicant, index },
		) => {
			axios({
				url: api.adopt.deleteAdoption(getters.adoptionProcessId),
				method: 'delete',
				headers: rootGetters['auth/authHeader'],
			})
				.then(() => {
					if (!isApplicant) {
						commit('DELETE_APPLICANT', index);
					}
				})
				.catch(err => {
					console.error(err.response);
				});
		},
		// ?????? ?????? ??????
		updateAdoptProcess: ({ commit, rootGetters, getters }, step) => {
			axios({
				url: api.adopt.processUpdate(getters['adoptionProcessId']),
				method: 'put',
				data: step,
				headers: rootGetters['auth/authHeader'],
			})
				.then(res => {
					commit('UPDATE_APPLICATION_STATUS');
					commit('SET_ADOPT_PROCESS', res.data.body.data);
				})
				.catch(err => {
					console.error(err.response);
				});
		},
		// ?????? ??????
		saveDate: (
			{ commit, rootGetters },
			{ adoptionProcessId, applicationStatus },
		) => {
			console.log(rootGetters, applicationStatus);
			// axios({
			// 	url: api.adopt.schedules(adoptionProcessId),
			// 	method: 'get',
			// 	data: { pageNo: 1, range: 15 },
			// 	headers: rootGetters['auth/authHeader'],
			// })
			// 	.then(res => {
			// 		console.log(res.data.body.data);
			// 		commit('SET_DATE', res.data.body.data.date);
			// 		commit('SET_APPLICATION_STATUS', res.data.body.data.step);
			// 	})
			// 	.catch(err => {
			// 		console.error(err.response);
			// 	});
			commit('SET_ADOPTION_PROCESS_ID', adoptionProcessId);
			commit('SET_APPLICATION_STATUS', 4);
			commit('SET_DATE', '08/17/2022, 14:56');
		},
		// chatList ??????
		fetchChatMain: ({ commit, rootGetters }, { chatRoomId, page }) => {
			if (page === 0) {
				commit('SET_CHAT_LIST', []);
			}
			axios({
				url: api.adopt.chats(),
				method: 'get',
				params: {
					page: page,
					chatRoomId: chatRoomId,
					userSeq: rootGetters['auth/user'].userSeq,
				},
				headers: rootGetters['auth/authHeader'],
			})
				.then(res => {
					commit('SET_CHAT_LIST', res.data.body.data.chatLogDtoList);
					commit('SET_CHAT_ROOM_ID', chatRoomId);

					const you = {
						userSeq: res.data.body.data.userSeq,
						nickname: res.data.body.data.nickname,
						profile: res.data.body.data.profile,
					};
					commit('SET_YOU', you);
				})
				.catch(err => {
					console.error(err.response);
				});
		},

		// ????????? ?????? ????????????
		getChatRoomInfo: ({ commit, rootGetters }, chatRoomId) => {
			axios({
				url: api.adopt.chatInfo(chatRoomId),
				method: 'get',
				headers: rootGetters['auth/authHeader'],
			})
				.then(res => {
					const chatRoomInfo = res.data.body.data;
					const userSeq = rootGetters['auth/user'].userSeq;
					if (userSeq === chatRoomInfo.protectorId) {
						commit('SET_ADOPT_TYPE', 'protector');
					} else {
						commit('SET_ADOPT_TYPE', 'applicant');
					}
				})
				.catch(err => {
					console.error(err.response);
				});
		},

		// ?????? ?????? ??? ??????
		saveSchedule: ({ commit, rootGetters, getters }, date) => {
			// ????????? ?????? ???
			if (getters.date === '') {
				axios({
					url: api.adopt.scheduleCreate(getters.adoptionProcessId),
					method: 'post',
					data: { date },
					headers: rootGetters['auth/authHeader'],
				}).catch(err => {
					console.error(err.response);
				});
			} else {
				// ????????? ?????? ???(?????? ??????)
				axios({
					url: api.adopt.scheduleUpdate(getters.adoptionProcessId),
					method: 'put',
					data: { date },
					headers: rootGetters['auth/authHeader'],
				}).catch(err => {
					console.error(err.response);
				});
			}
			commit('SET_DATE', date);
		},
		// ?????? ??????
		deleteSchedule: ({ commit, rootGetters, getters }) => {
			const payload = { step: getters.applicationStatus };
			console.log(rootGetters, payload);
			// axios({
			// 	url: api.adopt.scheduleDelete(getters.adoptionProcessId),
			// 	method: 'delete',
			// 	data: { payload },
			// 	headers: rootGetters['auth/authHeader'],
			// }).catch(err => {
			// 	console.error(err.response);
			// });
			commit('SET_DATE', '');
		},
		// ????????? ?????? ??????
		getToken: ({ commit }, sessionName) => {
			axios({
				url: 'http://localhost:5001/api-sessions/get-token',
				method: 'post',
				data: { sessionName },
				// headers: rootGetters['auth/authHeader'],
			})
				.then(res => {
					const openviduToken = res[0]; // Get token from response
					console.warn(
						'Request of TOKEN gone WELL (TOKEN:' + openviduToken + ')',
					);
					commit('SET_OPENVIDU_TOKEN', openviduToken);
					// callback(token); // Continue the join operation
				})
				.catch(err => {
					console.error(err.response);
				});
		},
		// ??????????????? ?????? ??????
		fetchApplication: ({ commit, rootGetters }, applicationId) => {
			axios({
				url: api.adopt.application(applicationId),
				method: 'get',
				headers: rootGetters['auth/authHeader'],
			})
				.then(res => {
					commit('SET_APPLICATION', res.data.body.data);
				})
				.catch(err => {
					console.error(err.response);
				});
		},
	},
};
