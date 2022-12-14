import api from '@/api/api';
import axios from 'axios';

export default {
	namespaced: true,
	state() {
		return {
			boardType: null,
			boardList: null,
			likeList: null,
			starList: null,
			userInfo: null,
		};
	},
	getters: {
		boardType: state => state.boardType,
		boardList: state => state.boardList,
		likeList: state => state.likeList,
		starList: state => state.starList,
		userInfo: state => state.userInfo,
	},
	mutations: {
		SET_BOARD_TYPE: (state, boardType) => (state.boardType = boardType),
		SET_BOARD_LIST: (state, boardList) => (state.boardList = boardList),
		SET_LIKE_LIST: (state, likeList) => (state.likeList = likeList),
		SET_STAR_LIST: (state, starList) => (state.starList = starList),
		SET_USER_INFO: (state, userInfo) => {
			state.userInfo = userInfo;
		},
	},
	actions: {
		setBoardType: ({ commit }, boardType) => {
			commit('SET_BOARD_TYPE', boardType);
		},
		fetchBoardList: ({ commit, dispatch, rootGetters }) => {
			axios({
				url: api.user.profileBoardList(rootGetters['auth/user']?.userSeq),
				method: 'get',
				headers: rootGetters['auth/authHeader'],
			})
				.then(res => {
					dispatch('setBoardType', 'board');
					commit('SET_BOARD_LIST', res.data.body.data.boardList);
					commit('SET_LIKE_LIST', res.data.body.data.likeList);
					commit('SET_STAR_LIST', res.data.body.data.starList);
				})
				.catch(err => {
					console.error(err.response);
				});
		},
		fetchUserInfo: ({ commit, rootGetters }) => {
			axios({
				url: api.user.profileDetail(rootGetters['auth/user']?.userSeq),
				method: 'get',
				headers: rootGetters['auth/authHeader'],
			})
				.then(res => {
					commit('SET_USER_INFO', res.data.body.data);
				})
				.catch(err => {
					console.error(err.response);
				});
		},
		updateUserInfo: ({ rootGetters }, payload) => {
			axios({
				url: api.user.profileDetailUpdate(rootGetters['auth/user'].userSeq),
				method: 'put',
				headers: rootGetters['auth/authHeader'],
				data: payload,
			}).catch(err => {
				console.error(err.response);
			});
		},
		updateUserNickname: ({ rootGetters }, payload) => {
			axios({
				url: api.user.profileNameUpdate(rootGetters['auth/user'].userSeq),
				method: 'put',
				headers: rootGetters['auth/authHeader'],
				data: payload,
			})
				.then(res => {
					this.state.userInfo.username = res.data.body.data;
				})
				.catch(err => {
					console.log(err.response);
				});
		},
		updateUserProfile: ({ rootGetters }, payload) => {
			axios({
				url: api.user.profileImageUpdate(rootGetters['auth/user'].userSeq),
				method: 'post',
				headers: {
					...rootGetters['auth/authHeader'],
					'Content-Type': 'multipart/form-data',
				},
				data: payload,
			})
				.then(res => {
					this.state.userInfo.profile = res.data.body.data;
				})
				.catch(err => {
					console.log(err);
				});
		},
		getUserInfo: ({ commit, rootGetters }) => {
			axios({
				url: api.user.profileGetUser(rootGetters['auth/user'].userSeq),
				method: 'get',
				headers: rootGetters['auth/authHeader'],
			})
				.then(res => {
					commit('SET_USER_INFO', res.data.body.data);
				})
				.catch(err => {
					console.log(err);
				});
		},
	},
};
