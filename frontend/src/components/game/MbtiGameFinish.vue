<template>
	<div class="mbti-result-frame">
		<div>
			<ol class="hashtag">
				<li>{{ mbtiDog?.hashtag[0] }}</li>
				<li>{{ mbtiDog?.hashtag[1] }}</li>
				<li>{{ mbtiDog?.hashtag[2] }}</li>
			</ol>
		</div>
		<div style="text-align: center">
			<img
				:src="require('@/assets/img/' + mbtiResult + '.jpg')"
				width="150"
				height="150"
				alt=""
			/>
		</div>
		<div class="mbti">
			<div>{{ mbtiResult }}</div>
			<!-- <div></div> -->
		</div>
		<div class="content">
			<p>당신과 잘 맞는 MBTI는?</p>
		</div>
		<div class="mbti-match">
			<div class="dog-match">
				<div>
					<img
						:src="require('@/assets/img/' + mbtiDog?.fitWell[0] + '.jpg')"
						class="dog-img"
						alt=""
					/>
				</div>
				<div class="dog-title">
					<div>{{ mbtiDog?.fitWell[0] }}</div>
					<div>{{ mbtiDog?.fitWellDog[0] }}</div>
				</div>
			</div>
			<div class="dog-match">
				<div>
					<img
						:src="require('@/assets/img/' + mbtiDog?.fitWell[1] + '.jpg')"
						class="dog-img"
						alt=""
					/>
				</div>
				<div class="dog-title">
					<div>{{ mbtiDog?.fitWell[1] }}</div>
					<div>{{ mbtiDog?.fitWellDog[1] }}</div>
				</div>
			</div>
			<div class="dog-match">
				<div>
					<img
						:src="require('@/assets/img/' + mbtiDog?.fitWell[2] + '.jpg')"
						class="dog-img"
						alt=""
					/>
				</div>
				<div class="dog-title">
					<div>{{ mbtiDog?.fitWell[2] }}</div>
					<div>{{ mbtiDog?.fitWellDog[2] }}</div>
				</div>
			</div>
		</div>
		<div class="button-wrapper">
			<a class="menu-btn" @click="gameMenu">Back to Menu</a>
			<button @click="onClickShareStory" class="share-btn">Share</button>
		</div>
	</div>
</template>

<script>
import { useStore } from 'vuex';
import { computed } from 'vue';
import { useRoute } from 'vue-router';
import router from '@/router';

export default {
	setup() {
		const route = useRoute();
		const store = useStore();
		const mbtiResult = computed(() => route.params.mbti);
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
		const gameMenu = () => {
			router.push({ path: '/game' });
		};
		return { mbtiResult, mbtiDog, onClickShareStory, gameMenu };
	},
};
</script>

<style lang="scss" scoped>
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
