<template>
	<div>
		<swiper
			:modules="modules"
			:slides-per-view="1"
			:pagination="{ clickable: true }"
			loop
		>
			<swiper-slide v-for="image in imageList" :key="image.orders">
				<div class="image-wrapper">
					<img :src="image.serverPath" class="image" />
				</div>
			</swiper-slide>
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
		const imageList = computed(() => store.getters['board/imageList']);
		return { imageList, modules: [Pagination, A11y] };
	},
};
</script>

<style lang="scss" scoped>
.image-wrapper {
	height: 40vh;
	position: relative;
}
.image {
	position: absolute;
	top: 0;
	left: 0;
	transform: translate(50, 50);
	width: 100%;
	height: 100%;
	object-fit: cover;
	margin: auto;
}
</style>
