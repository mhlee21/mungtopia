<template>
	<div class="frame">
		<div class="status">
			<h3>상태창</h3>
		</div>
		<div>
			<h2 class="content">
				Q1. 나에게 반려견을 맡긴 친구가 반려견의 안부를 묻는다면?
			</h2>
		</div>
		<div style="text-align: center">
			<img src="" alt="" />
		</div>
		<div class="game-btn">
			<div class="start-btn">
				뽀미는 잘지내!<br />
				뽀미와의 하루에 대해<br />
				보고한다.
			</div>
		</div>
		<div class="game-btn">
			<div class="start-btn">
				사진 폭격!<br />
				사진을 왕창 보내며<br />
				파워주접!
			</div>
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
