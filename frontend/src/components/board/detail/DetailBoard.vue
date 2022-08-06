<template>
	<div>
		<DetailBoardImage></DetailBoardImage>
		<DetailBoardHeader></DetailBoardHeader>
		<DetailBoardBody></DetailBoardBody>
	</div>
</template>

<script>
import DetailBoardImage from '@/components/board/detail/DetailBoardImage.vue';
import DetailBoardHeader from '@/components/board/detail/DetailBoardHeader.vue';
import DetailBoardBody from '@/components/board/detail/DetailBoardBody.vue';
export default {
	components: { DetailBoardImage, DetailBoardHeader, DetailBoardBody },
	setup() {
		return {};
	},
};
</script>

<style lang="scss" scoped></style>
