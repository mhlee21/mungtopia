<template>
	<div>
		<BaseUploadImage></BaseUploadImage>
		<BoardCategory></BoardCategory>
		<BoardCreateForm></BoardCreateForm>
	</div>
</template>

<script>
import BaseUploadImage from '@/components/board/create/BaseUploadImage.vue';
import BoardCategory from '@/components/board/create/BoardCategory.vue';
import BoardCreateForm from '@/components/board/create/BoardCreateForm.vue';
export default {
	components: { BaseUploadImage, BoardCategory, BoardCreateForm },
	setup() {
		return {};
	},
};
</script>

<style lang="scss" scoped></style>
