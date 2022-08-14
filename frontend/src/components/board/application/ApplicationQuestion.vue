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
			v-model="inputText"
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
				@click="minusQuestion"
			>
				이전
			</button>
			<button
				class="question-btn"
				v-if="questionCount < 14"
				@click="plusQuestion"
			>
				다음
			</button>
			<button class="question-btn" v-else @click="submitApplicationAnswer">
				제출
			</button>
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
		const route = useRoute();
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
		const adoptQuestionList = computed(
			() => store.getters['board/adoptQuestionList'],
		);
		const plusQuestion = () => {
			answerList[questionCount.value] = inputText.value;
			store.dispatch('board/plusQuestionCount');
			inputText.value = answerList[questionCount.value];
		};

		const minusQuestion = () => {
			store.dispatch('board/minusQuestionCount');
			inputText.value = answerList[questionCount.value];
		};
		const barWidth = computed(() => ((questionCount.value + 1) / 15) * 100);
		return {
			adoptQuestionList,
			questionCount,
			barWidth,
			plusQuestion,
			minusQuestion,
			inputText,
			answerList,
			submitApplicationAnswer,
		};
	},
};
</script>

<style lang="scss" scoped></style>
