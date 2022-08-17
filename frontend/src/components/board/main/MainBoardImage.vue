<template>
	<div class="img-box">
		<img :src="tagImagePath" alt="" class="board-notice-icon" />
		<swiper
			:modules="modules"
			:slides-per-view="1"
			:pagination="{ clickable: true }"
			loop
		>
			<swiper-slide v-for="image in imageStrorageList" :key="image.order"
				><img :src="image" class="image"
			/></swiper-slide>
		</swiper>
	</div>
</template>

<script>
import { computed } from 'vue';
import { Pagination, A11y } from 'swiper';
import { Swiper, SwiperSlide } from 'swiper/vue';
import 'swiper/css';
import 'swiper/css/pagination';

export default {
	components: {
		Swiper,
		SwiperSlide,
	},
	props: { imageStrorageList: Array, boardTag: Number },
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

		return { tagImagePath, modules: [Pagination, A11y] };
	},
};
</script>

<style lang="scss" scoped>
.image {
	height: 40vh;
	width: 100%;
}
</style>
