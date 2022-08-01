export default {
	namespaced: true,
	state() {
		return {
			adoptType: 'applicant',
			applicationList: [
				{
					application_id: 1,
					dog_img:
						'https://upload.wikimedia.org/wikipedia/commons/c/c4/Emily_Maltese.jpg', // 제일 첫번째 사진
					dog_name: '몽이',
					application_status: 1,
				},
				{
					application_id: 2,
					dog_img:
						'https://upload.wikimedia.org/wikipedia/commons/c/c4/Emily_Maltese.jpg', // 제일 첫번째 사진
					dog_name: '몽이',
					application_status: 6,
				},
				// {
				// 	id: 2,
				// 	name: '몽이',
				// 	imageUrl:
				// 		'https://upload.wikimedia.org/wikipedia/commons/c/c4/Emily_Maltese.jpg',
				// },
				// {
				// 	id: 3,
				// 	name: '몽이',
				// 	imageUrl:
				// 		'https://upload.wikimedia.org/wikipedia/commons/c/c4/Emily_Maltese.jpg',
				// },
			],
		};
	},
	getters: {
		adoptType: state => state.adoptType,
		applicationList: state => state.applicationList,
		isApplicant: state => state.adoptType === 'applicant',
	},
	mutations: {
		SET_ADOPT_TYPE: (state, adoptType) => (state.adoptType = adoptType),
		SET_APPLICATION_LIST: (state, applicationList) =>
			(state.applicationList = applicationList),
		// SET_BOARD: (state, board) => {
		// 	state.board = board;
		// },
	},
	actions: {
		change_adopt_type: ({ commit }, adoptType) => {
			const newApplicationList = [
				{
					application_id: 1,
					dog_img:
						'https://upload.wikimedia.org/wikipedia/commons/c/c4/Emily_Maltese.jpg', // 제일 첫번째 사진
					dog_name: '보호자',
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
			commit('SET_ADOPT_TYPE', adoptType);
			commit('SET_APPLICATION_LIST', newApplicationList);
			//  백엔드에 요청
		},
		// set_board: ({ commit }, newBoard) => {
		// 	commit('SET_BOARD', newBoard);
		// },
	},
};
