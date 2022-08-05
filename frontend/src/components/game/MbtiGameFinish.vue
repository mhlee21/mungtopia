<template>
	<div class="hash">
		<div style="color: #61378c">
			{{ mbtiDog[0]['hashtag'][0] }} {{ mbtiDog[0]['hashtag'][1] }} <br />
			{{ mbtiDog[0]['hashtag'][2] }}<br />
		</div>
	</div>
	<div class="content">
		{{ mbtiResult }}
	</div>
	<div style="text-align: center">
		<img :src="require(mbtiDog[0]['DogImg'])" width="200" height="200" alt="" />
	</div>
	<div class="content">
		<div>당신과 잘 맞는 MBTI는?</div>
	</div>
	<div style="display: flex; justify-content: space-between">
		<div>
			<div style="text-align: center">
				{{ mbtiDog[0]['fitWell'][0] }}<br />
				{{ mbtiDog[0]['fitWellDog'][0] }}
			</div>
			<div style="text-align: center">
				<img
					:src="mbtiDog[0]['fitWellImg'][0]"
					width="100"
					height="100"
					alt=""
				/>
			</div>
		</div>
		<div>
			<div style="text-align: center">
				{{ mbtiDog[0]['fitWell'][1] }}<br />
				{{ mbtiDog[0]['fitWellDog'][1] }}
			</div>
			<div style="text-align: center">
				<img
					:src="mbtiDog[0]['fitWellImg'][1]"
					width="100"
					height="100"
					alt=""
				/>
			</div>
		</div>
		<div>
			<div style="text-align: center">
				{{ mbtiDog[0]['fitWell'][2] }}<br />
				{{ mbtiDog[0]['fitWellDog'][2] }}
			</div>
			<div style="text-align: center">
				<img
					:src="mbtiDog[0]['fitWellImg'][2]"
					width="100"
					height="100"
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

export default {
	setup() {
		const store = useStore();
		const mbtiResult = computed(() => store.getters['game/mbtiResult']);
		const mbtiDog = computed(() => store.getters['game/mbtiDog']);

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
