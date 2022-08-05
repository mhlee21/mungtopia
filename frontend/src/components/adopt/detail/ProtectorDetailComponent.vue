<template>
	<div class="protector-detail-component">
		<img
			class="protector-detail-component-dog-image"
			:src="protectorDetail.dogImg"
		/>
		<div style="width: 70%; padding: 5%">{{ protectorDetail.dogName }}</div>
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

<style scoped>
.protector-detail-component {
	display: flex;
	justify-content: center;
	background-color: white;
	border-radius: 1rem;
	padding: 2% 5%;
}
.protector-detail-component-dog-image {
	width: 30%;
	height: 70px;
	border-radius: 1rem;
}
</style>
