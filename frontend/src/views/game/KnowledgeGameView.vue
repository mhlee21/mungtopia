<template>
	<div class="frame">
		<GameTitle :gameTitle="gameTitle"></GameTitle>
		<div style="text-align: center" v-if="gameType == 0">
			<img src="@/assets/images/knowledge.jpg" alt="" />
		</div>
		<div style="text-align: center" v-else-if="gameType == 1">
			<img src="@/assets/images/mbti.jpg" alt="" />
		</div>
		<div style="text-align: center" v-else>
			<img src="@/assets/images/matching.jpg" alt="" />
		</div>
		<GameDescription :gameDescription="gameDescription"></GameDescription>
		<GameMainButton></GameMainButton>
	</div>
</template>

<script>
import GameMainButton from '@/components/game/GameMainButton.vue';
import GameTitle from '@/components/game/GameTitle.vue';
import GameDescription from '@/components/game/GameDescription.vue';
import { useStore } from 'vuex';
import { computed } from 'vue';

export default {
	components: {
		GameMainButton,
		GameTitle,
		GameDescription,
	},
	setup() {
		const store = useStore();
		let gameTitle = computed(() => store.getters['game/gameTitle']);
		let gameDescription = computed(() => store.getters['game/gameDescription']);
		let gameType = computed(() => store.getters['game/gameType']);
		return { gameTitle, gameDescription, gameType };
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
	font-size: 30px;
}

img {
	width: 200px;
	height: 200px;
}

.content {
	text-align: center;
	margin: 20px;
	padding: 30px;
	font-weight: bolder;
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
	color: #ffffff;
	font-size: 30px;
	font-weight: bolder;
}
</style>
