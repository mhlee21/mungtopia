<template>
	<div>
		<swiper
			:modules="modules"
			:slides-per-view="1"
			@swiper="onSwiper"
			@slideChange="onSlideChange"
			:pagination="{ clickable: true }"
			loop
		>
			<swiper-slide v-for="image in imageList" :key="image.order">
				<img :src="image.url" class="image"
			/></swiper-slide>
		</swiper>
	</div>
</template>

<script>
import { computed } from 'vue';
import { useStore } from 'vuex';
import { Pagination, A11y } from 'swiper';
import { Swiper, SwiperSlide } from 'swiper/vue';
import 'swiper/css';
import 'swiper/css/pagination';
export default {
	components: {
		Swiper,
		SwiperSlide,
	},
	setup() {
		const store = useStore();
		const imageList = computed(() => store.getters['board/board']['imageList']);
		return { imageList, modules: [Pagination, A11y] };
	},
};
</script>

<style lang="scss" scoped>
.image {
	height: 40vh;
}
</style>
