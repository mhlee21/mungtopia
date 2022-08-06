<template>
	<div class="board-main-view">
		<div class="main">
			<BoardFilter></BoardFilter>
			<div class="board-pg">
				<BoardSearchbar></BoardSearchbar>
				<MainBoard></MainBoard>
			</div>
		</div>
		<NavBar></NavBar>
	</div>
</template>

<script>
import BoardFilter from '@/components/board/main/BoardFilter.vue';
import BoardSearchbar from '@/components/board/main/BoardSearchbar.vue';
import MainBoard from '@/components/board/main/MainBoard.vue';
import NavBar from '@/components/NavBar.vue';
export default {
	components: { BoardFilter, BoardSearchbar, MainBoard, NavBar },
	setup() {
		return {};
	},
};
</script>

<style></style>
