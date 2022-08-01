<template>
	<div class="game-btn">
		<div class="start-btn">START</div>
	</div>
	<div class="game-btn">
		<div class="start-btn">RANK</div>
	</div>
	<div class="game-btn">
		<div class="start-btn">BACK</div>
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
.game-btn {
	width: 90px;
	height: 50px;
	background-color: #f2cd5c;
	display: flex;
	align-items: center;
	text-align: center;
	padding: 10px;
	margin: 20px auto;
	border-radius: 1rem;
}
.start-btn {
	width: 100%;
	width: 100%;
	color: #ffffff;
	font-size: 30px;
	font-weight: bolder;
}
</style>
