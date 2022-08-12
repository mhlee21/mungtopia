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
		<div
			class="bulgy-radios"
			role="radiogroup"
			aria-labelledby="bulgy-radios-label"
		>
			<h2 id="bulgy-radios-label">Select an option</h2>
			<label>
				<input type="radio" name="options" checked />
				<span class="radio"></span>
				<span class="label">First option</span>
			</label>
			<label>
				<input type="radio" name="options" />
				<span class="radio"></span>
				<span class="label">Second option</span>
			</label>
			<label>
				<input type="radio" name="options" />
				<span class="radio"></span>
				<span class="label">Third option</span>
			</label>
			<label>
				<input type="radio" name="options" />
				<span class="radio"></span>
				<span class="label">Fourth option</span>
			</label>
			<label>
				<input type="radio" name="options" />
				<span class="radio"></span>
				<span class="label">Last option</span>
			</label>
		</div>
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
				if (questionNumber.value == 10) {
					if (correctAnswer.value >= 7) {
						const payload = {
							userSeq: store.getters['auth/user']['userSeq'],
							result: 1,
							gameType: gameType,
						};
						store.dispatch('game/sendResult', payload);
					}
					router.push({ path: '/game/0/finish' });
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
				console.log(JSON.stringify(payload));
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

.bulgy-radios {
	label {
		align-content: center;
		display: flex;
		flex-direction: column;
		flex-wrap: wrap;
		position: relative;
		height: 1em;
		padding-left: 4rem;
		margin-bottom: 1.75rem;
		cursor: pointer;
		font-size: 2.5rem;
		user-select: none;
		color: #555;
		letter-spacing: 1px;
		&:hover input:not(:checked) ~ .radio {
			opacity: 0.8;
		}
	}
	.label {
		display: flex;
		align-items: center;
		padding-right: 3rem;
		span {
			line-height: 1em;
		}
	}
	matching-input {
		position: absolute;
		cursor: pointer;
		height: 0;
		width: 0;
		left: -2000px;

		&:checked {
			~ .radio {
				background-color: #0ac07d;
				transition: background 0.3s;
				&::after {
					opacity: 1;
				}
			}
			~ .label {
				color: #0bae72;
				span {
					animation: bulge 0.5s forwards;
				}

				//adjust this if label is going to be more than 20 chars
				@for $i from 1 to 20 {
					span:nth-child(#{$i}) {
						animation-delay: $i * 0.025s;
					}
				}
			}
		}
	}
}
.radio {
	position: absolute;
	top: 0.2rem;
	left: 0;
	height: 2.5rem;
	width: 2.5rem;
	background: #c9ded6;
	border-radius: 50%;
	&::after {
		content: '';
		position: absolute;
		opacity: 0;
		top: 0.5rem;
		left: 0.5rem;
		width: 1.5rem;
		height: 1.5rem;
		border-radius: 50%;
		background: #fff;
	}
}
@keyframes bulge {
	50% {
		transform: rotate(4deg);
		font-size: 1.5em;
		font-weight: bold;
	}
	100% {
		transform: rotate(0);
		font-size: 1em;
		font-weight: bold;
	}
}
</style>
