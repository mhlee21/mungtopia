<template>
	<div class="image-box" v-if="applicationPageNum === 1">
		<i class="fa-solid fa-image image-upload-icon"></i>
		<p class="image-upload-notice">
			사진을 업로드 해주세요. <br />최대 3장까지 가능합니다.
		</p>
	</div>
</template>

<script>
import { computed } from 'vue';
import { useStore } from 'vuex';
export default {
	setup() {
		const store = useStore();
		const applicationPageNum = computed(
			() => store.getters['board/applicationPageNum'],
		);
		return { applicationPageNum };
	},
};
</script>

<style lang="scss" scoped></style>
