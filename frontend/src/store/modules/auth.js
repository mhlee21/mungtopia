import api from '@/api/api';
import axios from 'axios';

export default {
	namespaced: true,
	state() {
		return {
			user: null,
			token: localStorage.getItem('token') || '',
		};
	},
	getters: {
		user: state => state.user,
		token: state => state.token,
		authHeader: state =>
			state.token ? { Authorization: `Token ${state.token}` } : '',
	},
	mutations: {
		setToken(state, token) {
			state.token = token;
		},
		setUser(state, user) {
			state.user = user;
		},
	},
	actions: {
		saveToken({ commit }, token) {
			commit('setToken', token);
			localStorage.setItem('token', token);
		},
		fetchUser({ commit, getters }) {
			axios({
				url: api.auth.getUser(),
				method: 'get',
				headers: { Authorization: `Bearer ${getters.token}` },
			})
				.then(res => {
					commit('setUser', res.body.data.user);
				})
				.catch(err => {
					console.error(err.response);
				});
		},
	},
};
