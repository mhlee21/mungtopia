import api from '@/api/api';
import axios from 'axios';

export default {
	namespaced: true,
	state() {
		return {
			user: null,
			// user:
			// {
			// 	userSeq: 6,
			// 	username: '황희원',
			// 	profile: 'https://www.snsboom.co.kr/common/img/default_profile.png',
			// },
			token: localStorage.getItem('token') || '',
			userInfo: null,
		};
	},
	getters: {
		user: state => state.user,
		token: state => state.token,
		authHeader: state =>
			state.token ? { Authorization: `Token ${state.token}` } : '',
		userInfo: state => state.userInfo,
	},
	mutations: {
		SET_TOKEN(state, token) {
			state.token = token;
		},
		SET_USER(state, user) {
			state.user = user;
		},
		SET_USER_INFO: (state, userInfo) => {
			state.userInfo = userInfo;
		},
	},
	actions: {
		saveToken({ commit }, token) {
			commit('SET_TOKEN', token);
			localStorage.setItem('token', token);
		},
		fetchUser({ commit, getters }) {
			axios({
				url: api.auth.getUser(),
				method: 'get',
				headers: { Authorization: `Bearer ${getters.token}` },
			})
				.then(res => {
					commit('SET_USER', res.body.data.user);
				})
				.catch(err => {
					console.error(err.response);
				});
		},
	},
};
