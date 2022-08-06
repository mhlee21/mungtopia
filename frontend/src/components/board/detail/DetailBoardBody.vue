<template>
	<div>
		<div class="complex-container">
			<DetailBoardContent></DetailBoardContent>
			<DetailBoardCommentList></DetailBoardCommentList>
		</div>
	</div>
</template>

<script>
import DetailBoardContent from '@/components/board/detail/DetailBoardContent.vue';
import DetailBoardCommentList from '@/components/board/detail/DetailBoardCommentList.vue';
export default {
	components: { DetailBoardContent, DetailBoardCommentList },
	setup() {
		return {};
	},
};
</script>

<style lang="scss" scoped></style>
