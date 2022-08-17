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
	},
	actions: {
		// 전체글 불러오기
		fetchBoardList: ({ commit, rootGetters }, { tagNo, pageNo }) => {
			console.log('fetchBoardList', tagNo, pageNo, rootGetters);
			axios({
				url: api.board.boardMain({
					tagNo,
				}),
				method: 'get',
				headers: rootGetters['auth/authHeader'],
				params: {
					userSeq: rootGetters['auth/user'].userSeq,
					pageNo: pageNo,
				},
			})
				.then(res => {
					console.log(res.data.body.data.imageStorageList);
					commit('SET_PAGE_NO', pageNo + 1);
					commit('SET_BOARD_LIST', res.data.body.data);
				})
				.catch(err => {
					console.error(err.response);
				});
			// const data = {
			// 	boardList: [
			// 		{
			// 			boardTag: 1,
			// 			boardId: 1,
			// 			dogName: '몽이',
			// 			contents:
			// 				' 오늘 몽이랑 같이 산책 다녀왔어요! 몽이랑 같이 벚꽃 구경해서 너무 행복합니닿ㅎ 다들 갱얼쥐랑 꽃구경 다녀오세요^^ ',
			// 			createtime: '2022.08.06 10:00:00',
			// 			isLike: true,
			// 			haveInterest: false,
			// 			likeCount: 3,
			// 			commentCount: 5,
			// 			author: {
			// 				nickname: '이연정',
			// 				profile:
			// 					'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTL3Hl5FXWpEFo9p7GTrCTz2jKeSpMPXQJ7JQ&usqp=CAU',
			// 			},
			// 			dogInfo: {
			// 				gender: '암컷',
			// 				age: '1살',
			// 				weight: '3kg',
			// 				breed: '말티즈',
			// 				neutering: 'Y',
			// 				areaSido: '대구',
			// 			},
			// 			imageList: [
			// 				{
			// 					order: 1,
			// 					url: 'https://images.pexels.com/photos/33053/dog-young-dog-small-dog-maltese.jpg',
			// 				},
			// 				{
			// 					order: 2,
			// 					url: 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQSaN5YBCEG28CtGfcPkUroCqJpWPutX6GllpVCu_XK5YDun1tAFbuUoHy52TnJ5FupkXU&usqp=CAU',
			// 				},
			// 			],
			// 		},
			// 	],
			// };
			// const boardList = data.boardList;
			// commit('SET_BOARD_LIST', boardList);
			commit('SET_PAGE_NO', pageNo + 1);
			commit('SET_TAG_NO', tagNo);
		},

		//게시물 검색
		searchBoard: ({ commit, rootGetters, getters }, { pageNo, keyword }) => {
			console.log(
				'searchBoard',
				getters['tagNo'],
				pageNo,
				rootGetters,
				keyword,
			);
			axios({
				url: api.board.boardSearch(getters['tagNo']),
				method: 'get',
				headers: rootGetters['auth/authHeader'],
				params: {
					keyword,
					pageNo,
				},
			})
				.then(res => {
					console.log(res);
					commit('SET_BOARD_LIST', res.data.body.data.boardList);
					commit('SET_PAGE_NO', pageNo + 1);
				})
				.catch(err => {
					console.error(err.response);
				});
			const data = {
				boardList: [
					{
						boardTag: 0,
						boardId: 1,
						dogName: '갱얼쥐',
						contents:
							' 오늘 몽이랑 같이 산책 다녀왔어요! 몽이랑 같이 벚꽃 구경해서 너무 행복합니닿ㅎ 다들 갱얼쥐랑 꽃구경 다녀오세요^^ ',
						createtime: '2022.08.06 10:00:00',
						isLike: true,
						haveInterest: false,
						likeCount: 3,
						commentCount: 5,
						author: {
							nickname: '황희원',
							profile:
								'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTL3Hl5FXWpEFo9p7GTrCTz2jKeSpMPXQJ7JQ&usqp=CAU',
						},
						dogInfo: {
							gender: '암컷',
							age: '1살',
							weight: '3kg',
							breed: '말티즈',
							neutering: 'Y',
							areaSido: '대구',
						},
						imageList: [
							{
								order: 1,
								url: 'https://images.pexels.com/photos/33053/dog-young-dog-small-dog-maltese.jpg',
							},
						],
					},
				],
			};
			const boardList = data.boardList;
			commit('SET_PAGE_NO', pageNo + 1);
			commit('SET_BOARD_LIST', boardList);
		},

		// 상세글 불러오기
		fetchDetailBoard: ({ commit, rootGetters }, boardId) => {
			console.log('featureDetailBoard', commit, rootGetters, boardId);
			//상세 게시글 정보 가져오기
			axios({
				url: api.board.boardDetail(boardId),
				method: 'get',
				headers: rootGetters['auth/authHeader'],
			})
				.then(res => {
					console.log('boardDetail', res.data.body.data);
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
					console.log(res.data.body.data);
					commit('SET_COMMENT_LIST', res.data.body.data);
				})
				.catch(err => {
					console.error(err.response);
				});

			// 좋아요 여부 가져오기
			console.log(api.board.isLike(boardId, rootGetters['auth/user'].userSeq));
			axios({
				url: api.board.isLike(boardId, rootGetters['auth/user'].userSeq),
				method: 'get',
				headers: rootGetters['auth/authHeader'],
			})
				.then(res => {
					console.log('isLike', res.data.body.data);
					commit('SET_LIKE_IN_DETAIL', res.data.body.data);
				})
				.catch(err => {
					console.error(err.response);
				});

			// 별표 여부 가져오기
			console.log(
				api.board.haveStar(boardId, rootGetters['auth/user'].userSeq),
			);
			axios({
				url: api.board.haveStar(boardId, rootGetters['auth/user'].userSeq),
				method: 'get',
				headers: rootGetters['auth/authHeader'],
			})
				.then(res => {
					console.log('haveStar', res.data.body.data);
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
		createBoard: ({ commit, rootGetters }, payload) => {
			console.log('createBoard', commit, rootGetters, payload);
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
					console.log(res.data.body.data.boardId);
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
		updateBoard: ({ commit, rootGetters }, { boardId, payload }) => {
			console.log('updateBoard', commit, rootGetters);
			axios({
				url: api.board.boardUpdate(boardId),
				method: 'put',
				headers: rootGetters['auth/authHeader'],
				data: payload,
			})
				.then(res => {
					console.log(res.data.body.data);
				})
				.catch(err => {
					console.error(err.response);
				});
		},

		// 글 삭제
		deleteBoard: ({ commit, rootGetters }, boardId) => {
			console.log('deleteBoard', commit, rootGetters);
			axios({
				url: api.board.boardDelete(boardId),
				method: 'delete',
				headers: rootGetters['auth/authHeader'],
			})
				.then(res => {
					console.log(res.data.body.data);
				})
				.catch(err => {
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
			console.log('createComment', commit, rootGetters);
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
			console.log('updateComment', commit, rootGetters);
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
			console.log('deleteComment', commit, rootGetters);
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
			console.log('createReply', commit, rootGetters);
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
			console.log('updateReply', commit, rootGetters);
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
			console.log('deleteReply', commit, rootGetters);
			const boardId = getters['boardId'];
			axios({
				url: api.board.replyDelete(boardId, commentId, replyId),
				method: 'delete',
				headers: rootGetters['auth/authHeader'],
				data: payload,
			})
				.then(res => {
					console.log('delete', res.data.body.data);
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
					console.log(res.data.body.data);
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
			console.log('createLike', commit, rootGetters, boardId, payload);
			commit('UPDATE_IS_LIKE', index);

			axios({
				url: api.board.likeCreate(boardId, payload.userSeq),
				method: 'post',
				headers: rootGetters['auth/authHeader'],
				data: payload,
			})
				.then(res => {
					console.log(res.data.body.data);
					commit('SET_LIKE_IN_DETAIL', res.data.body.data); // 상세 페이지
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
			console.log('deleteLike', commit, rootGetters, boardId, payload);
			commit('UPDATE_IS_LIKE', index);

			axios({
				url: api.board.likeDelete(boardId, payload.userSeq),
				method: 'delete',
				headers: rootGetters['auth/authHeader'],
				data: payload,
			})
				.then(res => {
					console.log(res.data.body.data);
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
					console.log(res.data.body.data);
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
			console.log('createStar', commit, rootGetters, boardId, payload);
			commit('UPDATE_HAVE_INTEREST', index);
			axios({
				url: api.board.starCreate(boardId, payload.userSeq),
				method: 'post',
				headers: rootGetters['auth/authHeader'],
				data: rootGetters['auth/user'].userSeq,
			})
				.then(res => {
					console.log(res.data.body.data);
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
			console.log('deleteStar', commit, rootGetters, boardId, payload);
			commit('UPDATE_HAVE_INTEREST', index);
			axios({
				url: api.board.starDelete(boardId, payload.userSeq),
				method: 'delete',
				headers: rootGetters['auth/authHeader'],
				data: rootGetters['auth/user'].userSeq,
			})
				.then(res => {
					console.log(res.data.body.data);
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
			console.log(payload);
			// const payload = {
			// 	applicantAnswerList: getters['application'],
			// 	userSeq: rootGetters['auth/user']['userSeq'],
			// };
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
	},
};
