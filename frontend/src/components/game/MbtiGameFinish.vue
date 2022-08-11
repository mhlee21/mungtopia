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
			:src="require('@/assets/images/' + mbtiResult + '.jpg')"
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
					:src="require('@/assets/images/' + mbtiDog?.fitWell[0] + '.jpg')"
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
					:src="require('@/assets/images/' + mbtiDog?.fitWell[1] + '.jpg')"
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
					:src="require('@/assets/images/' + mbtiDog?.fitWell[2] + '.jpg')"
					width="75"
					height="75"
					alt=""
				/>
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
</template>

<script>
import { useStore } from 'vuex';
import { computed } from 'vue';
import { useRoute } from 'vue-router';

export default {
	setup() {
		const route = useRoute();
		const store = useStore();
		const mbtiResult = computed(() => route.params.mbtiResult);
		console.log(mbtiResult);
		store.dispatch('game/MbtiDogResult', mbtiResult.value);
		const mbtiDog = computed(() => store.getters['game/mbtiDog']);
		console.log(mbtiDog);
		return { mbtiResult, mbtiDog };
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
</style>
