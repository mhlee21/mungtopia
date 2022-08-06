<template>
	<div>
		<div class="adopt-detail-pg">
			<div class="dog-banner-container">
				<div class="motion-btn-box">
					<!-- 뒤로가기 눌렀을 때 메인 입양 페이지로 이동하게 설정했습니다. -->
					<a class="back-btn">
						<img src="@/assets/img/back-icon--white.svg" alt="" />
					</a>
					<ul class="favor-btn-wrap">
						<!-- active 여부로 색상 변환 -->
						<li class="favor star active">
							<i class="fa-solid fa-star"></i>
						</li>
						<li class="favor heart active">
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
	</div>
</template>

<script>
import DetailBoardImage from '@/components/board/detail/DetailBoardImage.vue';
import DetailBoardHeader from '@/components/board/detail/DetailBoardHeader.vue';
import DetailBoardBody from '@/components/board/detail/DetailBoardBody.vue';
import DetailCommentForm from '@/components/board/detail/DetailCommentForm.vue';
export default {
	components: {
		DetailBoardImage,
		DetailBoardHeader,
		DetailBoardBody,
		DetailCommentForm,
	},
	setup() {
		return {};
	},
};
</script>

<style lang="scss" scoped></style>
