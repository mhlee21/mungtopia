<template>
	<div class="game-btn">
		<div class="start-btn" @click="answerQuestion">YES</div>
	</div>
	<div class="game-btn">
		<div class="start-btn" @click="answerQuestion">NO</div>
	</div>
</template>

<script>
// import router from '@/router';
import { useStore } from 'vuex';
import router from 'vuex';
import { computed } from 'vue';

export default {
	setup() {
		const store = useStore();
		const questionNumber = computed(() => store.getters['game/questionNumber']);
		const gameType = computed(() => store.getters['game/gameType']);
		const answerQuestion = () => {
			if (questionNumber.value < 10) {
				store.dispatch('game/plusQuestionNumber');
			} else {
				router.push({
					name: 'GameFinish',
					params: { gameType: gameType },
				});
			}
		};
		// const correctAnswer = (userAnswer, questionAnswer) => {
		// 	store.dispatch('game/plusAnswerPoint', userAnswer, questionAnswer);
		// };
		return { answerQuestion };
	},
};
</script>

<style lang="scss" scoped>
.game-btn {
	width: 90px;
	height: 50px;
	background-color: #0d6aba;
	display: flex;
	align-items: center;
	text-align: center;
	padding: 10px;
	margin: 30px auto;
	border-radius: 1rem;
}
.start-btn {
	width: 100%;
	color: #fffbf0;
	font-size: 30px;
}
</style>
