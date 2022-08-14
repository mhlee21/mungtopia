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
		<button
			class="w-btn w-btn-indigo"
			type="button"
			@click="plusMatchAnswer(5)"
		>
			매우 그렇다
		</button>
		<button class="w-btn w-btn-green" type="button" @click="plusMatchAnswer(4)">
			그렇다
		</button>
		<button
			class="w-btn w-btn-green2"
			type="button"
			@click="plusMatchAnswer(3)"
		>
			보통이다
		</button>
		<button class="w-btn w-btn-brown" type="button" @click="plusMatchAnswer(2)">
			아니다
		</button>
		<button class="w-btn w-btn-blue" type="button" @click="plusMatchAnswer(1)">
			매우 아니다
		</button>
	</div>

	<!-- <div class="MATCH-game-btn">
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
	</div> -->
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
				if (questionNumber.value > 9) {
					if (correctAnswer.value >= 7) {
						const payload = {
							userSeq: store.getters['auth/user']['userSeq'],
							result: 1,
							gameType: gameType.value,
						};
						store.dispatch('game/sendResult', payload);
					}
					router.push({ path: '/game/0/finish' });
				}
			} else {
				router.push({ path: '/game/0/finish' });
			}
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
					gameTag: gameType.value,
				};
				console.log(payload);
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
				if (store.getters['auth/user']['userSeq']) {
					const payload = {
						userSeq: store.getters['auth/user']['userSeq'],
						// userSeq: 6,
						matchAnswer: matchNum.value,
						gameTag: gameType.value,
					};
					store.dispatch('game/sendMatchResult', payload);
				}
				router.push({
					name: 'MatchFinish',
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

@import url('https://fonts.googleapis.com/css?family=Poppins:200,300,400,500,600,700,800,900&display=swap');

* {
	margin: 0;
	padding: 0;
	box-sizing: border-box;
	display: grid;
	align-content: center;
	justify-content: space-around;
}

body {
	text-align: center;
	padding: 100px;
	background: whitesmoke;
	display: table-cell;
}

button {
	margin: 5px;
}

.w-btn {
	position: relative;
	border: none;
	display: inline-block;
	padding: 15px 30px;
	border-radius: 15px;
	font-family: 'paybooc-Light', sans-serif;
	box-shadow: 0 15px 35px rgba(0, 0, 0, 0.2);
	text-decoration: none;
	font-weight: 600;
	transition: 0.25s;
}

.w-btn-outline {
	position: relative;
	padding: 15px 30px;
	border-radius: 15px;
	font-family: 'paybooc-Light', sans-serif;
	box-shadow: 0 15px 35px rgba(0, 0, 0, 0.2);
	text-decoration: none;
	font-weight: 600;
	transition: 0.25s;
}

@import url('https://fonts.googleapis.com/css?family=Poppins:200,300,400,500,600,700,800,900&display=swap');

* {
	margin: 0;
	padding: 0;
	box-sizing: border-box;
}

body {
	text-align: center;
	padding: 100px;
	background: whitesmoke;
	display: table-cell;
}

button {
	margin: 10px;
}

.w-btn {
	position: relative;
	border: none;
	display: inline-block;
	padding: 15px 30px;
	border-radius: 15px;
	font-family: 'paybooc-Light', sans-serif;
	box-shadow: 0 15px 35px rgba(0, 0, 0, 0.2);
	text-decoration: none;
	font-weight: 600;
	transition: 0.25s;
}

.w-btn-outline {
	position: relative;
	padding: 15px 30px;
	border-radius: 15px;
	font-family: 'paybooc-Light', sans-serif;
	box-shadow: 0 15px 35px rgba(0, 0, 0, 0.2);
	text-decoration: none;
	font-weight: 600;
	transition: 0.25s;
}

.w-btn-indigo {
	background-color: aliceblue;
	color: #1e6b7b;
}

.w-btn-indigo-outline {
	border: 3px solid aliceblue;
	color: #1e6b7b;
}

.w-btn-indigo-outline:hover {
	color: #1e6b7b;
	background: aliceblue;
}

.w-btn-green {
	background-color: #77af9c;
	color: #d7fff1;
}

.w-btn-green2 {
	background-color: #519d9e;
	color: #9dc8c8;
}

.w-btn-green-outline {
	border: 3px solid #77af9c;
	color: darkgray;
}

.w-btn-green2-outline {
	border: 3px solid #519d9e;
	color: darkgray;
}

.w-btn-green-outline:hover {
	background-color: #77af9c;
	color: #d7fff1;
}

.w-btn-green2-outline:hover {
	background-color: #519d9e;
	color: #9dc8c8;
}

.w-btn-brown {
	background-color: #ce6d39;
	color: #ffeee4;
}

.w-btn-brown-outline {
	border: 3px solid #ce6d39;
	color: #b8b8b8;
}

.w-btn-brown-outline:hover {
	background-color: #ce6d39;
	color: #ffeee4;
}

.w-btn-blue {
	background-color: #6aafe6;
	color: #d4dfe6;
}

.w-btn:active {
	transform: scale(1.2);
}

.w-btn-outline:active {
	transform: scale(1.2);
	animation-fill-mode: backwards;
}

.w-btn-gra1 {
	background: linear-gradient(-45deg, #33ccff 0%, #ff99cc 100%);
	color: white;
}

.w-btn-gra2 {
	background: linear-gradient(-45deg, #ee7752, #e73c7e, #23a6d5, #23d5ab);
	color: white;
}

.w-btn-gra3 {
	background: linear-gradient(
		45deg,
		#ff0000,
		#ff7300,
		#fffb00,
		#48ff00,
		#00ffd5,
		#002bff,
		#7a00ff,
		#ff00c8,
		#ff0000
	);
	color: white;
}

.w-btn-gra-anim {
	background-size: 400% 400%;
	animation: gradient1 5s ease infinite;
}

@keyframes gradient1 {
	0% {
		background-position: 0% 50%;
	}
	50% {
		background-position: 100% 50%;
	}
	100% {
		background-position: 0% 50%;
	}
}

@keyframes gradient2 {
	0% {
		background-position: 100% 50%;
	}
	50% {
		background-position: 0% 50%;
	}
	100% {
		background-position: 100% 50%;
	}
}

@keyframes ring {
	0% {
		width: 30px;
		height: 30px;
		opacity: 1;
	}
	100% {
		width: 300px;
		height: 300px;
		opacity: 0;
	}
}

.w-btn-neon2 {
	position: relative;
	border: none;
	min-width: 200px;
	min-height: 50px;
	background: linear-gradient(
		90deg,
		rgba(129, 230, 217, 1) 0%,
		rgba(79, 209, 197, 1) 100%
	);
	border-radius: 1000px;
	color: darkslategray;
	cursor: pointer;
	box-shadow: 12px 12px 24px rgba(79, 209, 197, 0.64);
	font-weight: 700;
	transition: 0.3s;
}

.w-btn-neon2:hover {
	transform: scale(1.2);
}

.w-btn-neon2:hover::after {
	content: '';
	width: 30px;
	height: 30px;
	border-radius: 100%;
	border: 6px solid #00ffcb;
	position: absolute;
	z-index: -1;
	top: 50%;
	left: 50%;
	transform: translate(-50%, -50%);
	animation: ring 1.5s infinite;
}
</style>
