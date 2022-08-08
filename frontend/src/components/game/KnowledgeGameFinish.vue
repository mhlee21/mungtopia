<template>
	<div v-if="correctAnswer >= 7">
		<div class="frame">
			<div>
				<h2 class="title">SUCCESS</h2>
			</div>
			<div style="text-align: center">
				<img src="" alt="" />
			</div>
			<div class="content">
				<div>10문제 중 {{ correctAnswer }}문제를 맞춰 성공하였어요!</div>
			</div>
			<div class="game-btn">
				<div class="start-btn">SHARE</div>
			</div>
			<div class="game-btn">
				<div class="start-btn">GAME MENU</div>
			</div>
		</div>
	</div>
	<div v-else>
		<div class="frame">
			<div>
				<h2 class="title">Fail</h2>
			</div>
			<div style="text-align: center">
				<img src="" alt="" />
			</div>
			<div class="content">
				<div>10문제 중 {{ correctAnswer }}문제를 맞추었어요ㅠ</div>
			</div>
			<div class="game-btn">
				<div class="start-btn">REPLAY</div>
			</div>
			<div class="game-btn">
				<div class="start-btn">GAME MENU</div>
			</div>
		</div>
	</div>
</template>

<script>
import { useStore } from 'vuex';
import { computed } from 'vue';

export default {
	setup() {
		const store = useStore();
		const correctAnswer = computed(() => store.getters['game/correctAnswer']);
		return { correctAnswer };
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
	color: #000000;
	padding: 10px;
	font-size: 50px;
}

img {
	width: 200px;
	height: 200px;
}

.content {
	text-align: center;
	margin: 20px;
	padding: 30px;
	font-weight: bold;
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
