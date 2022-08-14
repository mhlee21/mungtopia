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
				v-model="inputText2"
				input="updateInput"
				type="text"
				placeholder="답변을 입력하세요."
				style="width: 250px; height: 200px; font-size: 30px; margin: 20px"
			/>
		</div>
		<div class="game-btn" v-if="questionCount > 0" @click="minustQuestion">
			<div class="start-btn">BEFORE</div>
		</div>
		<div class="game-btn" v-if="questionCount < 14" @click="plustQuestion">
			<div class="start-btn">NEXT</div>
		</div>
		<div class="game-btn" v-else>
			<div class="start-btn">제출</div>
		</div>
	</div>
</template>

<script>
import { useStore } from 'vuex';
import { computed, ref } from 'vue';
export default {
	setup() {
		const store = useStore();
		store.dispatch('board/setAdoptQ');
		let inputText2 = ref('a');
		const updateText = event => {
			let updatedText = event.target.value;
			inputText2 = updatedText;
			console.log(inputText2);
		};
		const adoptQuestionList = computed(
			() => store.getters['board/adoptQuestionList'],
		);
		const questionCount = computed(() => store.getters['board/questionCount']);
		const plustQuestion = () => {
			store.dispatch('board/plusQuestionCount');
			console.log(adoptQuestionList.value);
		};

		const minustQuestion = () => {
			store.dispatch('board/minusQuestionCount');
		};
		return {
			adoptQuestionList,
			questionCount,
			plustQuestion,
			minustQuestion,
			inputText2,
			updateText,
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
