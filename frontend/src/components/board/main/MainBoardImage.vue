<template>
	<div class="img-box">
		<img
			src="@/assets/img/board-notice-icon.svg"
			alt=""
			class="board-notice-icon"
		/>
		<!-- 스와이퍼 -->
		<div class="swiper feed-banner-swiper">
			<div class="swiper-wrapper">
				<div class="swiper-slide">
					<img src="@/assets/img/dog_img1.jpg" />
				</div>
				<div class="swiper-slide">
					<img src="@/assets/img/dog_img2.jpg" />
				</div>
			</div>
			<div class="swiper-pagination"></div>
		</div>
	</div>
</template>

<script>
export default {
	setup() {
		return {};
	},
};
</script>

<style lang="scss" scoped></style>
