<template>
	<div>
		<DetailBoardContent></DetailBoardContent>
		<DetailBoardCommentList></DetailBoardCommentList>
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
