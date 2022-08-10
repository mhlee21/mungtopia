import axios from 'axios';
import api from '@/api/api';
import router from '@/router';

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
			reply: {},
			modalType: null,
			isAdopting: null,
			category: 0,
			applicationPageNum: 1,
		};
	},
	getters: {
		boardList: state => state.boardList,
		pageNo: state => state.pageNo,
		tagNo: state => state.tagNo,
		board: state => state.board,
		boardId: state => state.board.boardId,
		adoptStatus: state => state.adoptStatus,
		comment: state => state.comment,
		reply: state => state.reply,
		modalType: state => state.modalType,
		isAdopting: state => state.isAdopting,
		category: state => state.category,
		applicationPageNum: state => state.applicationPageNum,
	},
	mutations: {
		SET_BOARD_LIST: (state, boardList) => (state.boardList = boardList),
		SET_PAGE_NO: (state, pageNo) => (state.pageNo = pageNo),
		SET_TAG_NO: (state, tagNo) => (state.tagNo = tagNo),
		SET_BOARD: (state, board) => (state.board = board),
		SET_ADOPT_STATUS: (state, adoptStatus) => (state.adoptStatus = adoptStatus),
		SET_COMMENT: (state, comment) => (state.comment = comment),
		SET_REPLY: (state, reply) => (state.reply = reply),
		SET_MODAL_TYPE: (state, modalType) => (state.modalType = modalType),
		SET_IS_ADOPTING: (state, isAdopting) => (state.isAdopting = isAdopting),
		SET_CATEGORY: (state, category) => (state.category = category),
		SET_APPLICATION_PAGE_NUM: (state, applicationPageNum) =>
			(state.applicationPageNum = applicationPageNum),
		UPDATE_IS_LIKE: state => (state.board.isLike = !state.board.isLike),
		UPDATE_HAVE_INTEREST: state =>
			(state.board.haveInterest = !state.board.haveInterest),
	},
	actions: {
		// 전체글 불러오기
		fetchBoardList: ({ commit, rootGetters }, { tagNo, pageNo }) => {
			console.log('fetchBoardList', tagNo, pageNo, rootGetters);

			axios({
				url: api.board.boardMain(tagNo),
				method: 'get',
				headers: rootGetters['auth/authHeader'],
				params: {
					pageNo,
				},
			})
				.then(res => {
					console.log(res);
					commit('SET_PAGE_NO', pageNo + 1);
					commit('SET_BOARD_LIST', res.body.data.boardList);
				})
				.catch(err => {
					console.error(err.response);
				});
			const data = {
				boardList: [
					{
						boardTag: 1,
						boardId: 1,
						dogName: '몽이',
						contents:
							' 오늘 몽이랑 같이 산책 다녀왔어요! 몽이랑 같이 벚꽃 구경해서 너무 행복합니닿ㅎ 다들 갱얼쥐랑 꽃구경 다녀오세요^^ ',
						createtime: '2022.08.06 10:00:00',
						isLike: true,
						haveInterest: false,
						likeCount: 3,
						commentCount: 5,
						author: {
							nickname: '이연정',
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
							{
								order: 2,
								url: 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQSaN5YBCEG28CtGfcPkUroCqJpWPutX6GllpVCu_XK5YDun1tAFbuUoHy52TnJ5FupkXU&usqp=CAU',
							},
						],
					},
				],
			};
			const boardList = data.boardList;
			commit('SET_BOARD_LIST', boardList);
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
					commit('SET_BOARD_LIST', res.body.data.boardList);
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
		fetchDetailBoard: ({ commit, rootGetters }, { boardId }) => {
			console.log('featureDetailBoard', commit, rootGetters, boardId);
			// axios({
			// 	url: api.board.boardDetail(boardId),
			// 	method: 'get',
			// 	headers: rootGetters['auth/authHeader'],
			// })
			// 	.then(res => {
			// 		console.log(res.body.data);
			// 		commit('SET_BOARD', res.body.data)
			// 	})
			// 	.catch(err => {
			// 		console.error(err.response);
			// 	});
			// axios({
			// 	url: api.board.adoptionStatusCheck(rootGetters['auth/user']['userSeq']),
			// 	method: 'get',
			// 	headers: rootGetters['auth/authHeader'],
			// })
			// 	.then(res => {
			// 		console.log(res.body.data);
			// 		commit('SET_IS_ADOPTING', res.body.data.status);
			// 	})
			// 	.catch(err => {
			// 		console.error(err.response);
			// 	});

			const board = {
				boardId: 1,
				tag: 1,
				dogName: '몽이',
				contents:
					'귀여움이 큰 매력인 몽이예요! 사람을 좋아해요! 보면 볼수록 매력이 넘치는 아이랍니다 이 아이와 가족이 되어줄 분을 구하고 있어요 우리 몽이와 가족 되어주세요 연락 기다리겠습니다',
				createtime: '2022.08.07 11:20:00',
				dogNature: ['온순함', '귀여움', '조용함', '사람 좋아함'],
				isLike: true,
				haveInterest: false, // 입양글일때만 존재
				imageList: [
					{
						order: 1,
						url: 'https://images.pexels.com/photos/33053/dog-young-dog-small-dog-maltese.jpg',
					},
					{
						order: 2,
						url: 'https://images.pexels.com/photos/33053/dog-young-dog-small-dog-maltese.jpg',
					},
				],
				author: {
					status: 1,
					userSeq: 2,
					nickname: '이연정',
					profile: 'https://freesvg.org/img/abstract-user-flat-4.png',
				},
				dogInfo: {
					gender: '암컷',
					age: '1살',
					weight: '3kg',
					breed: '말티즈',
					neutering: 'Y',
					areaSido: '대구',
				},
				commentList: [
					{
						commentId: 1,
						author: {
							userSeq: 1,
							nickname: '황희원',
							profile: 'https://freesvg.org/img/abstract-user-flat-4.png',
						},
						contents: '많이 짖는 편인가요?',
						secret: false,
						createtime: '2022.08.07 11:23:00',
						replyList: [
							{
								replyId: 2,
								contents: '아뇨 몽이 거의 안 짖어요! 순둥이입니다',
								createtime: '2022.08.07 12:25:00',
								author: {
									userSeq: 2,
									nickname: '이연정',
									profile: 'https://freesvg.org/img/abstract-user-flat-4.png',
								},
							},
						],
					},
				],
			};
			commit('SET_BOARD', board);
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
			// console.log(res.body.data);
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
			router.push({
				name: 'boardDetail',
				params: { boardId: 1 },
			});
			// axios({
			// 	url: api.board.boardCreate(),
			// 	method: 'post',
			// 	headers: {rootGetters['auth/authHeader'], "Content-Type": "multipart/form-data"},
			// 	data: payload,
			// })
			// 	.then(res => {
			// 		console.log(res.body.data.boardId);
			// 		this.$router.push({
			// 			name: 'boardDetail',
			// 			params: { boardId: res.body.data.boardId },
			// 		});
			// 	})
			// 	.catch(err => {
			// 		console.error(err.response);
			// 	});
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
					console.log(res.body.data);
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
					console.log(res.body.data);
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
		createComment: (
			{ commit, dispatch, getters, rootGetters },
			{ payload },
		) => {
			console.log('createComment', commit, rootGetters);
			const boardId = getters['boardId'];
			axios({
				url: api.board.commentCreate(boardId),
				method: 'post',
				headers: rootGetters['auth/authHeader'],
				data: payload,
			})
				.then(res => {
					dispatch('fetchDetailBoard', boardId);
					console.log(res.body.data);
				})
				.catch(err => {
					console.error(err.response);
				});
		},

		// 댓글 수정
		updateComment: (
			{ commit, dispatch, rootGetters, getters },
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
					dispatch('fetchDetailBoard', boardId);
					console.log(res.body.data);
				})
				.catch(err => {
					console.error(err.response);
				});
		},

		// 댓글 삭제
		deleteComment: (
			{ commit, dispatch, rootGetters, getters },
			{ commentId },
		) => {
			console.log('deleteComment', commit, rootGetters);
			const boardId = getters['boardId'];
			axios({
				url: api.board.commentDelete(boardId, commentId),
				method: 'delete',
				headers: rootGetters['auth/authHeader'],
			})
				.then(res => {
					dispatch('fetchDetailBoard', boardId);
					console.log(res.body.data);
				})
				.catch(err => {
					console.error(err.response);
				});
		},

		// 대댓글 쓰기
		createReply: (
			{ commit, dispatch, rootGetters, getters },
			{ commentId, payload },
		) => {
			console.log('createReply', commit, rootGetters);
			const boardId = getters['boardId'];
			axios({
				url: api.board.replyCreate(boardId, commentId),
				method: 'post',
				headers: rootGetters['auth/authHeader'],
				data: payload,
			})
				.then(res => {
					console.log(res.body.data);
					dispatch('fetchDetailBoard', boardId);
				})
				.catch(err => {
					console.error(err.response);
				});
		},

		// 대댓글 수정
		updateReply: (
			{ commit, dispatch, rootGetters },
			{ boardId, commentId, replyId, payload },
		) => {
			console.log('updateReply', commit, rootGetters);
			axios({
				url: api.board.replyUpdate(boardId, commentId, replyId),
				method: 'put',
				headers: rootGetters['auth/authHeader'],
				data: payload,
			})
				.then(res => {
					dispatch('fetchDetailBoard', boardId);
					console.log(res.body.data);
				})
				.catch(err => {
					console.error(err.response);
				});
		},

		// 대댓글 삭제
		deleteReply: (
			{ commit, dispatch, rootGetters },
			{ boardId, commentId, replyId },
		) => {
			console.log('deleteReply', commit, rootGetters);
			axios({
				url: api.board.replyDelete(boardId, commentId, replyId),
				method: 'delete',
				headers: rootGetters['auth/authHeader'],
			})
				.then(res => {
					console.log(res.body.data);
					dispatch('fetchDetailBoard', boardId);
				})
				.catch(err => {
					console.error(err.response);
				});
		},
		// 좋아요 하기
		createLike: ({ commit, rootGetters }, { boardId }) => {
			const payload = {
				userSeq: rootGetters['user/userSeq'],
			};
			console.log('createLike', commit, rootGetters, boardId, payload);
			commit('UPDATE_IS_LIKE');
			// axios({
			// 	url: api.board.likeCreate(boardId),
			// 	method: 'post',
			// 	headers: rootGetters['auth/authHeader'],
			// 	data: payload,
			// })
			// 	.then(res => {
			// 		console.log(res.body.data);
			// 	})
			// 	.catch(err => {
			// 		console.error(err.response);
			// 	});
		},

		// 좋아요 삭제
		deleteLike: ({ commit, rootGetters }, { boardId }) => {
			const payload = {
				userSeq: rootGetters['user/userSeq'],
			};
			console.log('deleteLike', commit, rootGetters, boardId, payload);
			commit('UPDATE_IS_LIKE');
			// axios({
			// 	url: api.board.likeDelete(boardId),
			// 	method: 'delete',
			// 	headers: rootGetters['auth/authHeader'],
			// 	data: payload,
			// })
			// 	.then(res => {
			// 		console.log(res.body.data);
			// 	})
			// 	.catch(err => {
			// 		console.error(err.response);
			// 	});
		},

		// 별표 하기
		createStar: ({ commit, rootGetters }, { boardId }) => {
			const payload = {
				userSeq: rootGetters['user/userSeq'],
			};
			console.log('createStar', commit, rootGetters, boardId, payload);
			commit('UPDATE_HAVE_INTEREST');
			// axios({
			// 	url: api.board.starCreate(boardId),
			// 	method: 'post',
			// 	headers: rootGetters['auth/authHeader'],
			// 	data: payload,
			// })
			// 	.then(res => {
			// 		console.log(res.body.data);
			// 	})
			// 	.catch(err => {
			// 		console.error(err.response);
			// 	});
		},
		// 별표 삭제
		deleteStar: ({ commit, rootGetters }, { boardId }) => {
			const payload = {
				userSeq: rootGetters['user/userSeq'],
			};
			console.log('deleteStar', commit, rootGetters, boardId, payload);
			commit('UPDATE_HAVE_INTEREST');
			// axios({
			// 	url: api.board.starDelete(boardId),
			// 	method: 'delete',
			// 	headers: rootGetters['auth/authHeader'],
			// 	data: payload,
			// })
			// 	.then(res => {
			// 		console.log(res.body.data);
			// 	})
			// 	.catch(err => {
			// 		console.error(err.response);
			// 	});
		},

		// 입양 신청서 작성
		createApplication: ({ commit, rootGetters }, { boardId, payload }) => {
			console.log('createApplication', commit, rootGetters);
			axios({
				url: api.board.applicationCreate(boardId),
				method: 'post',
				headers: rootGetters['auth/authHeader'],
				data: payload,
			})
				.then(res => {
					console.log(res.body.data);
				})
				.catch(err => {
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
	},
};
