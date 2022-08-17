<template>
	<div class="game-play-pg">
		<div class="game-header game-header3">
			<div class="game-controller">
				<div @click="clickBackButton">{{ `< Back` }}</div>
				<div>
					<b>{{ qNum }}</b>
					{{ ` of 10` }}
				</div>
				<div @click="clickX">X</div>
			</div>
			<div class="game-status-wrapper">
				<div class="game-status-bar">
					<div
						class="game-status-bar game-status-bar-inner3"
						:style="{ width: (qNum / 10) * 100 + '%' }"
					></div>
				</div>
			</div>
		</div>
		<!-- 카드 -->
		<div class="game-body2">
			<div
				class="card box"
				v-for="(question, index) in questionList"
				:key="index"
				v-show="qNum - 1 === index"
			>
				<div class="question-wrapper">{{ question['question'] }}</div>
				<div class="answer-wrapper">
					<div class="answer-box" @click.stop="clickAnswerButton(true)">O</div>
					<div class="answer-box" @click.stop="clickAnswerButton(false)">X</div>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
import Qdata from '@/assets/Qdata.json';
import { useRouter } from 'vue-router';
import { ref, computed } from 'vue';
import { useStore } from 'vuex';
export default {
	setup() {
		const qNum = ref(1);
		const store = useStore();
		const router = useRouter();
		const user = computed(() => store.getters['auth/user']);

		// 게임 정답 저장
		const shuffle = () => Math.random() - 0.5;
		const knowledge = computed(() => Qdata?.knowledge);
		const questionList = [...knowledge.value].sort(shuffle).slice(0, 10);
		// 답 선택
		const answer = Array(questionList?.length).fill('');
		const rightAnswer = idx => questionList[idx]['answer'];
		const clickAnswerButton = a => {
			answer[qNum.value - 1] = a;
			// 마지막 문제가 아닐때
			if (qNum.value < questionList.length) {
				qNum.value++;
			} else {
				// 마지막 문제일 때
				// knowledgeNum 결과 저장
				let score = 0;
				for (let idx = 0; idx < questionList?.length; idx++) {
					if (answer[idx] === rightAnswer(idx)) {
						score++;
					}
				}
				if (user.value) {
					const payload = {
						userSeq: user.value?.userSeq,
						result: score,
						gameTag: 1,
					};
					store.dispatch('game/sendResult', payload);
				}
				router.push({
					name: 'game1Result',
					params: { score: score },
				});
			}
		};
		// 뒤로가기 버튼
		const clickBackButton = () => {
			if (qNum.value === 1) {
				router.push({
					name: 'gameIntro',
					params: { gameTag: 1 },
				});
			}
			qNum.value--;
		};
		// 나가기 버튼
		const clickX = () => {
			router.push({ name: 'game' });
		};
		return {
			qNum,
			knowledge,
			clickBackButton,
			clickAnswerButton,
			clickX,
			questionList,
		};
	},
};
</script>

<style lang="scss" scoped>
.box {
	animation: fadein 0.5s;
}
@keyframes fadein {
	/* 효과를 동작시간 동안 0 ~ 1까지 */
	from {
		opacity: 0;
		width: 80vw;
		height: 60vh;
		background-color: #f4f4f4;
		font-size: 18px;
	}
	to {
		opacity: 1;
		width: 90vw;
		height: 70vh;
		background-color: white;
		font-size: 20px;
	}
}
@keyframes fadeout {
	/* 효과를 동작시간 동안 0 ~ 1까지 */
	from {
		opacity: 1;
	}
	to {
		opacity: 0;
	}
}
</style>
