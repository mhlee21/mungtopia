import axios from 'axios';
import api from '@/api/api';

export default {
	namespaced: true,
	state() {
		return {
			boardList: [],
			pageNo: 0,
			tagNo: 0,
		};
	},
	getters: {
		boardList: state => state.boardList,
		pageNo: state => state.pageNo,
		tagNo: state => state.tagNo,
	},
	mutations: {
		SET_BOARD_LIST: (state, boardList) => (state.boardList = boardList),
		SET_PAGE_NO: (state, pageNo) => (state.pageNo = pageNo),
		SET_TAG_NO: (state, tagNo) => (state.tagNo = tagNo),
	},
	actions: {
		// 전체글 불러오기
		fetchBoardList: ({ commit, rootGetters }, { tagNo, pageNo }) => {
			console.log('fetchBoardList', tagNo, pageNo, rootGetters);
			// axios({
			// 	url: api.board.boardMain(tagNo),
			// 	method: 'get',
			// 	headers: rootGetters['auth/authHeader'],
			// 	params: {
			// 		pageNo,
			// 	},
			// })
			// 	.then(res => {
			// 		console.log(res);
			// 		commit('SET_PAGE_NO', pageNo + 1);
			// 		commit('SET_BOARD_LIST', res.data.boardList);
			// 	})
			// 	.catch(err => {
			// 		console.error(err.response);
			// 	});
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
			// axios({
			// 	url: api.board.boardSearch(getters['tagNo']),
			// 	method: 'get',
			// 	headers: rootGetters['auth/authHeader'],
			// 	params: {
			// 		keyword,
			// 		pageNo,
			// 	},
			// })
			// 	.then(res => {
			// 		console.log(res);
			// 		commit('SET_BOARD_LIST', res.data.boardList);
			// 		commit('SET_PAGE_NO', pageNo + 1);
			// 	})
			// 	.catch(err => {
			// 		console.error(err.response);
			// 	});
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
			console.log('featureDetailBoard', commit, rootGetters);
			axios({
				url: api.board.boardDetail(boardId),
				method: 'get',
				headers: rootGetters['auth/authHeader'],
			})
				.then(res => {
					console.log(res.data);
				})
				.catch(err => {
					console.error(err.response);
				});
		},

		// 글 쓰기
		createBoard: ({ commit, rootGetters }, payload) => {
			console.log('createBoard', commit, rootGetters);
			axios({
				url: api.board.boardCreate(),
				method: 'post',
				headers: rootGetters['auth/authHeader'],
				data: payload,
			})
				.then(res => {
					console.log(res.data);
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
					console.log(res.data);
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
					console.log(res.data);
				})
				.catch(err => {
					console.error(err.response);
				});
		},

		// 댓글 쓰기
		createComment: ({ commit, rootGetters }, { boardId, payload }) => {
			console.log('createComment', commit, rootGetters);
			axios({
				url: api.board.commentCreate(boardId),
				method: 'post',
				headers: rootGetters['auth/authHeader'],
				data: payload,
			})
				.then(res => {
					console.log(res.data);
				})
				.catch(err => {
					console.error(err.response);
				});
		},

		// 댓글 수정
		updateComment: (
			{ commit, rootGetters },
			{ boardId, commentId, payload },
		) => {
			console.log('updateComment', commit, rootGetters);
			axios({
				url: api.board.commentUpdate(boardId, commentId),
				method: 'put',
				headers: rootGetters['auth/authHeader'],
				data: payload,
			})
				.then(res => {
					console.log(res.data);
				})
				.catch(err => {
					console.error(err.response);
				});
		},

		// 댓글 삭제
		deleteComment: ({ commit, rootGetters }, { boardId, commentId }) => {
			console.log('deleteComment', commit, rootGetters);
			axios({
				url: api.board.commentDelete(boardId, commentId),
				method: 'delete',
				headers: rootGetters['auth/authHeader'],
			})
				.then(res => {
					console.log(res.data);
				})
				.catch(err => {
					console.error(err.response);
				});
		},

		// 대댓글 쓰기
		createReply: ({ commit, rootGetters }, { boardId, commentId, payload }) => {
			console.log('createReply', commit, rootGetters);
			axios({
				url: api.board.replyCreate(boardId, commentId),
				method: 'post',
				headers: rootGetters['auth/authHeader'],
				data: payload,
			})
				.then(res => {
					console.log(res.data);
				})
				.catch(err => {
					console.error(err.response);
				});
		},

		// 대댓글 수정
		updateReply: (
			{ commit, rootGetters },
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
					console.log(res.data);
				})
				.catch(err => {
					console.error(err.response);
				});
		},

		// 대댓글 삭제
		deleteReply: ({ commit, rootGetters }, { boardId, commentId, replyId }) => {
			console.log('deleteReply', commit, rootGetters);
			axios({
				url: api.board.replyDelete(boardId, commentId, replyId),
				method: 'delete',
				headers: rootGetters['auth/authHeader'],
			})
				.then(res => {
					console.log(res.data);
				})
				.catch(err => {
					console.error(err.response);
				});
		},
		// 좋아요 하기
		createLike: ({ commit, rootGetters }, { boardId, payload }) => {
			console.log('createLike', commit, rootGetters);
			axios({
				url: api.board.likeCreate(boardId),
				method: 'post',
				headers: rootGetters['auth/authHeader'],
				data: payload,
			})
				.then(res => {
					console.log(res.data);
				})
				.catch(err => {
					console.error(err.response);
				});
		},

		// 좋아요 삭제
		deleteLike: ({ commit, rootGetters }, { boardId, payload }) => {
			console.log('deleteLike', commit, rootGetters);
			axios({
				url: api.board.likeDelete(boardId),
				method: 'delete',
				headers: rootGetters['auth/authHeader'],
				data: payload,
			})
				.then(res => {
					console.log(res.data);
				})
				.catch(err => {
					console.error(err.response);
				});
		},

		// 별표 하기
		createStar: ({ commit, rootGetters }, { boardId, payload }) => {
			console.log('createStar', commit, rootGetters);
			axios({
				url: api.board.starCreate(boardId),
				method: 'post',
				headers: rootGetters['auth/authHeader'],
				data: payload,
			})
				.then(res => {
					console.log(res.data);
				})
				.catch(err => {
					console.error(err.response);
				});
		},
		// 별표 삭제
		deleteStar: ({ commit, rootGetters }, { boardId, payload }) => {
			console.log('deleteStar', commit, rootGetters);
			axios({
				url: api.board.starDelete(boardId),
				method: 'delete',
				headers: rootGetters['auth/authHeader'],
				data: payload,
			})
				.then(res => {
					console.log(res.data);
				})
				.catch(err => {
					console.error(err.response);
				});
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
					console.log(res.data);
				})
				.catch(err => {
					console.error(err.response);
				});
		},
	},
};
