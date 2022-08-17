<template>
	<div class="game-main-pg">
		<div class="game-header" :style="getBgColor">
			<img
				:src="require('@/assets/img/gameMain' + (gameTag - 1) + '.png')"
				alt="개 아이콘  제작자: Freepik - Flaticon"
			/>
		</div>
		<div class="game-body">
			<div class="title">{{ mainTitle[gameTag - 1] }}</div>
			<div class="description">{{ mainDescription[gameTag - 1] }}</div>
			<div class="button-wrapper">
				<button class="start-btn" :style="getBgColor" @click="clickStart">
					Start
				</button>
				<button class="start-btn" :style="getBgColor" @click="clickBack">
					Back
				</button>
			</div>
		</div>
	</div>
</template>

<script>
import { computed } from '@vue/reactivity';
import { useRoute, useRouter } from 'vue-router';
export default {
	setup() {
		const router = useRouter();
		const route = useRoute();
		const gameTag = computed(() => Number(route.params.gameTag));

		// 배경색 바꾸기
		const bgColor = ['#88AABF', '#AEB9F2', '#BDD9F2'];
		const getBgColor = computed(() => ({
			'background-color': bgColor[gameTag.value - 1],
		}));
		const mainTitle = ['강아지식테스트', '댕BTI', '나와 잘맞는 반려견은?'];
		const mainDescription = [
			'10문제 중 7문제 이상 맞춰 \n 당신의 반려견 지식을 \n 뽐내보세요!',
			'댕BTI 검사를 통해 \n 당신과 비슷한 강아지를 \n 찾아보세요!',
			'간단한 설문을 통해 \n 당신과 밀접한 키워드를 알아보고\n 반려견 추천을 받아보세요!',
		];
		//
		// 시작 버튼
		const clickStart = () => {
			// 강아지식 테스트
			if (gameTag.value === 1) {
				router.push({
					name: 'game1Play',
				});
			}
			// 댕BTI
			else if (gameTag.value === 2) {
				router.push({
					name: 'game2Play',
				});
			}
			// 나와 잘맞는 반려견은?
			else {
				router.push({
					name: 'game3Play',
				});
			}
		};

		const clickBack = () => {
			router.push({
				name: 'game',
			});
		};
		return {
			clickStart,
			clickBack,
			bgColor,
			getBgColor,
			gameTag,
			mainTitle,
			mainDescription,
		};
	},
};
</script>

<style lang="scss" scoped></style>
