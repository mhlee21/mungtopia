<template>
	<div class="frame">
		<!-- 제목 -->
		<div>
			<h2 class="title">멍토피아</h2>
		</div>
		<!-- 게임 리스트 -->
		<div class="game-list">
			<div class="game-btn" @click="gameEnter(0)">
				<img src="" alt="" />
				<div class="game-title">강아지식 테스트</div>
			</div>
			<div class="game-btn" @click="gameEnter(1)">
				<img src="" alt="" />
				<div class="game-title">댕BTI</div>
			</div>
			<div class="game-btn" @click="gameEnter(2)">
				<img src="" alt="" />
				<div class="game-title">나와 잘맞는 반려견은?</div>
			</div>
		</div>
		<!-- 네브바 -->
		<div class="navbar">
			<button>홈</button>
			<button>게임</button>
			<button>추가</button>
			<button>이력</button>
			<button>프로필</button>
		</div>
	</div>
</template>

<script>
import { useStore } from 'vuex';
import router from '@/router';

export default {
	setup() {
		const store = useStore();
		const gameEnter = gameType => {
			store.dispatch('game/enterGame', gameType);
			router.push({ path: '/game/main' });
		};
		return { gameEnter };
	},
};
</script>

<style lang="scss" scoped>
.game-list {
	display: flex;
	align-content: space-between;
	flex-wrap: wrap;
}
.game-btn {
	width: 280px;
	height: 140px;
	background-color: #fffbf0;
	display: flex;
	align-items: center;
	text-align: center;
	padding: 10px;
	margin: 5px auto;
}
.game-title {
	width: 70%;
}
.frame {
	width: 350px;
	height: 640px;
	background-color: #ff9898;
	border: black solid 1px;
	border-radius: 1rem;
	position: relative;
}
img {
	width: 130px;
	height: 130px;
}
.title {
	text-align: center;
	margin: 0;
	padding: 10px;
}
.navbar {
	display: flex;
	justify-content: space-evenly;
	background-color: white;
	padding: 15px 0;
	position: absolute;
	bottom: 0;
	width: 100%;
	border-radius: 0 0 1rem 1rem;
}
</style>
