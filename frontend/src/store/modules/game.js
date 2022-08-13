import Qdata from '@/assets/Qdata.json';
import MbtiList from '@/assets/MbtiList.json';
import axios from 'axios';
import api from '@/api/api';

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
			mbtiDog: {},
			matchUserPoint: 0,
			matchNum: [0, 0, 0, 0, 0, 0],
			matchCount: 0,
			matchAnswer: {},
			matchData: [],
			statusWidth: 0,
			isClear: [false, false, false],
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
		mbtiDog: state => state.mbtiDog,
		mbtiUserAnswer: state => state.mbtiUserAnswer,
		mbtiCount: state => state.mbtiCount,
		matchUserPoint: state => state.matchUserPoint,
		matchNum: state => state.matchNum,
		matchCount: state => state.matchCount,
		matchAnswer: state => state.matchAnswer,
		matchData: state => state.matchData,
		statusWidth: state => state.statusWidth,
		isClear: state => state.isClear,
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
		PLUS_MATCH_USER_POINT: (state, userAnswer) => {
			state.matchUserPoint += userAnswer;
			state.matchCount += 1;
		},
		ZERO_MATCH_USER_POINT: (state, { question_type, userAnswer }) => {
			state.matchUserPoint += userAnswer;
			state.matchNum[question_type] = state.matchUserPoint;
			state.matchUserPoint = 0;
			state.matchCount = 0;
		},
		PLUS_MATCH_FOUR: (state, matchFour) => {
			state.matchFour = matchFour;
		},
		PLUS_MATCH_HASH: (state, matchHash) => {
			state.matchHash += matchHash;
		},
		PLUS_MATCH_ANSWER: (state, data) => {
			state.matchAnswer += data;
		},
		SET_MATCH_DATA: (state, matchData) => {
			state.matchData = matchData;
		},
		PLUS_STATUS_WIDTH: (state, statusWidth) => {
			state.statusWidth = statusWidth;
		},
		CLEAR_GAME: (state, { gameTag, result }) => {
			state.isClear[gameTag] = result;
		},
	},
	actions: {
		enterGame: ({ commit }, gameType) => {
			commit('SET_GAME_TYPE', gameType);
			if (gameType == 0) {
				commit('SET_GAME_TITLE', '강아지식테스트');
				commit(
					'SET_GAME_DESCRIPTION',
					'10문제 중 7문제 이상 맞춰\n 당신의 반려견 지식을\n 뽐내보세요!',
				);
			} else if (gameType == 1) {
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

		solveGame: ({ commit }, gameType) => {
			const res = Qdata;
			if (gameType == 0) {
				const data = res.knowledge.map(d => ({
					question: d.question,
					img: d.img,
					answer: d.answer,
				}));
				commit('SET_GAME_QUESTION', data);
			} else if (gameType == 1) {
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
			if (mbtiCount <= 2) {
				commit('PLUS_MBTI_USER_ANSWER', userAnswer);
				commit('MBTI_COUNT');
			} else {
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

		MbtiDogResult: ({ commit }, mbtiResult) => {
			const res = MbtiList;
			const data = res[mbtiResult];
			// (d => ({
			// 	DogName: d.DogName,
			// 	DogImg: d.DogImg,
			// 	hashtag: d.hashtag,
			// 	fitWell: d.fitWell,
			// 	fitWellDog: d.fitWellDog,
			// 	fitWellImg: d.fitWellImg,
			// }));
			commit('SET_MBTI_DOG', data);
		},

		plusMatchAnswer: ({ commit, getters }, { question_type, userAnswer }) => {
			const matchNum = getters.matchNum;
			const matchCount = getters.matchCount;
			if (matchCount < 2) {
				commit('PLUS_MATCH_USER_POINT', userAnswer);
			} else {
				commit('ZERO_MATCH_USER_POINT', { question_type, userAnswer });
			}
			console.log(matchNum);
		},

		matchResult: ({ commit }) => {
			// const matchNum = getters.matchCount;
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
				console.log(11);
				console.error(err.response);
			});
			// .then(res => {
			// 	console.log(res.body.data);

			// })
		},

		sendMatchResult: ({ rootGetters }, payload) => {
			axios({
				url: api.game.saveGame(),
				method: 'post',
				headers: rootGetters['auth/authHeader'],
				data: payload,
			}).catch(err => {
				console.error(err.response);
			});
			// .then(res => {
			// 	console.log(res.body.data);

			// })
		},

		receiveMatchResult: ({ commit, rootGetters }) => {
			axios({
				url: api.game.receiveGame(),
				method: 'get',
				headers: rootGetters['auth/authHeader'],
			})
				.then(res => {
					commit('SET_MATCH_DATA', res.body.data);
				})
				.catch(err => {
					console.error(err.response);
				});
		},

		updateProgressbar: ({ commit, getters }) => {
			const questionNumber = getters.questionNumber;
			const gameType = getters.gameType;
			if (gameType == 0) {
				let width = (questionNumber / 9) * 100 + '%';
				width = parseFloat(width).toFixed(2);
				if (width > 100) width = 100;
				commit('PLUS_STATUS_WIDTH', width);
			} else if (gameType == 1) {
				let width = (questionNumber / 11) * 100 + '%';
				width = parseFloat(width).toFixed(2);
				if (width > 100) width = 100;
				commit('PLUS_STATUS_WIDTH', width);
			} else {
				let width = (questionNumber / 17) * 100 + '%';
				width = parseFloat(width).toFixed(2);
				if (width > 100) width = 100;
				commit('PLUS_STATUS_WIDTH', width);
			}
		},

		receiveClear: ({ commit, rootGetters }) => {
			console.log(rootGetters['auth/user'].userSeq);
			axios({
				url: api.game.clearGame(rootGetters['auth/user'].userSeq),
				method: 'get',
				headers: rootGetters['auth/authHeader'],
			})
				.then(res => {
					console.log(res.data.body);
					// commit('SET_MATCH_DATA', res.body.data);
					commit('CLEAR_GAME', {
						gameTag: res.data.body.data.gameTag,
						result: res.data.body.data.result,
					});
				})
				.catch(err => {
					console.log(111);
					console.error(err.response);
				});
		},
	},
};
