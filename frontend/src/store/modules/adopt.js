export default {
	namespaced: true,
	state() {
		return {
			adoptType: '',
			applicantMainList: [],
			protectorMainList: [],
		};
	},
	getters: {
		adoptType: state => state.adoptType,
		applicantMainList: state => state.applicantMainList,
		protectorMainList: state => state.protectorMainList,
		isApplicant: state => state.adoptType === 'applicant',
	},
	mutations: {
		SET_ADOPT_TYPE: (state, adoptType) => (state.adoptType = adoptType),
		SET_APPLICANT_MAIN_LIST: (state, applicantMainList) =>
			(state.applicantMainList = applicantMainList),
		SET_PROTECTOR_MAIN_LIST: (state, protectorMainList) =>
			(state.protectorMainList = protectorMainList),
		// SET_BOARD: (state, board) => {
		// 	state.board = board;
		// },
	},
	actions: {
		fetch_applicant_main_list: ({ commit }) => {
			const newApplicationList = [
				{
					application_id: 1,
					dog_img:
						'https://upload.wikimedia.org/wikipedia/commons/c/c4/Emily_Maltese.jpg', // 제일 첫번째 사진
					dog_name: '처음',
					application_status: 1,
				},
				{
					application_id: 2,
					dog_img:
						'https://upload.wikimedia.org/wikipedia/commons/c/c4/Emily_Maltese.jpg', // 제일 첫번째 사진
					dog_name: '예시',
					application_status: 6,
				},
			];
			commit('SET_ADOPT_TYPE', 'applicant');
			commit('SET_APPLICANT_MAIN_LIST', newApplicationList);
		},
		change_adopt_type: ({ commit }, adoptType) => {
			if (adoptType === 'applicant') {
				const newApplicationList = [
					{
						application_id: 1,
						dog_img:
							'https://upload.wikimedia.org/wikipedia/commons/c/c4/Emily_Maltese.jpg', // 제일 첫번째 사진
						dog_name: '버튼누르기',
						application_status: 1,
					},
					{
						application_id: 2,
						dog_img:
							'https://upload.wikimedia.org/wikipedia/commons/c/c4/Emily_Maltese.jpg', // 제일 첫번째 사진
						dog_name: '예시',
						application_status: 6,
					},
				];
				commit('SET_ADOPT_TYPE', 'applicant');
				commit('SET_APPLICANT_MAIN_LIST', newApplicationList);
			} else {
				const newProtectorMainList = [
					{
						board_id: 1,
						dog_img:
							'https://upload.wikimedia.org/wikipedia/commons/c/c4/Emily_Maltese.jpg', // 제일 첫번째 사진
						dog_name: '보호자 예시',
						adoption_status: 0, // 0, 1, 2
					},
					{
						board_id: 2,
						dog_img:
							'https://upload.wikimedia.org/wikipedia/commons/c/c4/Emily_Maltese.jpg', // 제일 첫번째 사진
						dog_name: '몽이',
						adoption_status: 1, // 0, 1, 2
					},
				];
				commit('SET_ADOPT_TYPE', 'protector');
				commit('SET_PROTECTOR_MAIN_LIST', newProtectorMainList);
			}

			//  백엔드에 요청
		},
		// set_board: ({ commit }, newBoard) => {
		// 	commit('SET_BOARD', newBoard);
		// },
	},
};
