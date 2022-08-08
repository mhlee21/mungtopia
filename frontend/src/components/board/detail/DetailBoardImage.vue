<template>
	<div>
		<div class="swiper dog-banner-swiper">
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
import { useStore } from 'vuex';
export default {
	setup() {
		const store = useStore();
		const imageList = computed(() => store.getters['board/board']['imageList']);
		return { imageList };
	},
};
</script>

<style lang="scss" scoped></style>
