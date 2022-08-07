<template>
	<div>
		<div class="adopt-detail-comment-wrap">
			<h3>댓글</h3>
			<div class="comment-wrap">
				<DetailBoardComment></DetailBoardComment>
			</div>
		</div>
	</div>
</template>

<script>
import DetailBoardComment from '@/components/board/detail/DetailBoardComment.vue';
export default {
	components: { DetailBoardComment },
	setup() {
		return {};
	},
};
</script>

<style lang="scss" scoped></style>
