export default {
	namespaced: true,
	state() {
		return {
			gameType: 0,
			gameTitle: '',
			gameDescription: '',
		};
	},
	getters: {
		gameType: state => state.gameType,
		gameTitle: state => state.gameTitle,
		gameDescription: state => state.gameDescription,
	},
	mutations: {
		SET_GAME_TYPE: (state, gameType) => {
			state.gameType = gameType;
		},
		SET_GAME_TITLE: (state, gameTitle) => {
			state.gameTitle = gameTitle;
		},
		SET_GAME_DESCRIPTION: (state, gameDescription) => {
			state.gameDescription = gameDescription;
		},
	},
	actions: {
		enterGame: ({ commit }, gameType) => {
			commit('SET_GAME_TYPE', gameType);
			if (gameType === 0) {
				commit('SET_GAME_TITLE', '강아지식테스트');
				commit(
					'SET_GAME_DESCRIPTION',
					'10문제 중 7문제 이상 맞춰\n 당신의 반려견에게\n 뼈다귀를 선물하세요!',
				);
			} else if (gameType === 1) {
				commit('SET_GAME_TITLE', '댕BTI');
				commit(
					'SET_GAME_DESCRIPTION',
					'댕BTI 검사를 통해\n 당신과 비슷한 강아지를\n 찾아보세요!',
				);
			} else {
				commit('SET_GAME_TITLE', '나와 잘맞는 반려견은?');
				commit(
					'SET_GAME_DESCRIPTION',
					'간단한 설문을 통해\n당신과 밀접한 키워드를 알아보고\n반려견 추천을 받아보세요!',
				);
			}
		},
	},
};
