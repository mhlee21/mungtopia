<template>
	<div class="frame" style="overflow: scroll">
		<div>
			<h2 class="title">당신의 결과는?</h2>
		</div>
		<div class="hash">
			<div style="color: #61378c">
				#상상력 #순발력 <br />
				# 직관적<br />
			</div>
			<div style="color: #5dbf17">ENFP 비글</div>
		</div>
		<div style="text-align: center">
			<img src="" width="200" height="200" alt="" />
		</div>
		<div class="content">
			<div>당신과 잘 맞는 MBTI는?</div>
		</div>
		<div style="display: flex; justify-content: space-between">
			<div>
				<div style="text-align: center">
					ISTJ<br />
					시베리안 허스키
				</div>
				<div style="text-align: center">
					<img src="" width="100px" height="100px" alt="" />
				</div>
			</div>
			<div>
				<div style="text-align: center">
					ISFJ<br />
					슈나우저
				</div>
				<div style="text-align: center">
					<img src="" width="100px" height="100px" alt="" />
				</div>
			</div>
			<div>
				<div style="text-align: center">
					ESFJ<br />
					닥스훈트
				</div>
				<div style="text-align: center">
					<img src="" width="100px" height="100px" alt="" />
				</div>
			</div>
		</div>
		<div style="text-align: center">
			<div class="game-btn" style="margin: 0% 5% 0% 0%">
				<div class="start-btn">SHARE</div>
			</div>
			<div class="game-btn" style="margin: 0% 0% 0% 5%">
				<div class="start-btn">GAME MENU</div>
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
