<template>
	<div class="frame" style="overflow: scroll">
		<div>
			<h2 class="title">당신의 결과는?</h2>
		</div>
		<MbtiGameFinish></MbtiGameFinish>
	</div>
</template>

<script>
import MbtiGameFinish from '@/components/game/MbtiGameFinish.vue';
export default {
	components: {
		MbtiGameFinish,
	},
	setup() {
		return {};
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
	color: #000000;
	padding: 10px;
	font-size: 20px;
}

// img {
// 	width: 200px;
// 	height: 200px;
// }

.content {
	text-align: center;
	margin: 0px 20px 0px 20px;
	padding: 30px;
	font-weight: bold;
}
.hash {
	text-align: center;
	margin: 0px 20px 0px 20px;
	font-weight: bold;
}
.game-btn {
	width: 90px;
	height: 50px;
	background-color: #0d6aba;
	display: inline-table;
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
