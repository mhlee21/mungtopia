<template>
	<div class="question-status">
		<div class="status-bar-wrapper">
			<div class="status-bar">
				<div class="status-bar-inner" :style="{ width: barWidth + '%' }"></div>
			</div>
		</div>
		<div class="status-bar-counter-wrapper">{{ questionCount + 1 }}/15</div>
	</div>
	<div class="question-wrapper">
		<!-- 문제 상단 -->
		<div class="question-header">
			<!-- 문제 번호 -->
			<div class="question-num-wrapper">
				<div class="question-num">
					<span v-if="questionCount < 9">0</span>{{ questionCount + 1 }}
				</div>
			</div>
			<!-- 질문 -->
			<div class="question">
				{{ adoptQuestionList[questionCount]['question'] }}
			</div>
		</div>

		<!-- 문제 답변 -->
		<textarea
			v-model="inputText2"
			name=""
			id=""
			cols="30"
			rows="10"
			placeholder="답변을 입력하세요"
			class="question-answer"
		></textarea>

		<!-- 문제 버튼 -->
		<div class="question-btn-wrapper">
			<button
				class="question-btn"
				v-if="questionCount > 0"
				@click="minustQuestion"
			>
				이전
			</button>
			<button
				class="question-btn"
				v-if="questionCount < 14"
				@click="plustQuestion"
			>
				다음
			</button>
			<button class="question-btn" v-else>제출</button>
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
		let inputText2 = ref('');
		const updateText = event => {
			let updatedText = event.target.value;
			inputText2 = updatedText;
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
		const barWidth = computed(() => ((questionCount.value + 1) / 15) * 100);
		console.log(barWidth.value);
		return {
			adoptQuestionList,
			questionCount,
			plustQuestion,
			minustQuestion,
			inputText2,
			updateText,
			barWidth,
		};
	},
};
</script>

<style lang="scss" scoped></style>
