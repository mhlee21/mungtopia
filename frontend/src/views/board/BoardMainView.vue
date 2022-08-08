<template>
	<div>
		<div class="main">
			<BoardFilter></BoardFilter>
			<div class="board-pg adopt-pg review-pg">
				<BoardSearchbar></BoardSearchbar>
				<MainBoard></MainBoard>
			</div>
			<div style="height: 50px"></div>
		</div>
		<NavBar></NavBar>
	</div>
</template>

<script>
import BoardFilter from '@/components/board/main/BoardFilter.vue';
import BoardSearchbar from '@/components/board/main/BoardSearchbar.vue';
import MainBoard from '@/components/board/main/MainBoard.vue';
import NavBar from '@/components/NavBar.vue';
import { useStore } from 'vuex';
export default {
	components: { BoardFilter, BoardSearchbar, MainBoard, NavBar },
	setup() {
		const store = useStore();
		store.dispatch('board/fetchBoardList', {
			tagNo: 0,
			pageNum: 0,
		});
		return {};
	},
};
</script>

<style></style>
