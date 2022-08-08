<template>
	<div>
		<h2 class="content">{{ gameQuestion[questionNumber]['question'] }}</h2>
	</div>
	<div v-if="gameType == 0">
		<div class="game-btn">
			<div class="start-btn" @click="answerQuestion(true)">YES</div>
		</div>
		<div class="game-btn">
			<div class="start-btn" @click="answerQuestion(false)">NO</div>
		</div>
	</div>
	<div v-else-if="gameType == 1">
		<div class="MBTI-game-btn">
			<div class="MBTI-start-btn" @click="plusMbtiAnswer(0)">
				{{ gameQuestion[questionNumber]['answer'][0] }}
			</div>
		</div>
		<div class="MBTI-game-btn">
			<div class="MBTI-start-btn" @click="plusMbtiAnswer(1)">
				{{ gameQuestion[questionNumber]['answer'][1] }}
			</div>
		</div>
	</div>
	<div v-else>
		<div class="MATCH-game-btn">
			<div class="MATCH-start-btn" @click="plusMbtiAnswer(0)">
				{{ gameQuestion[questionNumber]['answer'][0] }}
			</div>
		</div>
		<div class="MATCH-game-btn">
			<div class="MATCH-start-btn" @click="plusMbtiAnswer(1)">
				{{ gameQuestion[questionNumber]['answer'][1] }}
			</div>
		</div>
		<div class="MATCH-game-btn">
			<div class="MATCH-start-btn" @click="plusMbtiAnswer(2)">
				{{ gameQuestion[questionNumber]['answer'][2] }}
			</div>
		</div>
		<div class="MATCH-game-btn">
			<div class="MATCH-start-btn" @click="plusMbtiAnswer(3)">
				{{ gameQuestion[questionNumber]['answer'][3] }}
			</div>
		</div>
		<div class="MATCH-game-btn">
			<div class="MATCH-start-btn" @click="plusMbtiAnswer(4)">
				{{ gameQuestion[questionNumber]['answer'][4] }}
			</div>
		</div>
	</div>
</template>

<script>
import { useStore } from 'vuex';
import { computed } from 'vue';
import router from '@/router';

export default {
	props: { gameQuestion: Object },
	setup(props) {
		const store = useStore();
		const questionNumber = computed(() => store.getters['game/questionNumber']);
		const gameType = computed(() => store.getters['game/gameType']);
		const answerQuestion = useranswer => {
			if (questionNumber.value < 10) {
				if (useranswer == props.gameQuestion[questionNumber.value]['answer']) {
					store.dispatch('game/plusAnswerPoint');
				}
				store.dispatch('game/plusQuestionNumber');
				if (questionNumber.value == 10) {
					router.push({ path: '/game/main/finish' });
				}
			} else {
				router.push({ path: '/game/main/fisish' });
			}
		};
		const plusMbtiAnswer = userAnswer => {
			store.dispatch('game/plusMbtiAnswer', {
				question_type:
					props.gameQuestion[questionNumber.value]['question_type'],
				userAnswer,
			});
			if (questionNumber.value == 11) {
				store.dispatch('game/MbtiDogResult');
				router.push({ path: '/game/2/finish' });
			} else {
				store.dispatch('game/plusQuestionNumber');
			}
		};
		return { questionNumber, answerQuestion, gameType, plusMbtiAnswer };
	},
};
</script>

<style lang="scss" scoped>
.content {
	text-align: center;
	margin: 10px;
	padding: 10px;
}

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

.MBTI-game-btn {
	width: 200px;
	height: 50px;
	background-color: #0d6aba;
	display: flex;
	align-items: center;
	text-align: center;
	padding: 10px;
	margin: 30px auto;
	border-radius: 1rem;
}
.MBTI-start-btn {
	width: 100%;
	color: #fffbf0;
}
.MATCH-game-btn {
	width: 100px;
	height: 30px;
	background-color: #0d6aba;
	display: flex;
	align-items: center;
	text-align: center;
	padding: 5px;
	margin: 30px auto;
	border-radius: 1rem;
}
.MATCH-start-btn {
	width: 100%;
	color: #fffbf0;
}
</style>
