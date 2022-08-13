<template>
	<div class="frame" style="overflow: scroll">
		<div>
			<h2 class="title">당신에게 맞는 반려견은?</h2>
		</div>
		<div class="content">
			<div>현재 당신을 원하는 반려견이 있습니다.</div>
		</div>
		<div style="text-align: center">
			<img
				class="matching-img"
				:src="require('@/assets/images/' + 'knowledge' + '.jpg')"
				alt=""
			/>
		</div>
		<div style="text-align: center">{{ matchData['dogName'] }}</div>
		<div style="text-align: center">
			매칭 퍼센트 :{{ matchData['percent'] }}%
		</div>
		<div style="text-align: center">
			<div class="game-btn" style="margin: 0% 5% 0% 0%">
				<div class="start-btn" @click="onClickShareStory">SHARE</div>
			</div>
			<div class="game-btn" style="margin: 0% 0% 0% 5%">
				<div class="start-btn">GAME MENU</div>
			</div>
		</div>
	</div>
</template>

<script>
import { useStore } from 'vuex';
import router from '@/router';
import { computed } from 'vue';
export default {
	setup() {
		const onClickShareStory = () => {
			window.Kakao.Link.sendCustom({
				templateId: 81265,
			});
		};
		const store = useStore();
		const matchData = computed(() => store.getters['game/matchData']);
		const enterDetailBoard = boardId => {
			router.push({ path: '/board/detail/' + boardId });
		};
		return { onClickShareStory, matchData, enterDetailBoard };
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
	margin: 20px 0px 30px 0px;
	padding: 30px;
	font-weight: bold;
	background-color: rgba(66, 130, 44, 0.2);
	font-size: 15px;
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
	justify-content: center;
}
.start-btn {
	width: 100%;
	color: #fffbf0;
	font-size: 20px;
	font-weight: bolder;
}

.matching-img {
	width: 60%;
	height: 30%;
}
</style>
