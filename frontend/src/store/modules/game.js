import MbtiList from '@/assets/MbtiList.json';
import axios from 'axios';
import api from '@/api/api';

export default {
	namespaced: true,
	state() {
		return {
			mbtiResult: '',
			mbtiDog: {},
			matchAnswer: {},
			matchData: [],
			isClear: [false, false, false],
		};
	},
	getters: {
		mbtiResult: state => state.mbtiResult,
		mbtiDog: state => state.mbtiDog,
		matchAnswer: state => state.matchAnswer,
		matchData: state => state.matchData,
		isClear: state => state.isClear,
	},
	mutations: {
		SET_MBTI_DOG: (state, mbtiDog) => {
			state.mbtiDog = mbtiDog;
		},
		PLUS_MATCH_ANSWER: (state, data) => {
			state.matchAnswer += data;
		},
		SET_MATCH_DATA: (state, matchData) => {
			state.matchData = matchData;
		},
		CLEAR_GAME: (state, { gameTag, result }) => {
			state.isClear[gameTag] = result;
		},
	},
	actions: {
		MbtiDogResult: ({ commit }, mbtiResult) => {
			const res = MbtiList;
			const data = res[mbtiResult];
			commit('SET_MBTI_DOG', data);
		},

		matchResult: ({ commit }) => {
			for (var i = 0; i < 6; i++) {
				commit('PLUS_MATCH_ANSWER', i);
			}
		},

		sendResult: ({ rootGetters }, payload) => {
			axios({
				url: api.game.saveGame(),
				method: 'post',
				headers: rootGetters['auth/authHeader'],
				data: payload,
			}).catch(err => {
				console.error(err.response);
			});
		},

		sendMatchResult: ({ commit, rootGetters }, payload) => {
			axios({
				url: api.game.receiveGame(),
				method: 'post',
				headers: rootGetters['auth/authHeader'],
				data: payload,
			})
				.then(res => {
					commit('SET_MATCH_DATA', res.data.body.data);
				})
				.catch(err => {
					console.error(err.response);
				});
		},

		receiveClear: ({ commit, rootGetters }) => {
			axios({
				url: api.game.clearGame(rootGetters['auth/user'].userSeq),
				method: 'get',
				headers: rootGetters['auth/authHeader'],
			})
				.then(res => {
					commit('SET_MATCH_DATA', res.body.data);
					if (res.data.body.data[0]) {
						commit('CLEAR_GAME', {
							gameTag: res.data.body.data[0].gameTag,
							result: res.data.body.data[0].result,
						});
					}
					if (res.data.body.data[1]) {
						commit('CLEAR_GAME', {
							gameTag: res.data.body.data[1].gameTag,
							result: res.data.body.data[1].result,
						});
					}
					if (res.data.body.data[2]) {
						commit('CLEAR_GAME', {
							gameTag: res.data.body.data[2].gameTag,
							result: res.data.body.data[2].result,
						});
					}
				})
				.catch(err => {
					console.error(err.response);
				});
		},
	},
};
