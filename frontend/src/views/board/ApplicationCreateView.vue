<template>
	<div class="frame">
		<div class="title">입양신청서</div>
		<div class="Subframe">
			<ApplicationQuestion></ApplicationQuestion>
			<input
				type="text"
				placeholder="답변을 입력하세요."
				style="width: 250px; height: 200px; font-size: 30px; margin: 20px"
			/>
		</div>
		<div class="game-btn" v-if="questionCount > 0" @click="plustQuestion">
			<div class="start-btn">BEFORE</div>
		</div>
		<div class="game-btn" v-if="questionCount < 15" @click="minustQuestion">
			<div class="start-btn">NEXT</div>
		</div>
		<div class="game-btn" v-else>
			<div class="start-btn">제출</div>
		</div>
	</div>
</template>

<script>
import { useStore } from 'vuex';
import { computed } from 'vue';
import ApplicationQuestion from '@/components/board/application/ApplicationQuestion.vue';
// import { computed } from '@vue/reactivity';

export default {
	components: {
		ApplicationQuestion,
	},
	setup() {
		const store = useStore();
		const questionCount = computed(() => store.getters['game/questionCount']);
		const plustQuestion = () => {
			store.dispatch('board/plusQuestionNumber');
		};
		const minustQuestion = () => {
			store.dispatch('board/minusQuestionNumber');
		};
		return { questionCount, plustQuestion, minustQuestion };
	},
};
</script>

<style lang="scss" scoped>
.frame {
	width: 350px;
	height: 640px;
	background-color: #ff9898;
	border: black solid 1px;
	border-radius: 1rem;
	position: relative;
}
.Subframe {
	width: 300px;
	background-color: #ffffff;
	border: black solid 1px;
	border-radius: 1rem;
	position: relative;
	text-align: center;
	margin: 20px 25px 20px 25px;
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
