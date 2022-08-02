<template>
	<div class="frame">
		<div class="status">
			<h3>상태창</h3>
		</div>
		<GameQuestion></GameQuestion>
		<div style="text-align: center">
			<img src="" alt="" />
		</div>
		<GameZicoButton></GameZicoButton>
	</div>
</template>

<script>
import GameZicoButton from '@/components/game/GameZicoButton.vue';
// import Qdata from '@/assets/Qdata.json';
import GameQuestion from '@/components/game/GameQuestion.vue';

export default {
	components: {
		GameZicoButton,
		GameQuestion,
	},
	setup() {
		// const Qlist = Qdata
		console.log(gameType);
		return {};
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
}
.status {
	text-align: center;
	margin: 0px;
	color: #0d6aba;
	padding: 0px;
}

img {
	width: 200px;
	height: 200px;
}

.content {
	text-align: center;
	margin: 10px;
	padding: 10px;
}
</style>
