<template>
	<div>
		<ul class="feed-wrap">
			<li
				class="feed-item"
				v-for="(board, index) in boardList"
				:key="board.boardId"
			>
				<MainBoardHeader
					:author="board.author"
					:createtime="board.createtime"
				></MainBoardHeader>
				<div class="dog-cont-box">
					<MainBoardImage
						:image-list="board.imageList"
						:board-tag="board.boardTag"
					></MainBoardImage>
					<MainBoardBody
						:board="board"
						@click.stop="boardDetail(board.boardId)"
						@click-star="clickStar(board.boardId, index, board.isLike)"
						@click-heart="clickHeart(board.boardId, index, board.haveInterest)"
					></MainBoardBody>
				</div>
			</li>
		</ul>
	</div>
</template>

<script>
import MainBoardHeader from '@/components/board/main/MainBoardHeader.vue';
import MainBoardImage from '@/components/board/main/MainBoardImage.vue';
import MainBoardBody from '@/components/board/main/MainBoardBody.vue';
import { useRouter } from 'vue-router';
import { useStore } from 'vuex';
import { computed } from 'vue';
export default {
	components: { MainBoardHeader, MainBoardImage, MainBoardBody },
	setup() {
		const router = useRouter();
		const store = useStore();
		const boardList = computed(() => store.getters['board/boardList']);
		const boardDetail = boardId => {
			router.push({ name: 'boardDetail', params: { boardId } });
		};
		const clickHeart = (boardId, index, isLike) => {
			console.log(isLike);
			if (isLike) {
				store.dispatch('board/deleteLike', { boardId, index });
			} else {
				store.dispatch('board/createLike', { boardId, index });
			}
		};
		const clickStar = (boardId, index, haveInterest) => {
			console.log(haveInterest);
			if (haveInterest) {
				store.dispatch('board/deleteStar', { boardId, index });
			} else {
				store.dispatch('board/createStar', { boardId, index });
			}
		};
		return { boardList, boardDetail, clickHeart, clickStar };
	},
};
</script>

<style lang="scss" scoped></style>
