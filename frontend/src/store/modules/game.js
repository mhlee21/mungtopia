import Qdata from '@/assets/Qdata.json';
import MbtiList from '@/assets/MbtiList.json';

export default {
	namespaced: true,
	state() {
		return {
			gameType: 0,
			gameTitle: '',
			gameDescription: '',
			gameQuestion: [],
			questionNumber: 0,
			correctAnswer: 0,
			mbtiResult: '',
			mbtiUserAnswer: 0,
			mbtiCount: 0,
			mbtiDog: [],
		};
	},
	getters: {
		gameType: state => state.gameType,
		gameTitle: state => state.gameTitle,
		gameDescription: state => state.gameDescription,
		gameQuestion: state => state.gameQuestion,
		questionNumber: state => state.questionNumber,
		correctAnswer: state => state.correctAnswer,
		mbtiResult: state => state.mbtiResult,
		mbtiUserAnswer: state => state.mbtiUserAnswer,
		mbtiCount: state => state.mbtiCount,
		mbtiDog: state => state.mbtiDog,
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
		SET_GAME_QUESTION: (state, gameQuestion) => {
			state.gameQuestion = gameQuestion;
		},
		SET_MBTI_DOG: (state, mbtiDog) => {
			state.mbtiDog = mbtiDog;
		},
		PLUS_QUESTION_NUMBER: state => {
			state.questionNumber += 1;
		},
		PLUS_CORRECT_ANSWER: state => {
			state.correctAnswer += 1;
		},

		PLUS_MBTI_USER_ANSWER: (state, mbtiUserAnswer) => {
			state.mbtiUserAnswer += mbtiUserAnswer;
		},

		ZERO_MBTI_USER_ANSWER: state => {
			state.mbtiUserAnswer = 0;
		},

		MBTI_COUNT: state => {
			state.mbtiCount += 1;
		},

		ZERO_MBTI_COUNT: state => {
			state.mbtiCount = 0;
		},

		PLUS_MBTI_RESULT: (state, mbtiResult) => {
			state.mbtiResult += mbtiResult;
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

		solveGame: ({ commit, getters }) => {
			const gameType = getters.gameType;
			const res = Qdata;
			if (gameType === 0) {
				const data = res.knowledge.map(d => ({
					question: d.question,
					answer: d.answer,
				}));
				commit('SET_GAME_QUESTION', data);
			} else if (gameType === 1) {
				const data = res.MBTI.map(d => ({
					question_type: d.question_type,
					question: d.question,
					answer: d.answer,
				}));
				commit('SET_GAME_QUESTION', data);
			} else {
				const data = res.MATCH.map(d => ({
					question_type: d.question_type,
					question: d.question,
					answer: d.answer,
				}));
				commit('SET_GAME_QUESTION', data);
			}
		},

		plusQuestionNumber: ({ commit }) => {
			commit('PLUS_QUESTION_NUMBER');
		},

		plusAnswerPoint: ({ commit }) => {
			commit('PLUS_CORRECT_ANSWER');
		},

		plusMbtiAnswer: ({ commit, getters }, { question_type, userAnswer }) => {
			const mbtiUserAnswer = getters.mbtiUserAnswer;
			const mbtiCount = getters.mbtiCount;
			if (mbtiCount < 2) {
				commit('PLUS_MBTI_USER_ANSWER', userAnswer);
				commit('MBTI_COUNT');
			} else {
				console.log(mbtiUserAnswer);
				console.log(mbtiCount);
				commit('ZERO_MBTI_COUNT');
				if (mbtiUserAnswer < 2) {
					if (question_type == 1) {
						commit('PLUS_MBTI_RESULT', 'I');
						commit('ZERO_MBTI_USER_ANSWER');
					} else if (question_type == 2) {
						commit('PLUS_MBTI_RESULT', 'S');
						commit('ZERO_MBTI_USER_ANSWER');
					} else if (question_type == 3) {
						commit('PLUS_MBTI_RESULT', 'T');
						commit('ZERO_MBTI_USER_ANSWER');
					} else {
						commit('PLUS_MBTI_RESULT', 'J');
						commit('ZERO_MBTI_USER_ANSWER');
					}
				} else {
					if (question_type == 1) {
						commit('PLUS_MBTI_RESULT', 'E');
						commit('ZERO_MBTI_USER_ANSWER');
					} else if (question_type == 2) {
						commit('PLUS_MBTI_RESULT', 'N');
						commit('ZERO_MBTI_USER_ANSWER');
					} else if (question_type == 3) {
						commit('PLUS_MBTI_RESULT', 'F');
						commit('ZERO_MBTI_USER_ANSWER');
					} else {
						commit('PLUS_MBTI_RESULT', 'P');
						commit('ZERO_MBTI_USER_ANSWER');
					}
				}
			}
		},

		MbtiDogResult: ({ commit, getters }) => {
			const mbtiResult = getters.mbtiResult;
			const res = MbtiList;
			const data = res[mbtiResult].map(d => ({
				DogName: d.DogName,
				DogImg: d.DogImg,
				hashtag: d.hashtag,
				fitWell: d.fitWell,
				fitWellDog: d.fitWellDog,
				fitWellImg: d.fitWellImg,
			}));
			commit('SET_MBTI_DOG', data);
		},
	},
};
