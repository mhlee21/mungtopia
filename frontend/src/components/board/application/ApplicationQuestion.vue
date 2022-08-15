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
			placeholder="답변을 입력하세요"
			class="question-answer"
			autofocus
			maxlength="300"
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
				완료
			</button>
		</div>
	</div>
</template>

<script>
import { useStore } from 'vuex';
import { computed, ref } from 'vue';
import { useRouter } from 'vue-router';

export default {
	setup() {
		const store = useStore();
		const router = useRouter();
		store.dispatch('board/setAdoptQ');
		var application = Array(15).fill('');
		const questionCount = ref(0);
		let inputText = ref(`${application[questionCount.value]}`);

		// 입양신청서 작성 완료
		const submitApplicationAnswer = () => {
			application[questionCount.value] = inputText.value;
			store.dispatch('board/saveApplication', application);
			router.go(-1);
		};
		const adoptQuestionList = computed(
			() => store.getters['board/adoptQuestionList'],
		);

		const plusQuestion = () => {
			application[questionCount.value] = inputText.value;
			questionCount.value++;
			inputText.value = application[questionCount.value];
		};

		const minusQuestion = () => {
			questionCount.value--;
			inputText.value = application[questionCount.value];
		};
		const barWidth = computed(() => ((questionCount.value + 1) / 15) * 100);
		return {
			adoptQuestionList,
			questionCount,
			barWidth,
			plusQuestion,
			minusQuestion,
			inputText,
			application,
			submitApplicationAnswer,
		};
	},
};
</script>

<style lang="scss" scoped></style>
