<template>
	<div class="frame">
		<GameTitle :gameTitle="gameTitle"></GameTitle>
		<div style="text-align: center">
			<img src="" alt="" />
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
		return { gameTitle, gameDescription };
	},
};
</script>

<style lang="scss" scoped>
.frame {
	width: 350px;
	height: 640px;
	background-color: #fffbf0;
	border: black solid 1px;
	border-radius: 1rem;
	position: relative;
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
