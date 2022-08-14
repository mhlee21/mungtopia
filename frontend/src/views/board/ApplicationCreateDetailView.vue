<template>
	<div class="application-create-pg">
		<div class="appication-header">
			<div class="title">입양신청서</div>
		</div>
		<div class="application-body">
			<ApplicationQuestion></ApplicationQuestion>
		</div>
	</div>
</template>

<script>
import ApplicationQuestion from '@/components/board/application/ApplicationQuestion.vue';
// import { computed } from '@vue/reactivity';

export default {
	components: {
		ApplicationQuestion,
	},
	setup() {
		return {};
	},
};
</script>

<style lang="scss" scoped></style>
