<template>
	<div class="frame">
		<div class="status">
			<h3>상태창</h3>
		</div>
		<div>
			<h2 class="content">Q1. 강아지의 털이 빠진다면?</h2>
		</div>
		<div style="text-align: center">
			<img src="" alt="" />
		</div>
		<div class="game-btn">
			<div class="start-btn">상관 없다.</div>
		</div>
		<div class="game-btn">
			<div class="start-btn">어느 정도는 괜찮다.</div>
		</div>
		<div class="game-btn">
			<div class="start-btn">너무 불편할 것 같다</div>
		</div>
	</div>
</template>

<script>
export default {
	setup() {
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
	margin: 5px;
	padding: 5px;
}
.game-btn {
	width: 90px;
	height: 50px;
	background-color: #0d6aba;
	display: flex;
	align-items: center;
	text-align: center;
	padding: 10px;
	margin: 30px auto;
	border-radius: 1rem;
}
.game-btn {
	width: 200px;
	height: 50px;
	background-color: #0d6aba;
	display: flex;
	align-items: center;
	text-align: center;
	padding: 10px;
	margin: 30px auto;
	border-radius: 1rem;
}
.start-btn {
	width: 100%;
	color: #fffbf0;
}
</style>
