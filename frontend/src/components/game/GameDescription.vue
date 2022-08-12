<template>
	<div>
		<h3 class="description">{{ gameDescription }}</h3>
	</div>
</template>

<script>
import { useStore } from 'vuex';
export default {
	setup() {
		const store = useStore();
		const gameDescription = store.getters['game/gameDescription'];
		return { gameDescription };
	},
};
</script>

<style lang="scss" scoped>
.description {
	text-align: center;
	margin: 10px;
	padding: 10px;
	white-space: pre-line;
}
</style>
