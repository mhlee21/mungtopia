<template>
	<div>
		<h2 class="content">
			Q{{ questionCount + 1 }}.<br />{{
				adoptQuestionList[questionCount]['question']
			}}
		</h2>
	</div>
	<div>
		<div>
			<input
				v-model="inputText"
				input="updateInput"
				type="text"
				placeholder="답변을 입력하세요."
				style="width: 250px; height: 200px; font-size: 30px; margin: 20px"
			/>
		</div>
		<div class="game-btn" v-if="questionCount > 0" @click="minusQuestion">
			<div class="start-btn">BEFORE</div>
		</div>
		<div class="game-btn" v-if="questionCount < 14" @click="plusQuestion">
			<div class="start-btn">NEXT</div>
		</div>
		<div class="game-btn" v-else @click="submitApplicationAnswer">
			<div class="start-btn">제출</div>
		</div>
	</div>
</template>

<script>
import { useStore } from 'vuex';
import { computed, ref } from 'vue';
import { useRoute } from 'vue-router';
import router from '@/router';
export default {
	setup() {
		const route = useRoute(0);
		const store = useStore();
		const boardId = computed(() => route.params.boardId);
		store.dispatch('board/setAdoptQ');
		var answerList = Array(15).fill('');
		const questionCount = computed(() => store.getters['board/questionCount']);
		let inputText = ref(`${answerList[questionCount.value]}`);
		const submitApplicationAnswer = () => {
			const payload = {
				userSeq: store.getters['auth/user']['userSeq'],
				applicantAnswerList: answerList,
			};
			store.dispatch('board/createApplication', {
				payload: payload,
				boardId: boardId.value,
			});
			router.push({
				name: 'boardDetail',
				params: { boardId: boardId.value },
			});
		};
		// const updateText = event => {
		// 	let updatedText = event.target.value;
		// 	inputText = updatedText;
		// };
		const adoptQuestionList = computed(
			() => store.getters['board/adoptQuestionList'],
		);
		const plusQuestion = () => {
			answerList[questionCount.value] = inputText.value;
			store.dispatch('board/plusQuestionCount');
			console.log(answerList);
			inputText.value = answerList[questionCount.value];
		};

		const minusQuestion = () => {
			store.dispatch('board/minusQuestionCount');
			inputText.value = answerList[questionCount.value];
		};
		return {
			adoptQuestionList,
			questionCount,
			plusQuestion,
			minusQuestion,
			inputText,
			answerList,
			submitApplicationAnswer,
		};
	},
};
</script>

<style lang="scss" scoped>
.content {
	text-align: center;
	margin: 10px;
	padding: 10px;
	font-size: 20px;
}

.title {
	text-align: center;
	margin: 20px;
	padding: 10px;
	font-size: 30px;
	color: #ffffff;
}
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
	margin: 5px auto;
	border-radius: 1rem;
}
.start-btn {
	width: 100%;
	color: #fffbf0;
	font-size: 20px;
	font-weight: bolder;
}
</style>
