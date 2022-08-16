<template>
	<div class="game-main-pg">
		<div class="game-header">
			<img
				:src="require('@/assets/img/gameMain1.png')"
				alt="개 아이콘  제작자: Freepik - Flaticon"
			/>
		</div>
		<div class="game-body">
			<div class="title">나와 잘맞는 반려견은?</div>
			<div class="description">
				간단한 설문을 통해<br />당신과 밀접한 키워드를 알아보고<br />
				반려견 추천을 받아보세요!
			</div>
			<div class="button-wrapper">
				<button class="start-btn" @click="clickStart">Start</button>
				<button class="start-btn" @click="clickBack">Back</button>
			</div>
		</div>
	</div>
</template>

<script>
import { useRoute, useRouter } from 'vue-router';
export default {
	setup() {
		const router = useRouter();
		const route = useRoute();
		const clickStart = () => {
			router.push({
				name: 'gamePlay',
				params: { gameType: route.params.gameType },
			});
		};
		const clickBack = () => {
			router.push({
				name: 'game',
			});
		};
		return { clickStart, clickBack };
	},
};
</script>

<style lang="scss" scoped></style>
