<template>
	<div class="game-btn">
		<div class="start-btn" @click="gameStart(gameType)">START</div>
	</div>
	<div class="game-btn">
		<div class="start-btn" @click="gameBack">BACK</div>
	</div>
</template>

<script>
import { useStore } from 'vuex';
import router from '@/router';

export default {
	setup() {
		const store = useStore();
		const gameType = store.getters['game/gameType'];
		const gameStart = gameType => {
			store.dispatch('game/solveGame', gameType);
			router.push({
				name: 'GamePlay',
				params: { gameType: gameType },
			});
		};
		const gameBack = () => {
			router.push({ path: '/game' });
		};
		return { gameType, gameStart, gameBack };
	},
};
</script>

<style lang="scss" scoped>
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
