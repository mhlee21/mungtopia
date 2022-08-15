<template>
	<div class="hash">
		<div style="color: #61378c">
			{{ mbtiDog?.hashtag[0] }} {{ mbtiDog?.hashtag[1] }} <br />
			{{ mbtiDog?.hashtag[2] }}<br />
		</div>
	</div>
	<div class="content">
		{{ mbtiResult }}
	</div>
	<div style="text-align: center">
		<img
			:src="require('@/assets/img/' + mbtiResult + '.jpg')"
			width="150"
			height="150"
			alt=""
		/>
	</div>
	<div class="content">
		<div>당신과 잘 맞는 MBTI는?</div>
	</div>
	<div style="display: flex; justify-content: space-between">
		<div>
			<div style="text-align: center">
				{{ mbtiDog?.fitWell[0] }}<br />
				{{ mbtiDog?.fitWellDog[0] }}
			</div>
			<div style="text-align: center">
				<img
					:src="require('@/assets/img/' + mbtiDog?.fitWell[0] + '.jpg')"
					width="75"
					height="75"
					alt=""
				/>
			</div>
		</div>
		<div>
			<div style="text-align: center">
				{{ mbtiDog?.fitWell[1] }}<br />
				{{ mbtiDog?.fitWellDog[1] }}
			</div>
			<div style="text-align: center">
				<img
					:src="require('@/assets/img/' + mbtiDog?.fitWell[1] + '.jpg')"
					width="75"
					height="75"
					alt=""
				/>
			</div>
		</div>
		<div>
			<div style="text-align: center">
				{{ mbtiDog?.fitWell[2] }}<br />
				{{ mbtiDog?.fitWellDog[2] }}
			</div>
			<div style="text-align: center">
				<img
					:src="require('@/assets/img/' + mbtiDog?.fitWell[2] + '.jpg')"
					width="75"
					height="75"
					alt=""
				/>
			</div>
		</div>
	</div>
	<div style="text-align: center">
		<div class="game-btn" style="margin: 0% 5% 0% 0%">
			<div class="start-btn" @click="onClickShareStory">SHARE</div>
			<!-- <div class="modal" v-html="msg">No message</div> -->
		</div>
		<div class="game-btn" style="margin: 0% 0% 0% 5%" @click="gameMenu">
			<div class="start-btn">GAME MENU</div>
		</div>
	</div>
</template>

<script>
import { useStore } from 'vuex';
import { computed } from 'vue';

// import { onMounted } from 'vue';
import { useRoute } from 'vue-router';
import router from '@/router';

export default {
	setup() {
		// window.Kakao.Share.createCustomButton({
		// 	container: '#kakaotalk-sharing-btn',
		// 	templateId: 81245,
		// 	templateArgs: {
		// 		title: '제목 영역입니다.',
		// 		description: '설명 영역입니다.',
		// 	},
		// });
		const route = useRoute();
		const store = useStore();
		const mbtiResult = computed(() => route.params.mbtiResult);
		const onClickShareStory = () => {
			window.Kakao.Link.sendCustom({
				templateId: 81245,
				templateArgs: {
					key: mbtiResult.value,
				},
			});
		};
		store.dispatch('game/MbtiDogResult', mbtiResult.value);
		const mbtiDog = computed(() => store.getters['game/mbtiDog']);
		// window.Kakao.Share.createCustomButton({
		// 	templateId: 81245,
		// });
		const gameMenu = () => {
			router.push({ path: '/game' });
		};
		return { mbtiResult, mbtiDog, onClickShareStory, gameMenu };
	},
};
</script>

<style lang="scss" scoped>
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
	height: 75px;
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

img {
	border-radius: 2rem;
}

.modal {
	display: none;
	position: fixed;
	top: 85%;
	left: 50%;
	transform: translate(-50%, 0%);
	z-index: 3;
	padding: 0px 10px;
	border-radius: 10px;
	background-color: #000;
	color: #fff;
	box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
	animation: fadeIn 1s;
}

@keyframes fadeIn {
	from {
		top: 100%;
		opacity: 0;
	}
	to {
		top: 85%;
		opacity: 1;
	}
}
</style>
