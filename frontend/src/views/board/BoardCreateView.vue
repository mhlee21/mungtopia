<template>
	<div class="board-create-view">
		<div class="new-adopt-pg">
			<BoardCategory></BoardCategory>
			<BoardCreateForm></BoardCreateForm>
		</div>
		<NavBar></NavBar>
	</div>
</template>

<script>
import BoardCategory from '@/components/board/create/BoardCategory.vue';
import BoardCreateForm from '@/components/board/create/BoardCreateForm.vue';
import NavBar from '@/components/NavBar.vue';
export default {
	components: { BoardCategory, BoardCreateForm, NavBar },
	setup() {
		return {};
	},
};
</script>

<style lang="scss" scoped></style>
