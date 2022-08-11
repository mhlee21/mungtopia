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
			<div class="MATCH-start-btn" @click="plusMatchAnswer(5)">
				{{ gameQuestion[questionNumber]['answer'][0] }}
			</div>
		</div>
		<div class="MATCH-game-btn">
			<div class="MATCH-start-btn" @click="plusMatchAnswer(4)">
				{{ gameQuestion[questionNumber]['answer'][1] }}
			</div>
		</div>
		<div class="MATCH-game-btn">
			<div class="MATCH-start-btn" @click="plusMatchAnswer(3)">
				{{ gameQuestion[questionNumber]['answer'][2] }}
			</div>
		</div>
		<div class="MATCH-game-btn">
			<div class="MATCH-start-btn" @click="plusMatchAnswer(2)">
				{{ gameQuestion[questionNumber]['answer'][3] }}
			</div>
		</div>
		<div class="MATCH-game-btn">
			<div class="MATCH-start-btn" @click="plusMatchAnswer(1)">
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
		const correctAnswer = computed(() => store.getters['game/correctAnswer']);
		const gameType = computed(() => store.getters['game/gameType']);
		const matchNum = computed(() => store.getters['game/matchNum']);
		const answerQuestion = useranswer => {
			if (questionNumber.value < 10) {
				if (useranswer == props.gameQuestion[questionNumber.value]['answer']) {
					store.dispatch('game/plusAnswerPoint');
				}
				store.dispatch('game/plusQuestionNumber');
				store.dispatch('game/updateProgressbar');
				if (questionNumber.value == 10) {
					if (correctAnswer.value >= 7) {
						const payload = {
							userSeq: store.getters['auth/user']['userSeq'],
							result: 1,
							gameType: gameType,
						};
						store.dispatch('game/sendResult', payload);
					}
					router.push({ path: '/game/main/finish' });
				}
			}
			// else {
			// 	router.push({ path: '/game/main/fisish' });
			// }
		};
		const plusMbtiAnswer = userAnswer => {
			store.dispatch('game/plusMbtiAnswer', {
				question_type:
					props.gameQuestion[questionNumber.value]['question_type'],
				userAnswer,
			});
			if (questionNumber.value == 11) {
				const payload = {
					userSeq: store.getters['auth/user']['userSeq'],
					result: 1,
					gameTag: gameType,
				};
				store.dispatch('game/sendResult', payload);
				router.push({
					name: 'MbtiFinish',
					params: { mbtiResult: store.getters['game/mbtiResult'] },
				});
			} else {
				store.dispatch('game/plusQuestionNumber');
				store.dispatch('game/updateProgressbar');
			}
		};

		const plusMatchAnswer = userAnswer => {
			store.dispatch('game/plusMatchAnswer', {
				question_type:
					props.gameQuestion[questionNumber.value]['question_type'],
				userAnswer,
			});
			if (questionNumber.value == 17) {
				const payload = {
					// userSeq: store.getters['auth/user']['userSeq'],
					userSeq: 1,
					matchAnswer: matchNum,
					gameTag: gameType,
				};
				store.dispatch('game/sendResult', payload);
				router.push({
					name: 'MatchFinish',
					params: { mbtiResult: store.getters['game/MatchFinish'] },
				});
			} else {
				store.dispatch('game/plusQuestionNumber');
				store.dispatch('game/updateProgressbar');
			}
		};
		return {
			questionNumber,
			answerQuestion,
			gameType,
			plusMbtiAnswer,
			plusMatchAnswer,
		};
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
