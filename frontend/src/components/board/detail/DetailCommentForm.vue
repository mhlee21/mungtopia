<template>
	<div>
		<form @submit.prevent="createComment()" class="comment-form">
			<!-- lock icon 클래스 active 여부로 아이콘전환 -->
			<div @click="commentSecret()">
				<p class="comment-icon" :class="{ active: !isCommentSecret }">
					<i class="fa-solid fa-lock-open"></i>
				</p>
				<p class="comment-icon" :class="{ active: isCommentSecret }">
					<i class="fa-solid fa-lock"></i>
				</p>
			</div>
			<input type="text" v-model="newComment" />
			<button>댓글달기</button>
		</form>
	</div>
</template>

<script>
import { useStore } from 'vuex';
import { ref, computed } from 'vue';
export default {
	setup() {
		const store = useStore();

		// 댓글 생성
		const userSeq = computed(() => store.getters['auth/user']?.userSeq);
		const userNickname = computed(
			() => store.getters['auth/user']?.userNickname,
		);
		const newComment = ref(null);
		const isCommentSecret = ref(false);
		const commentSecret = () => {
			isCommentSecret.value = !isCommentSecret.value;
		};

		const createComment = () => {
			if (newComment.value) {
				const payload = {
					userSeq: userSeq.value,
					contents: newComment.value,
					secret: isCommentSecret.value,
					userNickname: userNickname.value,
				};
				store.dispatch('board/createComment', payload);
				newComment.value = null;
				isCommentSecret.value = false;
			} else {
				alert('댓글을 작성해주세요');
			}
		};
		return {
			newComment,
			commentSecret,
			isCommentSecret,
			createComment,
		};
	},
};
</script>

<style lang="scss" scoped></style>
