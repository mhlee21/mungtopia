<template>
	<div>
		<h2 class="title">{{ gameTitle }}</h2>
	</div>
</template>

<script>
import { useStore } from 'vuex';
export default {
	setup() {
		const store = useStore();
		const gameTitle = store.getters['game/gameTitle'];
		return { gameTitle };
	},
};
</script>

<style lang="scss" scoped>
.title {
	text-align: center;
	margin: 20px;
	color: #0d6aba;
	padding: 10px;
}
</style>
