import api from '@/api/api';
import axios from 'axios';

export default {
	namespaced: true,
	state() {
		return {
			user: null,
			token: null,
		};
	},
	getters: {
		user: state => state.user,
		token: state => state.token,
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
		fetchUser({ commit, getters }) {
			axios({
				url: api.auth.getUser(),
				method: 'get',
				headers: { Authorization: `Bearer ${getters.token}` },
			})
				.then(res => {
					commit('setUser', res.data.body.user);
				})
				.catch(err => {
					console.error(err.response);
				});
		},
	},
};
