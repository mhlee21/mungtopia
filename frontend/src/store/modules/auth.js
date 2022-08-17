import api from '@/api/api';
import axios from 'axios';

export default {
	namespaced: true,
	state() {
		return {
			// user: null,
			user: {
				userSeq: 1,
				username: '이미현',
				userNickname: 'LMH',
				profile: 'https://www.snsboom.co.kr/common/img/default_profile.png',
			},
			token: localStorage.getItem('token') || '',
			userInfo: null,
		};
	},
	getters: {
		user: state => state.user,
		token: state => state.token,
		authHeader: state =>
			state.token ? { Authorization: `Bearer ${state.token}` } : '',
	},
	mutations: {
		SET_TOKEN: (state, token) => {
			state.token = token;
		},
		DELETE_TOKEN: state => {
			state.token = '';
		},
		SET_USER: (state, user) => {
			state.user = user;
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
				headers: getters['authHeader'],
			})
				.then(res => {
					console.log(res.data.body.user);
					commit('SET_USER', res.data.body.user);
				})
				.catch(err => {
					console.error(err.response);
				});
		},
		logout({ commit }) {
			commit('DELETE_TOKEN');
			localStorage.setItem('token', '');
		},
	},
};
