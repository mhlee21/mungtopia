<template>
	<div class="protector-detail-component">
		<img class="dog-img" :src="protectorDetail.dogImg" />
		<div class="dog-name" style="width: 70%; padding: 5%">
			{{ protectorDetail.dogName }}
		</div>
	</div>
</template>

<script>
import { useStore } from 'vuex';
import { computed } from 'vue';
export default {
	setup() {
		const store = useStore();
		const protectorDetail = computed(
			() => store.getters['adopt/protectorDetail'],
		);
		return { protectorDetail };
	},
};
</script>

<style scoped></style>
