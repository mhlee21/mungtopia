<template>
	<div class="img-box">
		<img :src="tagImagePath" alt="" class="board-notice-icon" />
		<!-- 스와이퍼 -->
		<div class="swiper feed-banner-swiper">
			<div class="swiper-wrapper">
				<div class="swiper-slide" v-for="image in imageList" :key="image.order">
					<img :src="image.url" />
				</div>
			</div>
			<div class="swiper-pagination"></div>
		</div>
	</div>
</template>

<script>
import { computed } from 'vue';
export default {
	props: { imageList: Array, boardTag: Number },
	setup(props) {
		const tagImagePath = computed(() => {
			if (props.boardTag === 1) {
				return require('@/assets/img/adopt-notice-icon.svg');
			} else if (props.boardTag === 2) {
				return require('@/assets/img/review-notice-icon.svg');
			} else {
				return require('@/assets/img/board-notice-icon.svg');
			}
		});
		return { tagImagePath };
	},
};
</script>

<style lang="scss" scoped></style>
