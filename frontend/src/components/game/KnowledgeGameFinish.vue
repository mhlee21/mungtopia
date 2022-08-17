<template>
	<div v-if="correctAnswer >= 7">
		<div class="frame">
			<div style="text-align: center">
				<img
					:src="
						require('@/assets/img/' + 'dog-' + 'success' + randomNum + '.png')
					"
					class="result-img"
					alt=""
				/>
			</div>
			<div>
				<h2 class="title success">SUCCESS</h2>
			</div>
			<div class="content">
				<div>10문제 중 {{ correctAnswer }}문제를 맞춰 성공했어요!</div>
			</div>
			<div class="button-wrapper">
				<a class="menu-btn" @click="gameMenu">Back to Menu</a>
				<button @click="onClickShareStory" class="share-btn success_back">
					Share
				</button>
			</div>
		</div>
	</div>
	<div v-else>
		<div class="frame">
			<div style="text-align: center">
				<img
					:src="require('@/assets/img/' + 'dog-' + 'fail' + randomNum + '.png')"
					alt=""
					class="result-img"
				/>
			</div>
			<div>
				<h2 class="title fail">FAIL</h2>
			</div>
			<div class="content">
				<div>10문제 중 {{ correctAnswer }}문제를 맞춰 실패했어요</div>
			</div>
			<div class="button-wrapper">
				<a class="menu-btn" @click="gameMenu">Back to Menu</a>
				<button @click="onClickShareStory" class="share-btn fail_back">
					Share
				</button>
			</div>
		</div>
	</div>
</template>

<script>
import { useRoute, useRouter } from 'vue-router';

export default {
	setup() {
		const route = useRoute();
		const router = useRouter();
		const randomNum = Math.floor(Math.random() * 3) + 1;
		const correctAnswer = route.params.score;
		const onClickShareStory = () => {
			window.Kakao.Link.sendCustom({
				templateId: 81263,
			});
		};

		const gameMenu = () => {
			router.push({ path: '/game' });
		};
		return { correctAnswer, onClickShareStory, gameMenu, randomNum };
	},
};
</script>

<style lang="scss" scoped>
.frame {
	width: 100vw;
	height: 100vh;
	background-color: white;
	position: relative;
	padding-top: 50px;
	flex-direction: column;
}
.fail {
	color: #ff8f91;
}
.fail_back {
	background-color: #ff8f91;
}
.success {
	color: #94de5e;
}
.success_back {
	background-color: #94de5e;
}
.title {
	text-align: center;
	margin: 3vh 0 2vh;
	font-weight: bold;
	font-size: 3rem;
	font-family: 'GongGothicMedium';
	text-align: center;
	font-weight: 700;
}

.content {
	margin-top: 1.5rem;
	text-align: center;
}

.result-img {
	margin: 6vh 0 2vh;
	width: 230px;
	height: 230px;
}
.button-wrapper {
	background-color: white;
	border: none;
	display: flex;
	align-items: center;
	flex-direction: column;
	padding-top: 5vh;
	text-align: center;
}
.button-wrapper .menu-btn {
	color: #848181;
}
.button-wrapper .share-btn {
	// background-color: #88aabf;
	color: white;
	width: 70vw;
	border: none;
	height: 8vh;
	border-radius: 1rem;
	margin: 2vh;
	font-size: 18px;
	font-family: 'GongGothicLight';
}
</style>
