<template>
	<div class="frame">
		<div class="status">
			<GamePlayStatus></GamePlayStatus>
		</div>
		<div style="text-align: center" v-if="gameType == 0">
			<img
				:src="
					require('@/assets/images/KNOWLEDGE/' +
						'knowledge_' +
						questionNumber +
						'.jpg')
				"
				width="150"
				height="150"
				alt=""
			/>
		</div>
		<div style="text-align: center" v-else-if="gameType == 1">
			<img
				:src="
					require('@/assets/images/MBTI/' + 'MBTI_' + questionNumber + '.jpg')
				"
				width="150"
				height="150"
				alt=""
			/>
		</div>
		<div style="text-align: center" v-else>
			<img
				:src="
					require('@/assets/images/MATCH/' + 'match_' + questionNumber + '.jpg')
				"
				width="150"
				height="150"
				alt=""
			/>
		</div>
		<GameQuestion :gameQuestion="gameQuestion"></GameQuestion>
	</div>
</template>

<script>
// import Qdata from '@/assets/Qdata.json';
import GameQuestion from '@/components/game/GameQuestion.vue';
import { useStore } from 'vuex';
import { computed } from 'vue';
import GamePlayStatus from '@/components/game/GamePlayStatus.vue';

export default {
	components: {
		GameQuestion,
		GamePlayStatus,
	},
	setup() {
		const store = useStore();

		let questionNumber = computed(() => store.getters['game/questionNumber']);
		let gameType = computed(() => store.getters['game/gameType']);
		let gameQuestion = computed(() => store.getters['game/gameQuestion']);
		console.log(gameQuestion);
		return { gameQuestion, questionNumber, gameType };
	},
};
</script>

<style lang="scss" scoped>
.frame {
	width: 100vw;
	height: 100vh;
	background-color: #fffbf0;
	border-radius: 1rem;
	box-sizing: border-box;
	position: relative;
	padding-top: 50px;
	flex-direction: column;
}
.title {
	text-align: center;
	margin: 20px;
	color: #0d6aba;
	padding: 10px;
}
.status {
	text-align: center;
	margin: 0px;
	color: #0d6aba;
	padding: 0px;
	display: flex;
	justify-content: center;
	margin-bottom: 30px;
}

img {
	width: 200px;
	height: 200px;
	border-radius: 1rem;
}

.content {
	text-align: center;
	margin: 10px;
	padding: 10px;
}
</style>
