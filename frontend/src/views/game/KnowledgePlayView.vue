<template>
	<div class="frame">
		<div class="status">
			<h3>상태창</h3>
		</div>
		<div style="text-align: center">
			<img src="" alt="" />
		</div>
		<GameQuestion :gameQuestion="gameQuestion"></GameQuestion>
	</div>
</template>

<script>
// import Qdata from '@/assets/Qdata.json';
import GameQuestion from '@/components/game/GameQuestion.vue';
import { useStore } from 'vuex';
import { computed } from 'vue';

export default {
	components: {
		GameQuestion,
	},
	setup() {
		const store = useStore();
		let gameQuestion = computed(() => store.getters['game/gameQuestion']);
		return { gameQuestion };
	},
};
</script>

<style lang="scss" scoped>
.frame {
	width: 350px;
	height: 640px;
	background-color: #fffbf0;
	border: black solid 1px;
	border-radius: 1rem;
	position: relative;
}
.title {
	text-align: center;
	margin: 20px;
	color: #0d6aba;
	padding: 10px;
}
.status {
	text-align: center;
	margin: 0px;
	color: #0d6aba;
	padding: 0px;
}

img {
	width: 200px;
	height: 200px;
}

.content {
	text-align: center;
	margin: 10px;
	padding: 10px;
}
</style>
