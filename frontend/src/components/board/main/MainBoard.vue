<template>
	<div>
		<ul class="feed-wrap">
			<li class="feed-item" v-for="board in boardList" :key="board.boardId">
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
		return { boardList, boardDetail };
	},
};
</script>

<style lang="scss" scoped></style>
