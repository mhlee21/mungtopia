import axios from 'axios';
import api from '@/api/api';
import router from '@/router';
import AdoptQ from '@/assets/AdoptQ.json';

export default {
	namespaced: true,
	state() {
		return {
			boardList: [],
			pageNo: 0,
			tagNo: 0,
			board: {},
			adoptStatus: null,
			comment: {},
			commentList: [],
			reply: {},
			modalType: null,
			isAdopting: null,
			likeInDetail: false,
			starInDetail: false,
			category: 1,
			applicationPageNum: 1,
			adoptQuestionList: [],
			application: Array(15).fill(''),
			keyword: '',
			isSendApplication: false,
		};
	},
	getters: {
		boardList: state => state.boardList,
		pageNo: state => state.pageNo,
		tagNo: state => state.tagNo,
		board: state => state.board,
		boardId: state => state.board.boardId,
		imageList: state => state.board.imageStorageList,
		adoptStatus: state => state.adoptStatus,
		comment: state => state.comment,
		commentList: state => state.commentList,
		reply: state => state.reply,
		modalType: state => state.modalType,
		isAdopting: state => state.isAdopting,
		likeInDetail: state => state.likeInDetail,
		starInDetail: state => state.starInDetail,
		category: state => state.category,
		applicationPageNum: state => state.applicationPageNum,
		adoptQuestionList: state => state.adoptQuestionList,
		application: state => state.application,
		keyword: state => state.keyword,
		isSendApplication: state => state.isSendApplication,
	},
	mutations: {
		SET_BOARD_LIST: (state, boardList) => (state.boardList = boardList),
		SET_PAGE_NO: (state, pageNo) => (state.pageNo = pageNo),
		SET_TAG_NO: (state, tagNo) => (state.tagNo = tagNo),
		SET_BOARD: (state, board) => (state.board = board),
		SET_ADOPT_STATUS: (state, adoptStatus) => (state.adoptStatus = adoptStatus),
		SET_COMMENT: (state, comment) => (state.comment = comment),
		SET_COMMENT_LIST: (state, commentList) => (state.commentList = commentList),
		SET_REPLY: (state, reply) => (state.reply = reply),
		SET_MODAL_TYPE: (state, modalType) => (state.modalType = modalType),
		SET_IS_ADOPTING: (state, isAdopting) => (state.isAdopting = isAdopting),
		SET_CATEGORY: (state, category) => (state.category = category),
		SET_APPLICATION_PAGE_NUM: (state, applicationPageNum) =>
			(state.applicationPageNum = applicationPageNum),
		SET_APPLICATION: (state, application) => (state.application = application),
		UPDATE_IS_LIKE: (state, index) => {
			if (index === -1) {
				state.board.isLike = !state.board.isLike;
			} else {
				state.boardList[index].isLike = !state.boardList[index].isLike;
			}
		},
		UPDATE_HAVE_INTEREST: (state, index) => {
			if (index === -1) {
				state.board.haveInterest = !state.board.haveInterest;
			} else {
				state.boardList[index].haveInterest =
					!state.boardList[index].haveInterest;
			}
		},
		SET_LIKE_IN_DETAIL: (state, isLike) => (state.likeInDetail = isLike),
		SET_STAR_IN_DETAIL: (state, haveStar) => (state.starInDetail = haveStar),
		SET_ADOPT_QUESTION_LIST: (state, adoptQuestionList) => {
			state.adoptQuestionList = adoptQuestionList;
		},
		SET_KEYWORD: (state, keyword) => {
			state.keyword = keyword;
		},
		SET_SEND_APPLICATION: (state, isSendApplication) =>
			(state.isSendApplication = isSendApplication),
	},
	actions: {
		// 전체글 불러오기
		fetchBoardList: ({ commit, rootGetters }, { tagNo, pageNo }) => {
			axios({
				url: api.board.boardMain({
					tagNo,
				}),
				method: 'get',
				headers: rootGetters['auth/authHeader'],
				params: {
					userSeq: rootGetters['auth/user']
						? rootGetters['auth/user'].userSeq
						: 0,
					pageNo: pageNo,
				},
			})
				.then(res => {
					commit('SET_PAGE_NO', pageNo + 1);
					commit('SET_BOARD_LIST', res.data.body.data);
				})
				.catch(err => {
					console.error(err.response);
				});
			commit('SET_PAGE_NO', pageNo + 1);
			commit('SET_TAG_NO', tagNo);
		},

		//게시물 검색
		searchBoard: ({ commit, rootGetters, getters }, { pageNo, keyword }) => {
			axios({
				url: api.board.boardSearch(getters['tagNo']),
				method: 'get',
				headers: rootGetters['auth/authHeader'],
				params: {
					pageNo,
					userSeq: rootGetters['auth/user']
						? rootGetters['auth/user'].userSeq
						: 0,
					keyword,
				},
			})
				.then(res => {
					commit('SET_BOARD_LIST', res.data.body.data);
					commit('SET_PAGE_NO', pageNo + 1);
				})
				.catch(err => {
					console.error(err.response);
				});
		},

		// 상세글 불러오기
		fetchDetailBoard: ({ commit, rootGetters }, boardId) => {
			//상세 게시글 정보 가져오기
			axios({
				url: api.board.boardDetail(boardId),
				method: 'get',
				headers: rootGetters['auth/authHeader'],
			})
				.then(res => {
					commit('SET_BOARD', res.data.body.data);
				})
				.catch(err => {
					console.error(err.response);
				});

			// 댓글 목록 가져오기
			axios({
				url: api.board.commentList(boardId),
				method: 'get',
				headers: rootGetters['auth/authHeader'],
			})
				.then(res => {
					commit('SET_COMMENT_LIST', res.data.body.data);
				})
				.catch(err => {
					console.error(err.response);
				});

			// 좋아요 여부 가져오기
			axios({
				url: api.board.isLike(
					boardId,
					rootGetters['auth/user'] ? rootGetters['auth/user'].userSeq : 0,
				),
				method: 'get',
				headers: rootGetters['auth/authHeader'],
			})
				.then(res => {
					commit('SET_LIKE_IN_DETAIL', res.data.body.data);
				})
				.catch(err => {
					console.error(err.response);
				});

			// 별표 여부 가져오기

			axios({
				url: api.board.haveStar(
					boardId,
					rootGetters['auth/user'] ? rootGetters['auth/user'].userSeq : 0,
				),
				method: 'get',
				headers: rootGetters['auth/authHeader'],
			})
				.then(res => {
					commit('SET_STAR_IN_DETAIL', res.data.body.data);
				})
				.catch(err => {
					console.error(err.response);
				});

			commit('SET_IS_ADOPTING', false);
		},

		// 입양 신청 여부 확인
		adoptStatusCheck: ({ commit, rootGetters }, { userSeq }) => {
			console.log('adoptStatusCheck', commit, rootGetters, userSeq);
			// axios({
			// 	url: api.board.adoptStatusCheck(userSeq),
			// 	method: 'get',
			// 	headers: rootGetters['auth/authHeader'],
			// })
			// 	.then(res => {
			// console.log(res.data.body.data);
			// commit('SET_ADOPT_STATUS', adoptStatus);
			// 	})
			// 	.catch(err => {
			// 		console.error(err.response);
			// 	});
			const adoptStatus = false;
			commit('SET_ADOPT_STATUS', adoptStatus);
		},

		// 글 쓰기
		createBoard: ({ rootGetters }, payload) => {
			axios({
				url: api.board.boardCreate(),
				method: 'post',
				headers: {
					...rootGetters['auth/authHeader'],
					'Content-Type': 'multipart/form-data',
				},
				data: payload,
			})
				.then(res => {
					router.push({
						name: 'boardDetail',
						params: { boardId: res.data.body.data.boardId },
					});
				})
				.catch(err => {
					console.error(err.response);
				});
		},

		// 글 수정
		updateBoard: ({ rootGetters }, { boardId, payload }) => {
			axios({
				url: api.board.boardUpdate(boardId),
				method: 'put',
				headers: rootGetters['auth/authHeader'],
				data: payload,
			}).catch(err => {
				console.error(err.response);
			});
		},

		// 글 삭제
		deleteBoard: ({ rootGetters }, boardId) => {
			axios({
				url: api.board.boardDelete(boardId),
				method: 'delete',
				headers: rootGetters['auth/authHeader'],
			}).catch(err => {
				console.error(err.response);
			});
		},

		// 댓글 정보 저장
		selectComment: ({ commit }, comment) => {
			commit('SET_COMMENT', comment);
		},

		// 대댓글 정보 저장
		selectReply: ({ commit }, reply) => {
			commit('SET_REPLY', reply);
		},

		// 모달 타입 저장
		selectModalType: ({ commit }, modalType) => {
			commit('SET_MODAL_TYPE', modalType);
		},

		// 댓글 쓰기
		createComment: ({ commit, getters, rootGetters }, payload) => {
			const boardId = getters['boardId'];
			axios({
				url: api.board.commentCreate(boardId),
				method: 'post',
				headers: rootGetters['auth/authHeader'],
				data: payload,
			})
				.then(res => {
					commit('SET_COMMENT_LIST', res.data.body.data);
				})
				.catch(err => {
					console.error(err.response);
				});
		},

		// 댓글 수정
		updateComment: (
			{ commit, rootGetters, getters },
			{ commentId, payload },
		) => {
			const boardId = getters['boardId'];
			axios({
				url: api.board.commentUpdate(boardId, commentId),
				method: 'put',
				headers: rootGetters['auth/authHeader'],
				data: payload,
			})
				.then(res => {
					commit('SET_COMMENT_LIST', res.data.body.data);
				})
				.catch(err => {
					console.error(err.response);
				});
		},

		// 댓글 삭제
		deleteComment: ({ commit, rootGetters, getters }, { commentId }) => {
			const boardId = getters['boardId'];
			axios({
				url: api.board.commentDelete(boardId, commentId),
				method: 'delete',
				headers: rootGetters['auth/authHeader'],
			})
				.then(res => {
					commit('SET_COMMENT_LIST', res.data.body.data);
				})
				.catch(err => {
					console.error(err.response);
				});
		},

		// 대댓글 쓰기
		createReply: ({ commit, rootGetters, getters }, { commentId, payload }) => {
			const boardId = getters['boardId'];
			axios({
				url: api.board.replyCreate(boardId, commentId),
				method: 'post',
				headers: rootGetters['auth/authHeader'],
				data: payload,
			})
				.then(res => {
					commit('SET_COMMENT_LIST', res.data.body.data);
				})
				.catch(err => {
					console.error(err.response);
				});
		},

		// 대댓글 수정
		updateReply: (
			{ commit, rootGetters, getters },
			{ commentId, replyId, payload },
		) => {
			const boardId = getters['boardId'];
			axios({
				url: api.board.replyUpdate(boardId, commentId, replyId),
				method: 'put',
				headers: rootGetters['auth/authHeader'],
				data: payload,
			})
				.then(res => {
					commit('SET_COMMENT_LIST', res.data.body.data);
				})
				.catch(err => {
					console.error(err.response);
				});
		},

		// 대댓글 삭제
		deleteReply: (
			{ commit, rootGetters, getters },
			{ commentId, replyId, payload },
		) => {
			const boardId = getters['boardId'];
			axios({
				url: api.board.replyDelete(boardId, commentId, replyId),
				method: 'delete',
				headers: rootGetters['auth/authHeader'],
				data: payload,
			})
				.then(res => {
					commit('SET_COMMENT_LIST', res.data.body.data);
				})
				.catch(err => {
					console.error(err.response);
				});
		},

		// 좋아요 여부 가져오기
		getLike: ({ commit, rootGetters }, { boardId }) => {
			axios({
				url: api.board.isLike(boardId, rootGetters['auth/user'].userSeq),
				method: 'get',
				headers: rootGetters['auth/authHeader'],
			})
				.then(res => {
					commit('SET_LIKE_IN_DETAIL', res.data.body.data);
				})
				.catch(err => {
					console.error(err.response);
				});
		},

		// 좋아요 하기
		createLike: ({ commit, rootGetters }, { boardId, index }) => {
			const payload = {
				userSeq: rootGetters['auth/user'].userSeq,
			};
			commit('UPDATE_IS_LIKE', index);

			axios({
				url: api.board.likeCreate(boardId, payload.userSeq),
				method: 'post',
				headers: rootGetters['auth/authHeader'],
				data: payload,
			})
				.then(res => {
					commit('SET_LIKE_IN_DETAIL', res.data.body.data); // 상세 페이지
					//
				})
				.catch(err => {
					console.error(err.response);
				});
		},

		// 좋아요 삭제
		deleteLike: ({ commit, rootGetters }, { boardId, index }) => {
			const payload = {
				userSeq: rootGetters['auth/user'].userSeq,
			};
			commit('UPDATE_IS_LIKE', index);

			axios({
				url: api.board.likeDelete(boardId, payload.userSeq),
				method: 'delete',
				headers: rootGetters['auth/authHeader'],
				data: payload,
			})
				.then(res => {
					commit('SET_LIKE_IN_DETAIL', res.data.body.data); // 상세 페이지
				})
				.catch(err => {
					console.error(err.response);
				});
		},

		// 별표 여부 가져오기
		getStar: ({ commit, rootGetters }, { boardId }) => {
			axios({
				url: api.board.haveStar(boardId, rootGetters['auth/user'].userSeq),
				method: 'get',
				headers: rootGetters['auth/authHeader'],
			})
				.then(res => {
					commit('SET_STAR_IN_DETAIL', res.data.body.data);
				})
				.catch(err => {
					console.error(err.response);
				});
		},

		// 별표 하기
		createStar: ({ commit, rootGetters }, { boardId, index }) => {
			const payload = {
				userSeq: rootGetters['auth/user'].userSeq,
			};
			commit('UPDATE_HAVE_INTEREST', index);
			axios({
				url: api.board.starCreate(boardId, payload.userSeq),
				method: 'post',
				headers: rootGetters['auth/authHeader'],
				data: rootGetters['auth/user'].userSeq,
			})
				.then(res => {
					commit('SET_STAR_IN_DETAIL', res.data.body.data); // 상세 페이지
				})
				.catch(err => {
					console.error(err.response);
				});
		},

		// 별표 삭제
		deleteStar: ({ commit, rootGetters }, { boardId, index }) => {
			const payload = {
				userSeq: rootGetters['auth/user'].userSeq,
			};
			commit('UPDATE_HAVE_INTEREST', index);
			axios({
				url: api.board.starDelete(boardId, payload.userSeq),
				method: 'delete',
				headers: rootGetters['auth/authHeader'],
				data: rootGetters['auth/user'].userSeq,
			})
				.then(res => {
					commit('SET_STAR_IN_DETAIL', res.data.body.data); // 상세 페이지
				})
				.catch(err => {
					console.error(err.response);
				});
		},
		// 입양신청서 저장
		saveApplication: ({ commit }, application) => {
			commit('SET_APPLICATION', application);
		},
		// 입양 신청서 작성
		createApplication: ({ rootGetters, getters }, boardId) => {
			const applicantAnswerList = getters['application'].map(
				(answer, index) => {
					return { answer, index };
				},
			);
			const payload = {
				userSeq: rootGetters['auth/user']['userSeq'],
				applicantAnswerList,
			};
			axios({
				url: api.board.applicationCreate(boardId),
				method: 'post',
				headers: rootGetters['auth/authHeader'],
				data: payload,
			}).catch(err => {
				console.error(err.response);
			});
		},

		// 카테고리 설정
		selectCategory: ({ commit }, category) => {
			commit('SET_CATEGORY', category);
		},

		// 신청서 페이지 설정
		setApplicationPageNum: ({ commit }, pageNum) => {
			commit('SET_APPLICATION_PAGE_NUM', pageNum);
		},

		setAdoptQ: ({ commit }) => {
			const res = AdoptQ;
			const data = res.adoptQuestion.map(d => ({
				idx: d.idx,
				question: d.question,
			}));
			commit('SET_ADOPT_QUESTION_LIST', data);
		},
		// commit
		getApplicationList: ({ commit, rootGetters }, { userSeq, boardId }) => {
			axios({
				url: api.board.applicationList(userSeq),
				method: 'get',
				headers: rootGetters['auth/authHeader'],
			})
				.then(res => {
					const sendlist = res.data.body.data;
					for (let i = 0; i < sendlist.length; i++) {
						if (sendlist[i] == boardId) {
							commit('SET_SEND_APPLICATION', true);
							break;
						}
					}
				})
				.catch(err => {
					console.error(err.response);
				});
		},
	},
};
