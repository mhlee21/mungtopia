<template>
	<div class="board-create-view">
		<div class="new-adopt-pg">
			<BaseUploadImage></BaseUploadImage>
			<BoardCategory></BoardCategory>
			<BoardCreateForm></BoardCreateForm>
		</div>
		<NavBar></NavBar>
	</div>
</template>

<script>
import BaseUploadImage from '@/components/board/create/BaseUploadImage.vue';
import BoardCategory from '@/components/board/create/BoardCategory.vue';
import BoardCreateForm from '@/components/board/create/BoardCreateForm.vue';
import NavBar from '@/components/NavBar.vue';
export default {
	components: { BaseUploadImage, BoardCategory, BoardCreateForm, NavBar },
	setup() {
		return {};
	},
};
</script>

<style lang="scss" scoped></style>
