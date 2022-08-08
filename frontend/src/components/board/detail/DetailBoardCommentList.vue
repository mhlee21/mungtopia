<template>
	<div>
		<div class="adopt-detail-comment-wrap">
			<h3>댓글</h3>
			<div class="comment-wrap">
				<DetailBoardComment
					v-for="comment in commentList"
					:key="comment.commentId"
					:comment="comment"
				></DetailBoardComment>
			</div>
		</div>
	</div>
</template>

<script>
import DetailBoardComment from '@/components/board/detail/DetailBoardComment.vue';
import { useStore } from 'vuex';
export default {
	components: { DetailBoardComment },
	setup() {
		const store = useStore();
		const commentList = store.getters['board/board']['commentList'];
		return { commentList };
	},
};
</script>

<style lang="scss" scoped></style>
