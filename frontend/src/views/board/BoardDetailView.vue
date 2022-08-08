<template>
	<div>
		<div class="main not-pd">
			<DetailBoard></DetailBoard>
		</div>
		<NavBar></NavBar>
	</div>
</template>

<script>
import DetailBoard from '@/components/board/detail/DetailBoard.vue';
import NavBar from '@/components/NavBar.vue';
import { useRoute } from 'vue-router';
import { useStore } from 'vuex';
export default {
	components: { DetailBoard, NavBar },
	setup() {
		const store = useStore();
		const route = useRoute();
		const boardId = route.params['boardId'];
		store.dispatch('board/fetchDetailBoard', boardId);
		return {};
	},
};
</script>

<style lang="scss" scoped></style>
