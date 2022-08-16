<template>
	<div class="adopt-detail-pg">
		<div class="dog-banner-container">
			<div class="motion-btn-box">
				<!-- 뒤로가기 -->
				<a class="back-btn" @click="goBoardMain()">
					<img src="@/assets/img/back-icon--white.svg" alt="" />
				</a>
				<ul class="favor-btn-wrap">
					<!-- 관심 -->
					<li
						class="favor star"
						:class="{ active: haveInterest }"
						@click="clickStar()"
					>
						<i class="fa-solid fa-star"></i>
					</li>
					<!-- 좋아요 -->
					<li
						class="favor heart"
						:class="{ active: isLike }"
						@click="clickLike()"
					>
						<i class="fa-solid fa-heart"></i>
					</li>
				</ul>
			</div>
			<DetailBoardImage></DetailBoardImage>
			<DetailBoardHeader></DetailBoardHeader>
		</div>
		<DetailBoardBody></DetailBoardBody>
		<DetailCommentForm></DetailCommentForm>
	</div>
</template>

<script>
import DetailBoardImage from '@/components/board/detail/DetailBoardImage.vue';
import DetailBoardHeader from '@/components/board/detail/DetailBoardHeader.vue';
import DetailBoardBody from '@/components/board/detail/DetailBoardBody.vue';
import DetailCommentForm from '@/components/board/detail/DetailCommentForm.vue';
import { useRouter } from 'vue-router';
import { useStore } from 'vuex';
import { computed } from 'vue';
export default {
	components: {
		DetailBoardImage,
		DetailBoardHeader,
		DetailBoardBody,
		DetailCommentForm,
	},
	setup() {
		const router = useRouter();
		const store = useStore();
		const goBoardMain = () => {
			router.push({ name: 'boardMain' });
		};

		// boardId
		const boardId = computed(() => store.getters['board/board']['boardId']);
		// 좋아요
		const isLike = computed(() => store.getters['board/likeInDetail']);
		const clickLike = () => {
			if (isLike.value) {
				// 좋아요 삭제
				store.dispatch('board/deleteLike', {
					boardId: boardId.value,
					index: -1,
				});
			} else {
				// 좋아요 생성
				store.dispatch('board/createLike', {
					boardId: boardId.value,
					index: -1,
				});
			}
		};

		// 관심
		const haveInterest = computed(() => store.getters['board/starInDetail']);
		const clickStar = () => {
			if (haveInterest.value) {
				// 관심 삭제
				store.dispatch('board/deleteStar', {
					boardId: boardId.value,
					index: -1,
				});
			} else {
				// 관심 생성
				store.dispatch('board/createStar', {
					boardId: boardId.value,
					index: -1,
				});
			}
		};
		return { goBoardMain, isLike, haveInterest, clickStar, clickLike };
	},
};
</script>

<style lang="scss" scoped></style>
