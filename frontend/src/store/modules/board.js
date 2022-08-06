// import axios from 'axios';
// import api from '@/api/api';

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
		// 게시글 가져오기
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

		//
	},
};
