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
		};
	},
	getters: {
		boardType: state => state.boardType,
		boardList: state => state.boardList,
		likeList: state => state.likeList,
		starList: state => state.starList,
	},
	mutations: {
		SET_BOARD_TYPE: (state, boardType) => (state.boardType = boardType),
		SET_BOARD_LIST: (state, boardList) => (state.boardList = boardList),
		SET_LIKE_LIST: (state, likeList) => (state.likeList = likeList),
		SET_STAR_LIST: (state, starList) => (state.starList = starList),
	},
	actions: {
		setBoardType({ commit }, boardType) {
			commit('SET_BOARD_TYPE', boardType);
		},
		fetchBoardList({ commit, dispatch, rootGetters }) {
			console.log('fetchBoardList');
			axios({
				url: api.user.profileBoardList(rootGetters['auth/user']?.userSeq),
				method: 'get',
				headers: rootGetters['auth/authHeader'],
			})
				.then(res => {
					// console.log(res.data.body.data);
					dispatch('setBoardType', 'board');
					commit('SET_BOARD_LIST', res.data.body.data.boardList);
					commit('SET_LIKE_LIST', res.data.body.data.likeList);
					commit('SET_STAR_LIST', res.data.body.data.starList);
				})
				.catch(err => {
					console.error(err.response);
				});
		},
	},
};
